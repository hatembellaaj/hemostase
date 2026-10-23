"""
Modes expérimentaux :
  B0  règles seules (outils déterministes + dictionnaire, sans LLM)
  B1  agent unique, mêmes outils / données / dictionnaire
  B2  workflow fixe : normalisation -> cohérence -> vérification, un seul passage, sans routage
  B3  multi-agent adaptatif : coordinateur conditionnel et borné
  B3-noverif  ablation de B3 sans vérificateur

Tous les modes LLM partent du même pré-traitement déterministe (normalisation + règles actives) :
les anomalies issues des règles sont conservées telles quelles (vérification « deterministic »).
"""
from __future__ import annotations

import time
from dataclasses import dataclass, field

from .agents import Agents
from .cases import Case, llm_view
from .dictionary import Dictionary
from .llm import Budget, BudgetExhausted, InvalidModelOutput, LLMTechnicalError
from .normalize import normalize_case
from .rules import CheckOutcome, RuleEngine
from .schemas import CandidateAnomaly, CaseResult, DecisionLogEntry, NormalizedValue, ValueState, VerificationState
from .tools import TOOL_SPECS, ToolBox

MODES = ["B0", "B1", "B2", "B3", "B3-noverif"]
NEEDS_NORMALIZATION_ISSUES = {"texte_residuel", "variante_lexicale_non_confirmee", "texte_converti"}


@dataclass
class RunContext:
    run_id: str
    mode: str
    d: Dictionary
    engine: RuleEngine
    limits: dict
    agents: Agents | None = None
    coordinator: str = "policy"  # policy | llm
    log: list[DecisionLogEntry] = field(default_factory=list)


def _log(ctx: RunContext, case: Case, step: int, actor: str, action: str, inputs: str, outcome: str, **kw):
    ctx.log.append(DecisionLogEntry(run_id=ctx.run_id, mode=ctx.mode, study_id=case.study_id, step=step, actor=actor,
                                    action=action, inputs_summary=inputs, outcome=outcome, **kw))


def _cells_needing_normalization(norm: dict[str, NormalizedValue], d: Dictionary) -> list[str]:
    return [fid for fid, n in norm.items()
            if d.fields[fid].type not in ("narrative", "text")
            and (n.state == ValueState.UNRESOLVED or NEEDS_NORMALIZATION_ISSUES & set(n.issues))]


def _all_nonempty(norm: dict[str, NormalizedValue], d: Dictionary) -> list[str]:
    return [fid for fid, n in norm.items() if d.fields[fid].type not in ("narrative",) and n.state != ValueState.EMPTY]


def _has_narrative(norm, d):
    return any(d.fields[f].type == "narrative" and n.state == ValueState.OBSERVED for f, n in norm.items())


def _result(ctx, case, norm, anomalies, status, budget: Budget | None, t0, errors, simulated=False) -> CaseResult:
    return CaseResult(
        run_id=ctx.run_id, mode=ctx.mode, study_id=case.study_id, normalized=list(norm.values()), anomalies=anomalies, status=status,
        llm_calls=budget.calls if budget else 0, tokens_in=budget.tokens_in if budget else 0, tokens_out=budget.tokens_out if budget else 0,
        latency_s=round(time.monotonic() - t0, 3), errors=errors, invalid_outputs=budget.invalid_outputs if budget else 0,
        calls_by_role=dict(budget.calls_by_role) if budget else {}, tokens_by_role=dict(budget.tokens_by_role) if budget else {},
        simulated=simulated,
    )


def _budget(limits: dict) -> Budget:
    return Budget(limits["max_llm_calls_per_case"], limits["max_tokens_per_case"], limits["max_seconds_per_case"], limits["max_technical_retries"])


def _merge_norm(norm: dict, proposals: list[NormalizedValue]) -> int:
    changed = 0
    for p in proposals:
        if norm[p.field_id].state in (ValueState.UNRESOLVED,) or norm[p.field_id].method == "deterministic" and NEEDS_NORMALIZATION_ISSUES & set(norm[p.field_id].issues):
            norm[p.field_id] = p
            changed += 1
    return changed


