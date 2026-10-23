"""
Rapprochement alertes <-> opportunités et métriques.

Politique de rapprochement (fixée avant le test) :
  - une alerte est rattachée à l'opportunité de même dossier et de même cible :
      règle B (H.., K.., N01)          -> COH:<sid>:<rule_id>
      S01/S02/S03 (champ unique)       -> UNC:<sid>:<field>
      S04                              -> UNC:<sid>:identity
  - plusieurs alertes sur la même opportunité comptent pour UNE détection (déduplication) ;
  - une alerte rejetée par le vérificateur n'est pas une détection (comptée à part) ;
  - une alerte sans opportunité correspondante est listée « hors opportunités » (adjudication a posteriori),
    sans entrer dans la précision principale ;
  - les opportunités d'un dossier en échec technique sont « sans décision » : ni positives ni négatives,
    elles comptent comme manquées dans le rappel global.

Référence : confirmed_anomaly = positif ; acceptable_variation / not_applicable = négatif ;
unresolved = exclu (compté). Aucune métrique n'est calculée sans référence : valeur None + raison.
"""
from __future__ import annotations

import random
from collections import defaultdict

from .schemas import CaseResult, VerificationState

NA = None


def opportunity_for(alert) -> str | None:
    rid = alert.rule_id or ""
    if rid.startswith(("S01", "S02", "S03")) and len(alert.fields) == 1:
        return f"UNC:{alert.study_id}:{alert.fields[0]}"
    if rid.startswith("S04"):
        return f"UNC:{alert.study_id}:identity"
    if rid.startswith("N01"):
        return f"COH:{alert.study_id}:N01"
    if rid:
        return f"COH:{alert.study_id}:{rid}"
    return None


def system_decisions(results: list[CaseResult], opp_ids: set[str]) -> dict:
    """-> {opp_id: 'flag'|'no_flag'|'failure'}, alertes hors opportunités, alertes rejetées."""
    by_case = {r.study_id: r for r in results}
    dec, outside, rejected = {}, [], []
    flagged = set()
    for r in results:
        for a in r.anomalies:
            if a.simulated:
                continue
            oid = opportunity_for(a)
            if a.verification == VerificationState.REJECTED:
                rejected.append(a.anomaly_id)
                continue
            if oid in opp_ids:
                flagged.add(oid)
            else:
                outside.append(a.anomaly_id)
    for oid in opp_ids:
        sid = oid.split(":")[1]
        r = by_case.get(sid)
        if r is None:
            continue
        if r.status == "technical_failure" and oid not in flagged and not oid.startswith("NORM"):
            dec[oid] = "failure"
        else:
            dec[oid] = "flag" if oid in flagged else "no_flag"
    return {"decisions": dec, "outside": outside, "rejected": rejected}


def _prf(tp, fp, fn):
    p = tp / (tp + fp) if tp + fp else None
    r = tp / (tp + fn) if tp + fn else None
    f = 2 * p * r / (p + r) if p is not None and r is not None and p + r else (None if p is None or r is None else 0.0)
    return p, r, f


def detection_metrics(decisions: dict[str, str], reference: dict[str, str], family_of: dict[str, str]) -> dict:
    """reference : opp_id -> étiquette adjudiquée. Retourne métriques par famille et globales avec dénominateurs."""
    out = {}
    fams = sorted(set(family_of.values())) + ["global"]
    for fam in fams:
        ids = [o for o, lab in reference.items() if lab and (fam == "global" or family_of.get(o) == fam) and not o.startswith("NORM")]
        if not ids:
            out[fam] = {"status": "no_reference", "reason": "aucune opportunité annotée"}
            continue
        tp = fp = fn = tn = fail_pos = fail_neg = 0
        unresolved = 0
        for o in ids:
            lab, d = reference[o], decisions.get(o)
            if lab == "unresolved":
                unresolved += 1
                continue
            pos = lab == "confirmed_anomaly"
            if d is None or d == "failure":
                fail_pos += pos
                fail_neg += not pos
                continue
            if d == "flag":
                tp += pos
                fp += not pos
            else:
                fn += pos
                tn += not pos
        p, r, f = _prf(tp, fp, fn)
        n_pos = tp + fn + fail_pos
        out[fam] = {
            "n_opportunities_annotated": len(ids), "n_reference_unresolved_excluded": unresolved,
            "n_positive_reference": n_pos, "tp": tp, "fp": fp, "fn": fn, "tn": tn,
            "n_no_decision_failure": fail_pos + fail_neg,
            "precision": p, "precision_denominator": "alertes émises sur opportunités décidées (tp+fp)",
            "recall_decided": r, "recall_decided_denominator": "positifs de référence décidés (tp+fn)",
            "recall_global": tp / n_pos if n_pos else None, "recall_global_denominator": "tous les positifs de référence, y compris sans décision",
            "f1_decided": f,
            "coverage": (tp + fp + fn + tn) / (tp + fp + fn + tn + fail_pos + fail_neg) if (tp + fp + fn + tn + fail_pos + fail_neg) else None,
        }
    return out


