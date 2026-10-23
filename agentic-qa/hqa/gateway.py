"""
Passerelle QA (FastAPI) pour des agents externes — ex. assistants Maître/Esclaves de RAISE.

Les agents n'accèdent JAMAIS à l'API nominative du registre (/api/fiches/...) : ils passent par cette
passerelle, qui garantit côté serveur ce que le prompt seul ne peut pas garantir :

  * vue pseudonymisée uniquement (identifiant d'étude, dates au mois, commentaires expurgés) ;
  * refus de servir des données si HQA_ALLOW_REGISTRY_DATA_TO_LLM != yes ;
  * lecture seule : aucune route n'écrit dans le registre ; les propositions sont des actions de revue ;
  * validation par code des sorties d'agents (preuve = source, règle active, pas de précision inventée…) ;
  * bornes : appels d'outils, tentatives de normalisation, révisions après vérification ;
  * un dossier non terminé ou en échec = « technical_failure », jamais « aucune anomalie » ;
  * journal de décision désidentifié et exports au même format que `hqa run` (=> `hqa evaluate`).

    uvicorn hqa.gateway:app --host 0.0.0.0 --port 8095
Documentation OpenAPI : /api/qa/docs  (schéma : /api/qa/openapi.json)
"""
from __future__ import annotations

import json
import os
import secrets
import threading
import time
import uuid
from dataclasses import dataclass, field
from datetime import datetime, timezone
from pathlib import Path
from typing import Any, Literal

from fastapi import Depends, FastAPI, Header, HTTPException
from pydantic import BaseModel, Field

from .agents import validate_candidates, validate_proposals
from .cases import Case, llm_view
from .config import ROOT, load_config
from .exports import write_manifest, write_results
from .normalize import normalize_case
from .pipelines import _all_nonempty, _cells_needing_normalization, _has_narrative, _merge_norm
from .rules import RuleEngine
from .schemas import CandidateAnomaly, CaseResult, DecisionLogEntry, VerificationState
from .split import load_split, subset
from .tools import TOOL_SPECS, ToolBox

PREFIX = "/api/qa"
DEFAULT_GATEWAY_LIMITS = {"max_tool_calls_per_case": 30, "max_case_views": 10}

app = FastAPI(
    title="Passerelle QA du registre d'hémophilie",
    version="1.0.0",
    description="Outils déterministes, vue pseudonymisée et validation des propositions d'agents. "
                "Lecture seule : le registre n'est jamais modifié. Authentification : en-tête X-API-Key.",
    docs_url=f"{PREFIX}/docs", openapi_url=f"{PREFIX}/openapi.json", redoc_url=None,
)


# --------------------------------------------------------------------------------------------- état
@dataclass
class CaseState:
    case: Case
    norm: dict
    det: list[CandidateAnomaly]
    tools: ToolBox
    agent_cands: list[CandidateAnomaly] = field(default_factory=list)
    ambiguous: list[dict] = field(default_factory=list)
    tool_calls: int = 0
    views: int = 0
    norm_attempts: int = 0
    cand_submissions: int = 0
    verdict_submissions: int = 0
    invalid_outputs: int = 0
    rejected_by_code: int = 0
    status: str | None = None  # None = en cours
    errors: list[str] = field(default_factory=list)
    t0: float | None = None
    t_last: float | None = None
    calls_by_role: dict = field(default_factory=dict)
    external_cost: dict = field(default_factory=dict)


@dataclass
class Session:
    session_id: str
    system_label: str
    subset: str
    model: str | None
    prompt_version: str | None
    normalization_scope: str
    cases: dict[str, CaseState]
    split: dict | None
    log: list[DecisionLogEntry] = field(default_factory=list)
    closed: bool = False
    created: str = field(default_factory=lambda: datetime.now(timezone.utc).isoformat(timespec="seconds"))


class _Store:
    def __init__(self):
        self.lock = threading.RLock()
        self.cfg = None
        self.d = self.wb = self.all_cases = self.same = None
        self.sessions: dict[str, Session] = {}

    def load(self, cfg: dict | None = None):
        from .cli import _setup

        self.cfg = cfg or load_config(os.environ.get("HQA_CONFIG") or None)
        self.d, self.wb, self.all_cases, self.same = _setup(self.cfg)


STORE = _Store()


def _cfg() -> dict:
    if STORE.cfg is None:
        STORE.load()
    return STORE.cfg


def require_key(x_api_key: str | None = Header(default=None, alias="X-API-Key")):
    expected = os.environ.get("HQA_API_KEY")
    if not expected or len(expected) < 24:
        raise HTTPException(503, "HQA_API_KEY absent ou trop court (>= 24 caractères) : passerelle désactivée")
    if not x_api_key or not secrets.compare_digest(x_api_key, expected):
        raise HTTPException(401, "clé API invalide")


