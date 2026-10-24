"""
Agents LLM spécialisés et agent unique. Chaque sortie est validée par du code avant d'être retenue :
- références de champs existantes, valeurs citées identiques à la source (désidentifiée) ;
- précision temporelle jamais plus fine que la source ; opérateur des valeurs censurées conservé ;
- « non applicable » seulement si l'applicabilité est confirmée ; règle citée active ou N01.
Les rejets sont comptés (sorties non conformes), jamais transformés en « pas d'anomalie ».
"""
from __future__ import annotations

import hashlib
import json
from pathlib import Path

from .cases import Case, llm_view
from .dictionary import Dictionary
from .llm import Budget, LLMClient, call_json
from .parsers import ParseFailure, parse_date, parse_number
from .schemas import (
    CandidateAnomaly,
    Category,
    Comparator,
    Evidence,
    NormalizedValue,
    ProposedAction,
    TemporalPrecision,
    ValueState,
    VerificationState,
)
from .tools import TOOL_SPECS, ToolBox

PREC_RANK = {"year": 0, "month": 1, "day": 2}


class Prompts:
    def __init__(self, directory: str | Path, version: str = "v1"):
        self.dir = Path(directory)
        self.version = version
        self.texts = {p.stem.split(".")[0]: p.read_text(encoding="utf-8") for p in self.dir.glob(f"*.{version}.md")}
        self.hashes = {k: hashlib.sha256(v.encode()).hexdigest()[:16] for k, v in self.texts.items()}

    def system(self, role: str) -> str:
        return self.texts["common"] + "\n\n" + self.texts[role]


def _dict_excerpt(d: Dictionary, field_ids: list[str]) -> dict:
    out = {}
    for fid in field_ids:
        f = d.fields[fid]
        out[fid] = {
            "name": f.contextual_name, "type": f.type, "unit": f.unit, "unit_status": f.unit_status,
            "definition_status": f.definition_status, "note": f.definition_note,
            "codes": {str(k): f"{c.label} ({c.status})" for k, c in (f.code_map or {}).items()},
            "applicability": f.applicability.model_dump() if f.applicability else None,
        }
    return out


def code_meanings(d: Dictionary, view: dict) -> dict:
    """Signification (et statut) des codes des champs codés renseignés : sans elle, un agent devrait deviner le sens."""
    return {fid: {"name": f.contextual_name, "definition_status": d.fields[fid].definition_status,
                  "codes": {str(k): f"{c.label} ({c.status})" for k, c in (d.fields[fid].code_map or {}).items()}}
            for fid, f in ((fid, d.fields[fid]) for fid, e in view["fields"].items() if e.get("value") is not None)
            if f.type == "code"}


# ---------------------------------------------------------------------------------------- validation
def validate_proposals(obj: dict, requested: list[str], case: Case, d: Dictionary) -> tuple[list[NormalizedValue], list[dict], int]:
    """-> (valeurs retenues, déclarations d'ambiguïté, nombre de propositions rejetées par le code)."""
    props = obj["proposals"]
    if not isinstance(props, list):
        raise ValueError("proposals doit être une liste")
    kept, ambiguous, rejected = [], [], 0
    for p in props:
        fid = p.get("field_id")
        if fid not in requested:
            rejected += 1
            continue
        raw = case.cells[fid].raw_value
        f = d.fields[fid]
        if p.get("decision") == "ambiguous" or p.get("state") == "unresolved":
            ambiguous.append({"field_id": fid, "reason": p.get("ambiguity_reason") or "information_insuffisante",
                              "justification": str(p.get("justification", ""))[:300]})
            continue
        try:
            state = ValueState(p.get("state", "observed"))
        except ValueError:
            rejected += 1
            continue
        if raw is None and state == ValueState.OBSERVED:
            rejected += 1  # interdiction de compléter une valeur manquante
            continue
        if state == ValueState.NOT_APPLICABLE and not (f.applicability and f.applicability.status == "confirmed"):
            rejected += 1
            continue
        comp = p.get("comparator")
        if isinstance(raw, str) and raw.strip()[:1] in "<>≤≥" and comp in (None, "="):
            rejected += 1  # opérateur de censure perdu
            continue
        tp = p.get("temporal_precision")
        if f.type == "date":
            det = parse_date(raw)
            if not isinstance(det, ParseFailure) and tp and PREC_RANK.get(tp, 3) > PREC_RANK[det.temporal_precision.value]:
                rejected += 1  # précision inventée
                continue
            if isinstance(raw, int) and tp in ("month", "day"):
                rejected += 1
                continue
        if f.type in ("number", "integer") and state == ValueState.OBSERVED:
            det = parse_number(raw, f.unit)
            if not isinstance(det, ParseFailure) and p.get("value") is not None:
                try:
                    if abs(float(p["value"]) - det.value) > 1e-9:
                        rejected += 1  # la valeur numérique lisible ne peut pas être changée par la normalisation
                        continue
                except (TypeError, ValueError):
                    rejected += 1
                    continue
        try:
            nv = NormalizedValue(
                field_id=fid, state=state, value_type=p.get("value_type") or "none", value=p.get("value"), unit=p.get("unit"),
                comparator=Comparator(comp) if comp else None,
                temporal_precision=TemporalPrecision(tp) if tp else None,
                justification=str(p.get("justification", ""))[:300] or "(sans justification)", method="llm",
                sources=[case.cells[fid].ref], issues=["proposition_llm_non_validee_par_expert"],
            )
        except (ValueError, TypeError):
            rejected += 1
            continue
        kept.append(nv)
    return kept, ambiguous, rejected