def normalization_metrics(results: list[CaseResult], reference_values: dict[str, dict]) -> dict:
    """reference_values : NORM:<sid>:<field> -> {"state":..,"value":..,"unit":..,"comparator":..,"temporal_precision":..} ou {"state":"unresolved"}"""
    if not reference_values:
        return {"status": "no_reference", "reason": "aucune valeur normalisée de référence"}
    sysv = {f"NORM:{r.study_id}:{n.field_id}": n for r in results for n in r.normalized}
    exact = abstain = wrong = ref_unres = 0
    for oid, ref in reference_values.items():
        if ref.get("state") == "unresolved":
            ref_unres += 1
            continue
        n = sysv.get(oid)
        if n is None or n.state.value == "unresolved":
            abstain += 1
            continue
        ok = n.state.value == ref.get("state")
        for k in ("value", "unit", "comparator", "temporal_precision"):
            if k in ref:
                sv = getattr(n, k)
                sv = sv.value if hasattr(sv, "value") else sv
                ok = ok and (sv == ref[k] or (isinstance(sv, float) and isinstance(ref[k], (int, float)) and abs(sv - ref[k]) < 1e-9))
        exact += ok
        wrong += not ok
    decided = exact + wrong
    total = decided + abstain
    return {
        "n_reference": len(reference_values), "n_reference_unresolved_excluded": ref_unres,
        "exact": exact, "wrong": wrong, "abstained": abstain,
        "accuracy_on_decided": exact / decided if decided else None, "accuracy_on_decided_denominator": "cellules normalisées par le système (hors abstention)",
        "accuracy_global": exact / total if total else None, "accuracy_global_denominator": "toutes les cellules de référence résolues (abstention = échec)",
        "abstention_rate": abstain / total if total else None, "coverage": decided / total if total else None,
    }


def correction_metrics(results: list[CaseResult], reference_values: dict[str, dict]) -> dict:
    props = [(r.study_id, a) for r in results for a in r.anomalies if a.action.kind == "correct_value" and not a.simulated]
    if not props:
        return {"status": "not_applicable", "reason": "aucune proposition de correction émise (non autorisées par la configuration actuelle)"}
    unjustified = sum(1 for _, a in props if not a.evidence or not a.rule_id)
    wrong = sum(1 for sid, a in props if (ref := reference_values.get(f"NORM:{sid}:{a.fields[0]}")) and ref.get("value") != a.action.proposed_value)
    return {"n_proposals": len(props), "unjustified_rate": unjustified / len(props), "wrong_rate_vs_reference": wrong / len(props)}


def cost_metrics(results: list[CaseResult], price_in: float | None, price_out: float | None) -> dict:
    n = len(results)
    if not n:
        return {"status": "not_run"}
    calls = sum(r.llm_calls for r in results)
    ti = sum(r.tokens_in for r in results)
    to = sum(r.tokens_out for r in results)
    roles = defaultdict(int)
    for r in results:
        for k, v in r.calls_by_role.items():
            roles[k] += v
    statuses = defaultdict(int)
    for r in results:
        statuses[r.status] += 1
    lat = sorted(r.latency_s for r in results)
    cost = (ti / 1000 * price_in + to / 1000 * price_out) if price_in is not None and price_out is not None else None
    return {
        "n_cases": n, "llm_calls_total": calls, "llm_calls_mean": calls / n, "calls_by_role": dict(roles),
        "tokens_in": ti, "tokens_out": to, "cost": cost, "cost_note": None if cost is not None else "prix non renseigné",
        "latency_median_s": lat[n // 2], "latency_max_s": lat[-1],
        "technical_errors": sum(len(r.errors) for r in results), "invalid_outputs": sum(r.invalid_outputs for r in results),
        "statuses": dict(statuses),
        "cases_sent_to_expert": sum(1 for r in results if any(a.verification != VerificationState.REJECTED for a in r.anomalies)),
    }


def paired_bootstrap(case_ids: list[str], metric_fn, systems: list[str], n: int = 2000, seed: int = 7, conf: float = 0.95) -> dict:
    """metric_fn(system, sampled_case_ids) -> float|None. Même rééchantillon de DOSSIERS pour tous les systèmes."""
    rng = random.Random(seed)
    samples = {s: [] for s in systems}
    diffs = defaultdict(list)
    for _ in range(n):
        ids = [rng.choice(case_ids) for _ in case_ids]
        vals = {s: metric_fn(s, ids) for s in systems}
        for s, v in vals.items():
            if v is not None:
                samples[s].append(v)
        for i, a in enumerate(systems):
            for b in systems[i + 1:]:
                if vals[a] is not None and vals[b] is not None:
                    diffs[f"{b}-{a}"].append(vals[b] - vals[a])
    lo_q, hi_q = (1 - conf) / 2, 1 - (1 - conf) / 2

    def ci(xs):
        if len(xs) < n * 0.5:
            return None  # trop de rééchantillons non définis
        xs = sorted(xs)
        return [xs[int(lo_q * (len(xs) - 1))], xs[int(hi_q * (len(xs) - 1))]]

    return {"per_system": {s: ci(v) for s, v in samples.items()}, "paired_differences": {k: ci(v) for k, v in diffs.items()},
            "n_resamples": n, "unit": "dossier", "seed": seed}