def require_llm_authorization():
    cfg = _cfg()
    if str(cfg["llm"].get("allow_registry_data")).lower() != "yes":
        raise HTTPException(403, "Données du registre non servies aux agents : HQA_ALLOW_REGISTRY_DATA_TO_LLM doit valoir yes "
                                 "après confirmation de l'environnement LLM et de l'autorisation de traitement.")


AUTH = [Depends(require_key)]


def _session(sid: str) -> Session:
    s = STORE.sessions.get(sid)
    if not s:
        raise HTTPException(404, "session inconnue (la passerelle a peut-être redémarré : créer une nouvelle session)")
    if s.closed:
        raise HTTPException(409, "session close")
    return s


def _case(s: Session, study_id: str) -> CaseState:
    cs = s.cases.get(study_id)
    if not cs:
        raise HTTPException(404, "dossier hors de cette session")
    if cs.status:
        raise HTTPException(409, f"dossier déjà terminé ({cs.status})")
    now = time.monotonic()
    cs.t0 = cs.t0 or now
    cs.t_last = now
    return cs


def _limits() -> dict:
    return {**_cfg()["limits"], **DEFAULT_GATEWAY_LIMITS, **(_cfg().get("gateway") or {})}


def _log(s: Session, cs: CaseState, actor: str, action: str, inputs: str, outcome: str, error: str | None = None):
    s.log.append(DecisionLogEntry(run_id=s.session_id, mode=s.system_label, study_id=cs.case.study_id, step=len(s.log) + 1,
                                  actor=actor, action=action, inputs_summary=inputs[:200], outcome=outcome[:300], error=error))


def _count_tool(cs: CaseState, role: str):
    L = _limits()
    if cs.tool_calls >= L["max_tool_calls_per_case"]:
        raise HTTPException(429, f"budget d'appels d'outils épuisé ({L['max_tool_calls_per_case']}) : appeler finish avec stopped_budget")
    cs.tool_calls += 1
    cs.calls_by_role[role] = cs.calls_by_role.get(role, 0) + 1


def _to_normalize(s: "Session", cs: CaseState) -> list[str]:
    if s.normalization_scope == "all_nonempty":
        return _all_nonempty(cs.norm, STORE.d)
    return _cells_needing_normalization(cs.norm, STORE.d)


def _cand_view(c: CandidateAnomaly) -> dict:
    return {"anomaly_id": c.anomaly_id, "rule_id": c.rule_id, "rule_status": c.rule_status, "fields": c.fields,
            "category": c.category.value, "subtype": c.subtype, "justification": c.justification,
            "evidence": [{"field_id": e.ref.field_id, "quoted_value": e.quoted_value} for e in c.evidence],
            "verification": c.verification.value, "produced_by": c.produced_by}


# --------------------------------------------------------------------------------------------- modèles
class SessionCreate(BaseModel):
    system_label: str = Field(..., pattern=r"^[A-Za-z0-9_.\-]{2,40}$", description="ex. RAISE-B3, RAISE-B1")
    subset: Literal["dev", "test", "all"] = "dev"
    model: str | None = Field(None, description="modèle utilisé par les agents (ex. azure/gpt-4o-prod)")
    prompt_version: str | None = Field(None, description="version des prompts RAISE (ex. raise-v1)")
    max_cases: int | None = Field(None, ge=1, description="essai : limiter la session aux N premiers dossiers (ordre des identifiants)")
    normalization_scope: Literal["needed", "all_nonempty"] = Field(
        "needed", description="needed : cellules non résolues (B1/B3) ; all_nonempty : toutes les cellules renseignées (workflow fixe B2)")


class ToolCall(BaseModel):
    args: dict[str, Any] = Field(default_factory=dict)
    role: Literal["coordinator", "normalizer", "coherence", "verifier", "single_agent"] = "coherence"


class Proposals(BaseModel):
    proposals: list[dict[str, Any]]


class Candidates(BaseModel):
    candidates: list[dict[str, Any]]


class Verdict(BaseModel):
    anomaly_id: str
    decision: Literal["accept", "reject", "needs_review"]
    reason: str = ""
    fixable: bool = False


class Verdicts(BaseModel):
    verdicts: list[Verdict]


class Finish(BaseModel):
    status: Literal["completed", "stopped_budget", "stopped_no_progress", "technical_failure"]
    note: str = ""


class InvalidOutput(BaseModel):
    role: str
    detail: str = ""


