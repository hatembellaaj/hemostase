"""
Opportunités de vérification définies AVANT l'évaluation, indépendamment des sorties des systèmes,
et fichier d'annotation en aveugle (deux experts + adjudication).

Familles :
  NORM:<sid>:<field>   normalisation d'une cellule renseignée (référence : représentation attendue)
  UNC:<sid>:<field>    la cellule doit-elle être signalée comme incertaine (code non défini, unité absente, format) ?
  UNC:<sid>:identity   question d'identité (même clé de nom qu'un autre dossier)
  COH:<sid>:<rule>     relation entre champs, quand tous les champs nécessaires sont renseignés
  COH:<sid>:N01        compatibilité commentaires / champs structurés (si un commentaire existe)
"""
from __future__ import annotations

import csv
from pathlib import Path

from .cases import Case, safe_value
from .dictionary import Dictionary

LABELS = ["confirmed_anomaly", "acceptable_variation", "not_applicable", "unresolved"]

ANNOTATION_COLUMNS = [
    "opportunity_id", "study_id", "family", "target", "fields", "question", "values_shown",
    "annotator_A_label", "annotator_A_expected_value", "annotator_B_label", "annotator_B_expected_value",
    "adjudicated_label", "adjudicated_expected_value", "notes",
]


def _rule_fields(r) -> list[str]:
    fs = list(r.fields)
    for f in [r.when_filled] + list(r.when_filled_any or []) + ([r.requires["field"]] if r.requires else []):
        if f and f not in fs:
            fs.append(f)
    return fs


def rule_applicable(r, c: Case) -> bool:
    """Une relation est une opportunité si les champs nécessaires à son évaluation sont renseignés."""
    filled = lambda f: c.cells[f].raw_value is not None  # noqa: E731
    if r.when_filled:
        return filled(r.when_filled) and filled(r.requires["field"])
    if r.when_filled_any:
        return any(filled(f) for f in r.when_filled_any) and filled(r.requires["field"])
    if r.id.startswith("K01"):
        return filled("severity") and filled("clinical_form") and (filled("fviii_level") or filled("fix_level"))
    return all(filled(f) for f in r.fields)


def build_opportunities(cases: list[Case], d: Dictionary, same_name: dict[str, list[str]]) -> list[dict]:
    opps = []
    for c in cases:
        for fid, f in d.fields.items():
            if not f.in_scope or f.type == "identifier" or c.cells[fid].raw_value is None:
                continue
            v = safe_value(c, d, fid)
            if f.type != "narrative":
                opps.append({"opportunity_id": f"NORM:{c.study_id}:{fid}", "study_id": c.study_id, "family": "A_normalisation",
                             "target": fid, "fields": fid, "question": f"Représentation normalisée de « {f.contextual_name} »",
                             "values_shown": v})
                opps.append({"opportunity_id": f"UNC:{c.study_id}:{fid}", "study_id": c.study_id, "family": "C_incertitude",
                             "target": fid, "fields": fid,
                             "question": f"La valeur de « {f.contextual_name} » doit-elle être signalée (code non défini, unité absente, format non autorisé, sens ambigu) ?",
                             "values_shown": v})
        opps.append({"opportunity_id": f"UNC:{c.study_id}:identity", "study_id": c.study_id, "family": "C_incertitude",
                     "target": "identity", "fields": "patient_name",
                     "question": "Ce dossier est-il une double saisie possible (même clé de nom et même date de naissance) ?",
                     "values_shown": "dossiers de même nom et même date de naissance : " + (", ".join(s for s in same_name.get(c.study_id, []) if s != c.study_id) or "aucun")})
        for r in d.rules.values():
            if r.kind in ("structural",) or r.id.startswith("S0"):
                continue
            if r.id == "N01_narrative_vs_structured":
                if any(c.cells[f].raw_value is not None for f in r.fields):
                    opps.append({"opportunity_id": f"COH:{c.study_id}:N01", "study_id": c.study_id, "family": "B_coherence",
                                 "target": r.id, "fields": "+".join(r.fields),
                                 "question": "Un commentaire contredit-il un champ structuré (en tenant compte de périodes possiblement différentes) ?",
                                 "values_shown": " | ".join(f"{d.fields[f].contextual_name} = {safe_value(c, d, f)}" for f in r.fields if c.cells[f].raw_value is not None)})
                continue
            fs = _rule_fields(r)
            if not rule_applicable(r, c):
                continue
            opps.append({"opportunity_id": f"COH:{c.study_id}:{r.id}", "study_id": c.study_id, "family": "B_coherence",
                         "target": r.id, "fields": "+".join(fs), "question": r.description,
                         "values_shown": " | ".join(f"{d.fields[f].contextual_name} = {safe_value(c, d, f)}" for f in fs)})
    return opps


def write_annotation_file(opps: list[dict], path: str | Path) -> None:
    """Fichier d'annotation en aveugle : aucune sortie de système n'y figure."""
    with open(path, "w", newline="", encoding="utf-8-sig") as fh:
        w = csv.DictWriter(fh, fieldnames=ANNOTATION_COLUMNS, delimiter=";")
        w.writeheader()
        for o in opps:
            w.writerow({k: o.get(k, "") for k in ANNOTATION_COLUMNS})


def read_annotations(path: str | Path) -> dict[str, dict]:
    out = {}
    with open(path, encoding="utf-8-sig") as fh:
        for row in csv.DictReader(fh, delimiter=";"):
            lab = (row.get("adjudicated_label") or "").strip()
            if lab and lab not in LABELS:
                raise ValueError(f"{row['opportunity_id']} : étiquette inconnue {lab}")
            out[row["opportunity_id"]] = row
    return out


def agreement(annotations: dict[str, dict]) -> dict:
    """Accord brut et kappa de Cohen entre les deux annotateurs (sur les opportunités annotées par les deux)."""
    pairs = [(a["annotator_A_label"], a["annotator_B_label"]) for a in annotations.values() if a["annotator_A_label"] and a["annotator_B_label"]]
    if not pairs:
        return {"n": 0, "observed_agreement": None, "cohen_kappa": None}
    n = len(pairs)
    po = sum(x == y for x, y in pairs) / n
    labs = sorted({x for p in pairs for x in p})
    pe = sum((sum(x == l for x, _ in pairs) / n) * (sum(y == l for _, y in pairs) / n) for l in labs)
    kappa = (po - pe) / (1 - pe) if pe < 1 else None
    return {"n": n, "observed_agreement": round(po, 4), "cohen_kappa": round(kappa, 4) if kappa is not None else None}
