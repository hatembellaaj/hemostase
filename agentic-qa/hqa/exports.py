"""Exports désidentifiés, journaux, tableaux pour l'article et manifeste d'expérience."""
from __future__ import annotations

import csv
import hashlib
import importlib.metadata
import json
import platform
import subprocess
from pathlib import Path

from .dictionary import Dictionary
from .schemas import CandidateAnomaly, CaseResult, DecisionLogEntry

NOT_RUN = "non exécuté"


def generalize(d: Dictionary, field_id: str, value: str | None) -> str | None:
    """Exports : dates de naissance / DDN ramenées au mois, identifiants masqués."""
    if value is None:
        return None
    f = d.fields.get(field_id)
    if f and f.privacy_role == "direct_identifier":
        return "[identifiant]"
    if f and f.type == "date" and f.privacy_role == "quasi_identifier" and len(value) >= 7:
        return value[:7]
    return value


def anomaly_rows(anoms: list[CandidateAnomaly], d: Dictionary) -> list[dict]:
    rows = []
    for a in anoms:
        rows.append({
            "anomaly_id": a.anomaly_id, "study_id": a.study_id, "check_id": a.check_id, "rule_id": a.rule_id,
            "rule_version": a.rule_version, "rule_status": a.rule_status, "fields": "+".join(a.fields), "category": a.category.value,
            "subtype": a.subtype,
            "evidence": " | ".join(f"{e.ref.cell_id} [{e.ref.field_id}] = {generalize(d, e.ref.field_id, e.quoted_value)}" for e in a.evidence),
            "justification": a.justification, "action": f"{a.action.kind}: {a.action.detail}", "produced_by": a.produced_by,
            "verification": a.verification.value, "human_validation": a.human.value, "simulated": a.simulated,
        })
    return rows


def write_csv(rows: list[dict], path: Path) -> None:
    if not rows:
        path.write_text("aucune ligne\n", encoding="utf-8")
        return
    with open(path, "w", newline="", encoding="utf-8-sig") as fh:
        w = csv.DictWriter(fh, fieldnames=list(rows[0]), delimiter=";")
        w.writeheader()
        w.writerows(rows)


def write_results(run_dir: Path, results: list[CaseResult], log: list[DecisionLogEntry], d: Dictionary) -> None:
    run_dir.mkdir(parents=True, exist_ok=True)
    anoms = [a for r in results for a in r.anomalies]
    write_csv(anomaly_rows(anoms, d), run_dir / "anomalies_deidentified.csv")
    with open(run_dir / "anomalies_deidentified.jsonl", "w", encoding="utf-8") as fh:
        for row in anomaly_rows(anoms, d):
            fh.write(json.dumps(row, ensure_ascii=False) + "\n")
    with open(run_dir / "decision_log.jsonl", "w", encoding="utf-8") as fh:
        for e in log:
            fh.write(e.model_dump_json() + "\n")
    with open(run_dir / "case_results.jsonl", "w", encoding="utf-8") as fh:
        for r in results:
            dump = r.model_dump(mode="json")
            for n in dump["normalized"]:
                if n["value_type"] == "date" and d.fields[n["field_id"]].privacy_role == "quasi_identifier" and n["value"]:
                    n["value"] = str(n["value"])[:7]
            for a in dump["anomalies"]:
                for e in a["evidence"]:
                    e["quoted_value"] = generalize(d, e["ref"]["field_id"], e["quoted_value"])
            fh.write(json.dumps(dump, ensure_ascii=False, default=str) + "\n")


def _versions() -> dict:
    out = {"python": platform.python_version()}
    for pkg in ("openpyxl", "pydantic", "PyYAML", "httpx", "pytest", "streamlit"):
        try:
            out[pkg] = importlib.metadata.version(pkg)
        except importlib.metadata.PackageNotFoundError:
            out[pkg] = None
    return out


def _git_commit(root: Path) -> str | None:
    import os

    try:
        out = subprocess.run(["git", "rev-parse", "HEAD"], cwd=root, capture_output=True, text=True, timeout=5).stdout.strip()
    except (OSError, subprocess.SubprocessError):
        out = ""
    # dans l'image Docker il n'y a pas de .git : le commit est injecté à la construction (HQA_GIT_COMMIT)
    return out or os.environ.get("HQA_GIT_COMMIT") or None