class ExternalCost(BaseModel):
    tokens_in: int | None = None
    tokens_out: int | None = None
    llm_calls: int | None = None
    cost: float | None = None
    latency_s: float | None = None


# --------------------------------------------------------------------------------------------- routes
@app.get(f"{PREFIX}/health")
def health():
    return {"status": "ok", "registry_data_to_llm_authorized": str(_cfg()["llm"].get("allow_registry_data")).lower() == "yes"}


@app.get(f"{PREFIX}/dictionary/rules", dependencies=AUTH)
def rules():
    cfg = _cfg()
    eng = RuleEngine(STORE.d, cfg["rules"]["active_statuses"])
    return [{"rule_id": r.id, "status": r.status, "active": r.id in eng.active, "description": r.description} for r in STORE.d.rules.values()]


@app.post(f"{PREFIX}/sessions", dependencies=AUTH + [Depends(require_llm_authorization)])
def create_session(body: SessionCreate):
    cfg = _cfg()
    with STORE.lock:
        cases, split = STORE.all_cases, None
        if body.subset != "all":
            split = load_split(Path(cfg["paths"]["derived_dir"]) / "split.json")
            if body.subset == "test" and not split.get("frozen"):
                raise HTTPException(409, "découpage non figé : exécuter `hqa split --freeze` avant toute exécution sur le test")
            cases = subset(cases, split, body.subset)
        if body.max_cases:
            cases = sorted(cases, key=lambda c: c.study_id)[: body.max_cases]
        engine = RuleEngine(STORE.d, cfg["rules"]["active_statuses"], {"same_name": STORE.same})
        states = {}
        for c in cases:
            norm = normalize_case(c, STORE.d)
            det, _ = engine.run(c, norm, "b0_rules")
            states[c.study_id] = CaseState(c, norm, det, ToolBox(c, STORE.d, norm, engine))
        sid = f"{datetime.now(timezone.utc):%Y%m%dT%H%M%S}-{body.system_label}-{uuid.uuid4().hex[:6]}"
        STORE.sessions[sid] = Session(sid, body.system_label, body.subset, body.model, body.prompt_version,
                                      body.normalization_scope, states, split)
    return {"session_id": sid, "n_cases": len(states), "limits": _limits(),
            "note": "vue pseudonymisée ; aucune écriture dans le registre ; fermer la session pour produire les exports"}


@app.get(f"{PREFIX}/sessions/{{sid}}/cases", dependencies=AUTH)
def list_cases(sid: str):
    s = _session(sid)
    return [{"study_id": k, "status": v.status or "pending"} for k, v in s.cases.items()]


@app.get(f"{PREFIX}/sessions/{{sid}}/cases/{{study_id}}", dependencies=AUTH)
def get_case(sid: str, study_id: str):
    s = _session(sid)
    cs = _case(s, study_id)
    L = _limits()
    if cs.views >= L["max_case_views"]:
        raise HTTPException(429, "trop de lectures de ce dossier : appeler finish")
    cs.views += 1
    eng = cs.tools.engine
    view = llm_view(cs.case, STORE.d, cs.norm, _cfg()["privacy"]["llm_date_generalization"])
    return {
        **view,
        "cells_needing_normalization": _to_normalize(s, cs),
        "has_narrative": _has_narrative(cs.norm, STORE.d),
        "deterministic_findings": [_cand_view(c) for c in cs.det],
        "agent_candidates": [_cand_view(c) for c in cs.agent_cands],
        "ambiguous_cells": cs.ambiguous,
        "active_rules": sorted(eng.active) + ["N01_narrative_vs_structured"],
        "remaining": {"tool_calls": L["max_tool_calls_per_case"] - cs.tool_calls,
                      "normalization_attempts": L["max_normalization_attempts"] - cs.norm_attempts,
                      "candidate_submissions": 1 + L["max_revisions_after_verification"] - cs.cand_submissions,
                      "verdict_submissions": 1 + L["max_revisions_after_verification"] - cs.verdict_submissions},
    }


@app.get(f"{PREFIX}/tools", dependencies=AUTH)
def list_tools():
    return TOOL_SPECS


@app.post(f"{PREFIX}/sessions/{{sid}}/cases/{{study_id}}/tools/{{tool}}", dependencies=AUTH)
def call_tool(sid: str, study_id: str, tool: str, body: ToolCall):
    s = _session(sid)
    cs = _case(s, study_id)
    _count_tool(cs, body.role)
    res = cs.tools.call(tool, body.args)
    _log(s, cs, f"tool:{tool}", "tool_call", json.dumps(body.args, ensure_ascii=False, default=str), "ok" if "error" not in res else res["error"])
    return res