def _apply_verdicts(cands: list[CandidateAnomaly], verdicts: list[dict]) -> list[int]:
    fixable = []
    for i, (c, v) in enumerate(zip(cands, verdicts)):
        c.verification = {"accept": VerificationState.ACCEPTED, "reject": VerificationState.REJECTED,
                          "needs_review": VerificationState.NEEDS_REVIEW}[v["decision"]]
        c.justification = (c.justification + f" | vérification : {v['reason']}")[:400]
        if v["decision"] == "reject" and v["fixable"]:
            fixable.append(i)
    return fixable


# ----------------------------------------------------------------------------------------------- B0
def run_b0(ctx: RunContext, case: Case) -> tuple[CaseResult, list[CheckOutcome]]:
    t0 = time.monotonic()
    norm = normalize_case(case, ctx.d)
    anomalies, outcomes = ctx.engine.run(case, norm, "b0_rules")
    _log(ctx, case, 0, "tool:rules", "deterministic_pass", f"{len(norm)} cellules", f"{len(anomalies)} anomalies candidates")
    return _result(ctx, case, norm, anomalies, "completed", None, t0, []), outcomes


# ----------------------------------------------------------------------------------------------- B2
def run_b2(ctx: RunContext, case: Case) -> CaseResult:
    t0 = time.monotonic()
    budget = _budget(ctx.limits)
    norm = normalize_case(case, ctx.d)
    det, _ = ctx.engine.run(case, norm, "b0_rules")
    tools = ToolBox(case, ctx.d, norm, ctx.engine)
    errors, cands, status, step = [], [], "completed", 0
    try:
        fids = _all_nonempty(norm, ctx.d)  # workflow fixe : toutes les cellules renseignées, sans sélection
        kept, amb, rej = ctx.agents.normalizer(budget, case, norm, fids)
        _merge_norm(norm, kept)
        _log(ctx, case, step := step + 1, "normalizer", "normalize", f"{len(fids)} cellules", f"{len(kept)} retenues, {len(amb)} ambiguës, {rej} rejetées par le code", llm_call=True)
        cands, rej = ctx.agents.coherence(budget, case, norm, det, tools, set(ctx.engine.active))
        _log(ctx, case, step := step + 1, "coherence", "find_candidates", "dossier complet", f"{len(cands)} candidates, {rej} rejetées par le code", llm_call=True)
        if cands and ctx.mode != "B3-noverif":
            verdicts = ctx.agents.verifier(budget, case, cands)
            _apply_verdicts(cands, verdicts)
            _log(ctx, case, step := step + 1, "verifier", "verify", f"{len(cands)} candidates", ",".join(v["decision"] for v in verdicts), llm_call=True)
    except BudgetExhausted as e:
        status, errors = "stopped_budget", [str(e)]
    except (LLMTechnicalError, InvalidModelOutput) as e:
        status, errors = "technical_failure", [f"{type(e).__name__}: {e}"]
        _log(ctx, case, step + 1, "system", "error", "", "échec : les opportunités dépendant des agents restent sans décision", error=str(e))
    return _result(ctx, case, norm, det + cands, status, budget, t0, errors, simulated=ctx.agents.client.simulated)