def rules_for_agents(d: Dictionary, active_rules: set[str]) -> dict[str, str]:
    """Relations confiées aux agents : celles qu'aucun code ne sait évaluer (deterministic: false). Les règles formelles
    sont exécutées par le moteur de règles ; leurs résultats sont fournis aux agents comme contexte."""
    return {rid: r.description for rid, r in sorted(d.rules.items())
            if not r.deterministic and (rid in active_rules or r.status == "structural")}


def agent_rule_context(d: Dictionary, active_rules: set[str], det_findings: list[CandidateAnomaly]) -> dict:
    return {
        "rules_to_examine": rules_for_agents(d, active_rules),
        "rules_already_evaluated_by_code": sorted(r for r in active_rules if r in d.rules and d.rules[r].deterministic),
        "deterministic_findings": [{"rule_id": a.rule_id, "fields": a.fields, "justification": a.justification} for a in det_findings],
    }


def validate_candidates(obj: dict, case: Case, d: Dictionary, tools: ToolBox, active_rules: set[str], produced_by: str,
                        simulated: bool) -> tuple[list[CandidateAnomaly], int]:
    cands = obj["candidates"]
    if not isinstance(cands, list):
        raise ValueError("candidates doit être une liste")
    out, rejected = [], 0
    for i, c in enumerate(cands):
        rid = c.get("rule_id")
        fields = c.get("fields") or []
        evid = c.get("evidence") or []
        if rid not in rules_for_agents(d, active_rules):
            # règle formelle (déjà évaluée par le code), inactive ou inconnue : ne peut pas fonder une anomalie d'agent
            rejected += 1
            continue
        if not fields or any(fid not in d.fields or d.fields[fid].type == "identifier" for fid in fields):
            rejected += 1
            continue
        if not evid or not all(e.get("field_id") in fields and tools.quoted_matches(e["field_id"], e.get("quoted_value")) for e in evid):
            rejected += 1  # preuve absente ou citation non conforme à la source
            continue
        try:
            cat = Category(c.get("category", "B_coherence"))
            act = c.get("action") or {}
            kind = act.get("kind", "review")
            if kind not in ("review", "confirm_definition"):
                kind = "review"  # aucune correction automatique proposée par un agent sans revue
            rule = d.rules.get(rid)
            out.append(CandidateAnomaly(
                anomaly_id=f"{produced_by}-{case.study_id}-{rid}-{'-'.join(fields)}-{i}", study_id=case.study_id,
                check_id=f"{rid}:{'+'.join(sorted(fields))}", rule_id=rid, rule_version=rule.version if rule else None,
                rule_status=rule.status if rule else None, fields=fields, category=cat, subtype=str(c.get("subtype", "agent"))[:60],
                evidence=[Evidence(ref=case.cells[e["field_id"]].ref, quoted_value=str(e.get("quoted_value"))) for e in evid],
                justification=str(c.get("justification", ""))[:300], action=ProposedAction(kind=kind, detail=str(act.get("detail", ""))[:200]),
                produced_by=produced_by, simulated=simulated,
            ))
        except (ValueError, TypeError):
            rejected += 1
    return out, rejected