@app.post(f"{PREFIX}/sessions/{{sid}}/cases/{{study_id}}/normalizations", dependencies=AUTH)
def submit_normalizations(sid: str, study_id: str, body: Proposals):
    s = _session(sid)
    cs = _case(s, study_id)
    L = _limits()
    if cs.norm_attempts >= L["max_normalization_attempts"]:
        raise HTTPException(429, "nombre maximal de tentatives de normalisation atteint")
    _count_tool(cs, "normalizer")
    cs.norm_attempts += 1
    requested = _to_normalize(s, cs)
    kept, amb, rej = validate_proposals({"proposals": body.proposals}, requested, cs.case, STORE.d)
    changed = _merge_norm(cs.norm, kept)
    cs.ambiguous = [a for a in cs.ambiguous if a["field_id"] not in {k.field_id for k in kept}] + amb
    cs.rejected_by_code += rej
    _log(s, cs, "normalizer", "normalize", f"{len(body.proposals)} propositions", f"{changed} retenues, {len(amb)} ambiguës, {rej} rejetées par le code")
    return {"kept": [k.field_id for k in kept], "ambiguous": amb, "rejected_by_code": rej,
            "allowed_field_ids": requested, "attempts_left": L["max_normalization_attempts"] - cs.norm_attempts,
            "note": "rejet = proposition non conforme (champ non demandé, précision inventée, censure perdue, valeur complétée, "
                    "valeur numérique modifiée, non_applicable sans confirmation)"}


@app.post(f"{PREFIX}/sessions/{{sid}}/cases/{{study_id}}/candidates", dependencies=AUTH)
def submit_candidates(sid: str, study_id: str, body: Candidates):
    """Chaque soumission REMPLACE les candidates d'agent précédentes (révision)."""
    s = _session(sid)
    cs = _case(s, study_id)
    L = _limits()
    if cs.cand_submissions >= 1 + L["max_revisions_after_verification"]:
        raise HTTPException(429, "nombre maximal de soumissions (1 + révisions) atteint")
    _count_tool(cs, "coherence")
    cs.cand_submissions += 1
    active = set(cs.tools.engine.active)
    accepted, rejected = [], []
    for i, c in enumerate(body.candidates):
        out, _ = validate_candidates({"candidates": [c]}, cs.case, STORE.d, cs.tools, active, "agent:coherence", simulated=False)
        if out:
            out[0].anomaly_id = f"{s.system_label}-{cs.case.study_id}-{cs.cand_submissions}-{i}-{out[0].rule_id}"
            accepted.append((i, out[0]))
        else:
            rejected.append(i)
    cs.agent_cands = [a for _, a in accepted]
    cs.rejected_by_code += len(rejected)
    _log(s, cs, "coherence", "find_candidates", f"{len(body.candidates)} candidates", f"{len(accepted)} acceptées par le code, {len(rejected)} rejetées")
    return {"accepted": [{"index": i, "anomaly_id": a.anomaly_id} for i, a in accepted],
            "rejected_indexes": rejected,
            "submissions_left": 1 + L["max_revisions_after_verification"] - cs.cand_submissions,
            "note": "rejet = règle inactive (hypothèse), champ inconnu ou identifiant, preuve absente ou citation différente de la source. "
                    "Les actions sont toujours converties en « review » : aucune correction automatique."}


@app.post(f"{PREFIX}/sessions/{{sid}}/cases/{{study_id}}/verdicts", dependencies=AUTH)
def submit_verdicts(sid: str, study_id: str, body: Verdicts):
    s = _session(sid)
    cs = _case(s, study_id)
    L = _limits()
    if cs.verdict_submissions >= 1 + L["max_revisions_after_verification"]:
        raise HTTPException(429, "nombre maximal de vérifications atteint")
    by_id = {c.anomaly_id: c for c in cs.agent_cands}
    ids = {v.anomaly_id for v in body.verdicts}
    if ids != set(by_id):
        cs.invalid_outputs += 1
        raise HTTPException(422, f"un verdict par candidate d'agent est requis : attendus {sorted(by_id)}")
    _count_tool(cs, "verifier")
    cs.verdict_submissions += 1
    fixable = []
    for v in body.verdicts:
        c = by_id[v.anomaly_id]
        c.verification = {"accept": VerificationState.ACCEPTED, "reject": VerificationState.REJECTED,
                          "needs_review": VerificationState.NEEDS_REVIEW}[v.decision]
        c.justification = (c.justification + f" | vérification : {v.reason}")[:400]
        if v.decision == "reject" and v.fixable:
            fixable.append(v.anomaly_id)
    _log(s, cs, "verifier", "verify", f"{len(body.verdicts)} verdicts", ",".join(v.decision for v in body.verdicts))
    can_revise = cs.cand_submissions < 1 + L["max_revisions_after_verification"]
    return {"fixable_rejections": fixable, "revision_allowed": bool(fixable) and can_revise}