def write_manifest(run_dir: Path, *, run_id: str, mode: str, cfg: dict, d: Dictionary, prompts_hashes: dict | None,
                   workbook_sha: str, split: dict | None, subset: str, llm_info: dict | None, results: list[CaseResult],
                   notes: list[str], root: Path) -> dict:
    m = {
        "run_id": run_id, "mode": mode, "subset": subset,
        "software_versions": _versions(), "git_commit": _git_commit(root),
        "workbook_sha256": workbook_sha,
        "dictionary": {"version": d.version, "fields_sha256": d.fields_sha256, "rules_sha256": d.rules_sha256},
        "active_rule_statuses": cfg["rules"]["active_statuses"],
        "prompts_sha256": prompts_hashes or NOT_RUN,
        "split": {"sha256": split["split_sha256"], "seed": split["seed"], "frozen": split.get("frozen")} if split else NOT_RUN,
        "llm": llm_info or {"status": NOT_RUN, "reason": "mode sans LLM ou LLM non configuré"},
        "limits": cfg["limits"],
        "n_cases": len(results),
        "simulated_outputs": any(r.simulated for r in results),
        "notes": notes,
    }
    from .config import redacted_config

    m["config_sha256"] = hashlib.sha256(json.dumps(redacted_config(cfg), sort_keys=True, default=str).encode()).hexdigest()
    (run_dir / "manifest.json").write_text(json.dumps(m, indent=1, ensure_ascii=False, default=str), encoding="utf-8")
    return m


def fmt(v) -> str:
    if v is None:
        return "NA"
    if isinstance(v, float):
        return f"{v:.3f}"
    return str(v)


def metrics_tables(metrics: dict, out_dir: Path) -> None:
    """Tableaux Markdown/CSV pour l'article. NA = non calculable (jamais 0)."""
    head = []
    if metrics.get("artificial_experiment"):
        head = ["> **Expérience ARTIFICIELLE** (données générées, vérité terrain du générateur) : validation logicielle uniquement, "
                "à ne pas présenter comme une estimation sur le registre réel.", ""]
    if any(s.get("simulated") for s in metrics["systems"].values()):
        head += ["> Les systèmes marqués **[SIMULÉ]** ont été exécutés avec un FAUX modèle : leurs sorties de modèle sont exclues "
                 "des métriques (seules les détections déterministes restent). Ce ne sont PAS des résultats de modèles.", ""]
    lines = head + ["| Système | Famille | Annotées | Exclues (non résolu) | Positifs réf. | TP | FP | FN | Sans décision | Précision | Rappel (décidés) | Rappel global | F1 | Couverture |",
             "|---|---|---|---|---|---|---|---|---|---|---|---|---|---|"]
    csv_rows = []
    for sysname, m in metrics["systems"].items():
        for fam, x in m["detection"].items():
            if x.get("status"):
                lines.append(f"| {sysname} | {fam} | NA ({x['reason']}) |||||||||||||")
                csv_rows.append({"system": sysname, "family": fam, "status": x["status"]})
                continue
            lines.append(f"| {sysname} | {fam} | {x['n_opportunities_annotated']} | {x['n_reference_unresolved_excluded']} | "
                         f"{x['n_positive_reference']} | {x['tp']} | {x['fp']} | {x['fn']} | {x['n_no_decision_failure']} | "
                         f"{fmt(x['precision'])} | {fmt(x['recall_decided'])} | {fmt(x['recall_global'])} | {fmt(x['f1_decided'])} | {fmt(x['coverage'])} |")
            csv_rows.append({"system": sysname, "family": fam, **{k: v for k, v in x.items() if not k.endswith("denominator")}})
    lines += ["", "Dénominateurs : précision = alertes émises sur opportunités décidées ; rappel (décidés) = positifs de référence décidés ; "
              "rappel global = tous les positifs de référence (sans décision = manqué) ; « non résolu » dans la référence = exclu.", ""]
    lines += ["| Système | Appels LLM | Tokens entrée | Tokens sortie | Latence médiane (s) | Erreurs techniques | Sorties non conformes | Dossiers envoyés à l'expert |",
              "|---|---|---|---|---|---|---|---|"]
    for sysname, m in metrics["systems"].items():
        c = m["cost"]
        if c.get("status"):
            lines.append(f"| {sysname} | NA ({c['status']}) |||||||")
            continue
        lines.append(f"| {sysname} | {c['llm_calls_total']} | {c['tokens_in']} | {c['tokens_out']} | {fmt(c['latency_median_s'])} | "
                     f"{c['technical_errors']} | {c['invalid_outputs']} | {c['cases_sent_to_expert']} |")
    (out_dir / "metrics_table.md").write_text("\n".join(lines) + "\n", encoding="utf-8")
    write_csv(csv_rows, out_dir / "metrics_table.csv")