def validate_verdicts(obj: dict, n: int) -> list[dict]:
    vs = obj["verdicts"]
    if not isinstance(vs, list):
        raise ValueError("verdicts doit être une liste")
    out = {}
    for v in vs:
        i = v.get("candidate_index")
        if not isinstance(i, int) or not 0 <= i < n or v.get("decision") not in ("accept", "reject", "needs_review"):
            raise ValueError("verdict invalide")
        out[i] = {"decision": v["decision"], "reason": str(v.get("reason", ""))[:300], "fixable": bool(v.get("fixable"))}
    if len(out) != n:
        raise ValueError("un verdict par proposition est requis")
    return [out[i] for i in range(n)]


# ---------------------------------------------------------------------------------------- agents
class Agents:
    def __init__(self, client: LLMClient, prompts: Prompts, d: Dictionary, max_tokens: int, date_generalization: str):
        self.client, self.p, self.d, self.max_tokens, self.dg = client, prompts, d, max_tokens, date_generalization

    def normalizer(self, budget: Budget, case: Case, norm: dict, field_ids: list[str], feedback: str | None = None):
        view = llm_view(case, self.d, norm, self.dg)
        payload = {"study_id": case.study_id, "cells": {f: view["fields"][f] for f in field_ids if f in view["fields"]},
                   "dictionary": _dict_excerpt(self.d, field_ids)}
        if feedback:
            payload["previous_attempt_feedback"] = feedback
        obj, _ = call_json(self.client, budget, "normalizer", self.p.system("normalizer"), json.dumps(payload, ensure_ascii=False, default=str),
                           self.max_tokens, lambda o: o if "proposals" in o else (_ for _ in ()).throw(ValueError("proposals manquant")))
        return validate_proposals(obj, field_ids, case, self.d)

    def coherence(self, budget: Budget, case: Case, norm: dict, det_findings: list[CandidateAnomaly], tools: ToolBox,
                  active_rules: set[str], feedback: list[dict] | None = None):
        view = llm_view(case, self.d, norm, self.dg)
        payload = {"case": view, "code_meanings": code_meanings(self.d, view), **agent_rule_context(self.d, active_rules, det_findings)}
        if feedback:
            payload["verifier_feedback_on_previous_candidates"] = feedback
        obj, _ = call_json(self.client, budget, "coherence", self.p.system("coherence"), json.dumps(payload, ensure_ascii=False, default=str),
                           self.max_tokens, lambda o: o if "candidates" in o else (_ for _ in ()).throw(ValueError("candidates manquant")))
        return validate_candidates(obj, case, self.d, tools, active_rules, "agent:coherence", self.client.simulated)

    def verifier(self, budget: Budget, case: Case, candidates: list[CandidateAnomaly]):
        payload = {"study_id": case.study_id, "candidates": [
            {"index": i, "rule": (self.d.rules[c.rule_id].description if c.rule_id in self.d.rules else None),
             "rule_status": c.rule_status, "fields": c.fields,
             "evidence": [{"field_id": e.ref.field_id, "field_name": self.d.fields[e.ref.field_id].contextual_name, "value": e.quoted_value} for e in c.evidence],
             "justification": c.justification}
            for i, c in enumerate(candidates)],
            "dictionary": _dict_excerpt(self.d, sorted({f for c in candidates for f in c.fields}))}
        obj, _ = call_json(self.client, budget, "verifier", self.p.system("verifier"), json.dumps(payload, ensure_ascii=False, default=str),
                           self.max_tokens, lambda o: {"verdicts": validate_verdicts(o, len(candidates))})
        return obj["verdicts"]

    def coordinator(self, budget: Budget, state_summary: dict, allowed: list[str]) -> str:
        payload = {"state": state_summary, "allowed_actions": allowed,
                   "budget_remaining": {"calls": budget.max_calls - budget.calls, "tokens": budget.max_tokens - budget.tokens_in - budget.tokens_out}}
        obj, _ = call_json(self.client, budget, "coordinator", self.p.system("coordinator"), json.dumps(payload, ensure_ascii=False),
                           200, lambda o: o if o.get("action") in allowed else (_ for _ in ()).throw(ValueError("action non autorisée")))
        return obj["action"]

    def single_agent_turn(self, budget: Budget, payload: dict) -> dict:
        def val(o):
            if o.get("action") == "tool":
                if o.get("tool") not in TOOL_SPECS:
                    raise ValueError("outil inconnu")
                return o
            if o.get("action") == "final" and isinstance(o.get("proposals", []), list) and isinstance(o.get("candidates", []), list):
                o.setdefault("proposals", [])
                o.setdefault("candidates", [])
                return o
            raise ValueError("action invalide")
        obj, _ = call_json(self.client, budget, "single_agent", self.p.system("single_agent"), json.dumps(payload, ensure_ascii=False, default=str),
                           self.max_tokens, val)
        return obj