@app.post(f"{PREFIX}/sessions/{{sid}}/cases/{{study_id}}/invalid-output", dependencies=AUTH)
def report_invalid_output(sid: str, study_id: str, body: InvalidOutput):
    """À appeler quand un agent délégué n'a pas répondu ou a renvoyé une sortie inexploitable."""
    s = _session(sid)
    cs = _case(s, study_id)
    cs.invalid_outputs += 1
    cs.errors.append(f"{body.role}: {body.detail}"[:200])
    _log(s, cs, body.role, "invalid_output", "", body.detail, error="invalid_output")
    return {"recorded": True, "advice": "ne pas conclure à l'absence d'anomalie ; terminer avec technical_failure si l'étape est requise"}


@app.post(f"{PREFIX}/sessions/{{sid}}/cases/{{study_id}}/finish", dependencies=AUTH)
def finish_case(sid: str, study_id: str, body: Finish):
    s = _session(sid)
    cs = _case(s, study_id)
    cs.status = body.status
    if body.note:
        cs.errors.append(f"finish: {body.note}"[:200])
    _log(s, cs, "coordinator", "finish", body.note, body.status)
    return {"study_id": study_id, "status": cs.status, "n_deterministic": len(cs.det), "n_agent_candidates": len(cs.agent_cands)}


@app.post(f"{PREFIX}/sessions/{{sid}}/cases/{{study_id}}/cost", dependencies=AUTH)
def record_cost(sid: str, study_id: str, body: ExternalCost):
    """Coûts mesurés par l'orchestrateur (ex. cost_tracking de RAISE). Accepté aussi après finish."""
    s = STORE.sessions.get(sid)
    if not s or s.closed or study_id not in s.cases:
        raise HTTPException(404, "session ou dossier inconnu")
    s.cases[study_id].external_cost = body.model_dump(exclude_none=True)
    return {"recorded": True}


@app.post(f"{PREFIX}/sessions/{{sid}}/close", dependencies=AUTH)
def close_session(sid: str):
    s = _session(sid)
    cfg = _cfg()
    results = []
    for k, cs in s.cases.items():
        status = cs.status or "technical_failure"
        errors = list(cs.errors) + ([] if cs.status else ["dossier non terminé par l'orchestrateur"])
        ec = cs.external_cost
        lat = ec.get("latency_s") if "latency_s" in ec else ((cs.t_last - cs.t0) if cs.t0 and cs.t_last else 0.0)
        results.append(CaseResult(
            run_id=sid, mode=s.system_label, study_id=k, normalized=list(cs.norm.values()), anomalies=cs.det + cs.agent_cands,
            status=status, llm_calls=int(ec.get("llm_calls") or 0), tokens_in=int(ec.get("tokens_in") or 0),
            tokens_out=int(ec.get("tokens_out") or 0), latency_s=round(float(lat or 0), 3), errors=errors,
            invalid_outputs=cs.invalid_outputs + cs.rejected_by_code, calls_by_role=dict(cs.calls_by_role), simulated=False,
        ))
    run_dir = Path(cfg["paths"]["runs_dir"]) / sid
    write_results(run_dir, results, s.log, STORE.d)
    notes = ["Exécution pilotée par un orchestrateur externe via la passerelle QA (ex. RAISE Maître/Esclaves).",
             "Coûts LLM : ceux transmis par l'orchestrateur (/cost) ; absents = non mesurés, pas nuls."]
    unfinished = sum(1 for cs in s.cases.values() if not cs.status)
    if unfinished:
        notes.append(f"{unfinished} dossier(s) non terminé(s) : comptés en technical_failure.")
    write_manifest(run_dir, run_id=sid, mode=s.system_label, cfg=cfg, d=STORE.d, prompts_hashes={"external": s.prompt_version or "non renseigné"},
                   workbook_sha=STORE.wb.sha256, split=s.split, subset=s.subset,
                   llm_info={"orchestrator": "external", "model": s.model, "prompt_version": s.prompt_version},
                   results=results, notes=notes, root=ROOT)
    s.closed = True
    return {"run_id": sid, "run_dir": str(run_dir), "n_cases": len(results), "unfinished": unfinished,
            "next": f"hqa evaluate --runs {sid} ... --annotations data/derived/annotation_<subset>_adjudicated.csv"}