# ----------------------------------------------------------------------------------------------- B3
def run_b3(ctx: RunContext, case: Case, use_verifier: bool = True) -> CaseResult:
    t0 = time.monotonic()
    L = ctx.limits
    budget = _budget(L)
    norm = normalize_case(case, ctx.d)
    det, _ = ctx.engine.run(case, norm, "b0_rules")
    tools = ToolBox(case, ctx.d, norm, ctx.engine)
    errors: list[str] = []
    status = "completed"
    attempts: dict[str, int] = {}
    cands: list[CandidateAnomaly] = []
    unverified: list[CandidateAnomaly] = []
    rejected_fixable: list[dict] = []
    coherence_done, revisions, no_progress, step, new_info = False, 0, 0, 0, False

    def allowed_actions() -> list[str]:
        acts = []
        pending = [f for f in _cells_needing_normalization(norm, ctx.d) if attempts.get(f, 0) < L["max_normalization_attempts"]]
        if pending:
            acts.append("normalize")
        if not coherence_done and (_has_narrative(norm, ctx.d) or new_info):
            acts.append("coherence")
        if unverified and use_verifier:
            acts.append("verify")
        if rejected_fixable and revisions < L["max_revisions_after_verification"]:
            acts.append("revise")
        acts.append("stop")
        return acts

    while True:
        acts = allowed_actions()
        ok, why = budget.can_call()
        if not ok:
            status = "stopped_budget" if acts != ["stop"] else status
            _log(ctx, case, step + 1, "coordinator", "stop", ",".join(acts), why)
            break
        if no_progress >= L["stop_after_steps_without_progress"]:
            status = "stopped_no_progress"
            _log(ctx, case, step + 1, "coordinator", "stop", ",".join(acts), "absence de progrès")
            break
        if ctx.coordinator == "llm" and len(acts) > 1:
            try:
                action = ctx.agents.coordinator(budget, {"pending_normalization": len([a for a in acts if a == "normalize"]),
                                                         "narrative_present": _has_narrative(norm, ctx.d), "unverified": len(unverified),
                                                         "rejected_fixable": len(rejected_fixable)}, acts)
            except (InvalidModelOutput, LLMTechnicalError, BudgetExhausted) as e:
                errors.append(f"coordinateur LLM : {e}")
                action = acts[0]  # repli explicite sur la politique, journalisé
                _log(ctx, case, step + 1, "coordinator", "fallback_policy", ",".join(acts), action, error=str(e))
        else:
            action = acts[0]  # politique : normaliser > cohérence > vérifier > réviser > arrêter
        step += 1
        _log(ctx, case, step, "coordinator", "route", "actions possibles : " + ",".join(acts), action)
        if action == "stop":
            break
        try:
            if action == "normalize":
                fids = [f for f in _cells_needing_normalization(norm, ctx.d) if attempts.get(f, 0) < L["max_normalization_attempts"]]
                for f in fids:
                    attempts[f] = attempts.get(f, 0) + 1
                kept, amb, rej = ctx.agents.normalizer(budget, case, norm, fids)
                changed = _merge_norm(norm, kept)
                for a in amb:  # une ambiguïté déclarée est une réponse valide : pas de nouvelle tentative
                    attempts[a["field_id"]] = L["max_normalization_attempts"]
                    norm[a["field_id"]].issues.append(f"ambiguite_declaree:{a['reason']}")
                new_info = new_info or changed > 0
                no_progress = 0 if (changed or amb) else no_progress + 1
                _log(ctx, case, step, "normalizer", "normalize", f"{len(fids)} cellules", f"{len(kept)} retenues, {len(amb)} ambiguës, {rej} rejetées", llm_call=True)
            elif action == "coherence":
                new, rej = ctx.agents.coherence(budget, case, norm, det, tools, set(ctx.engine.active))
                coherence_done = True
                cands += new
                unverified += new
                no_progress = 0 if new else no_progress + 1
                _log(ctx, case, step, "coherence", "find_candidates", "dossier", f"{len(new)} candidates, {rej} rejetées", llm_call=True)
            elif action == "verify":
                batch = list(unverified)
                verdicts = ctx.agents.verifier(budget, case, batch)
                fix = _apply_verdicts(batch, verdicts)
                unverified = []
                rejected_fixable = [{"candidate": batch[i].justification, "fields": batch[i].fields, "reason": verdicts[i]["reason"]} for i in fix]
                no_progress = 0
                _log(ctx, case, step, "verifier", "verify", f"{len(batch)} candidates", ",".join(v["decision"] for v in verdicts), llm_call=True)
            elif action == "revise":
                revisions += 1
                fb, rejected_fixable = rejected_fixable, []
                new, rej = ctx.agents.coherence(budget, case, norm, det, tools, set(ctx.engine.active), feedback=fb)
                cands += new
                unverified += new
                no_progress = 0 if new else no_progress + 1
                _log(ctx, case, step, "coherence", "revise", f"{len(fb)} retours du vérificateur", f"{len(new)} candidates", llm_call=True)
        except BudgetExhausted as e:
            status = "stopped_budget"
            errors.append(str(e))
            break
        except (LLMTechnicalError, InvalidModelOutput) as e:
            errors.append(f"{action}: {type(e).__name__}: {e}")
            _log(ctx, case, step, action, "error", "", "échec technique ou sortie invalide", error=str(e))
            if action in ("coherence", "verify", "revise"):
                status = "technical_failure"
                break
            no_progress += 1
    if unverified and use_verifier and status == "completed":
        status = "stopped_budget"
    return _result(ctx, case, norm, det + cands, status, budget, t0, errors, simulated=ctx.agents.client.simulated)


# ----------------------------------------------------------------------------------------------- B1
def run_b1(ctx: RunContext, case: Case) -> CaseResult:
    t0 = time.monotonic()
    budget = _budget(ctx.limits)
    norm = normalize_case(case, ctx.d)
    det, _ = ctx.engine.run(case, norm, "b0_rules")
    tools = ToolBox(case, ctx.d, norm, ctx.engine)
    payload = {
        "case": llm_view(case, ctx.d, norm, ctx.agents.dg),
        "tools": TOOL_SPECS,
        "active_rules": {rid: ctx.d.rules[rid].description for rid in sorted(ctx.engine.active)} | {
            "N01_narrative_vs_structured": ctx.d.rules["N01_narrative_vs_structured"].description},
        "deterministic_findings": [{"rule_id": a.rule_id, "fields": a.fields, "justification": a.justification} for a in det],
        "cells_needing_normalization": _cells_needing_normalization(norm, ctx.d),
        "tool_results": [],
    }
    errors, cands, status, step = [], [], "completed", 0
    from .agents import validate_candidates, validate_proposals

    try:
        while True:
            step += 1
            obj = ctx.agents.single_agent_turn(budget, payload)
            if obj["action"] == "tool":
                res = tools.call(obj["tool"], obj.get("args") or {})
                payload["tool_results"].append({"tool": obj["tool"], "args": obj.get("args"), "result": res})
                _log(ctx, case, step, "single_agent", f"tool:{obj['tool']}", str(obj.get("args"))[:120], "ok" if "error" not in res else res["error"], llm_call=True)
                continue
            kept, amb, rej_p = validate_proposals({"proposals": obj["proposals"]}, _all_nonempty(norm, ctx.d), case, ctx.d)
            _merge_norm(norm, kept)
            for a in amb:
                norm[a["field_id"]].issues.append(f"ambiguite_declaree:{a['reason']}")
            cands, rej_c = validate_candidates({"candidates": obj["candidates"]}, case, ctx.d, tools, set(ctx.engine.active),
                                               "single_agent", ctx.agents.client.simulated)
            raw = obj["candidates"]
            for c in cands:  # auto-vérification déclarée par l'agent unique
                idx = int(c.anomaly_id.rsplit("-", 1)[-1])
                sc = raw[idx].get("self_check") if idx < len(raw) else None
                c.verification = VerificationState.NEEDS_REVIEW if sc == "needs_review" else VerificationState.ACCEPTED if sc == "accept" else VerificationState.NOT_VERIFIED
            _log(ctx, case, step, "single_agent", "final", "", f"{len(kept)} normalisations, {len(cands)} candidates, {rej_p + rej_c} rejetées", llm_call=True)
            break
    except BudgetExhausted as e:
        status, errors = "stopped_budget", [str(e)]
    except (LLMTechnicalError, InvalidModelOutput) as e:
        status, errors = "technical_failure", [f"{type(e).__name__}: {e}"]
        _log(ctx, case, step, "single_agent", "error", "", "échec technique ou sortie invalide", error=str(e))
    return _result(ctx, case, norm, det + cands, status, budget, t0, errors, simulated=ctx.agents.client.simulated)


def run_case(ctx: RunContext, case: Case) -> CaseResult:
    if ctx.mode == "B0":
        return run_b0(ctx, case)[0]
    if ctx.agents is None:
        raise RuntimeError("Mode LLM sans client configuré")
    if ctx.mode == "B1":
        return run_b1(ctx, case)
    if ctx.mode == "B2":
        return run_b2(ctx, case)
    if ctx.mode == "B3":
        return run_b3(ctx, case, use_verifier=True)
    if ctx.mode == "B3-noverif":
        return run_b3(ctx, case, use_verifier=False)
    raise ValueError(ctx.mode)
