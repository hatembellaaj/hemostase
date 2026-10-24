"""
Données ENTIÈREMENT ARTIFICIELLES et perturbations contrôlées, avec vérité terrain et graine.

Aucune valeur n'est tirée du registre réel : seules la structure (positions et intitulés des colonnes du
dictionnaire) est reprise. Trois classes de perturbation, toujours séparées :
  variation    formats équivalents, unités explicites, précision temporelle -> la valeur reste correcte
  injected     violation d'une règle -> anomalie attendue
  undecidable  valeur inconnue ou ambiguë -> « unresolved » attendu
Les résultats sur ces données valident le logiciel ; ils n'estiment PAS la fréquence des erreurs naturelles.
"""
from __future__ import annotations

import csv
import json
import random
from datetime import datetime
from pathlib import Path

import openpyxl

from .dictionary import Dictionary
from .opportunities import ANNOTATION_COLUMNS

N_COLS = 165


def _clean_row(rng: random.Random, i: int) -> dict[str, object]:
    year = rng.randint(1980, 2020)
    months_diag = rng.choice([2, 6, 12, 18, 24, 36, 48])
    form = rng.choice([1, 1, 1, 2])
    sev = rng.choice([1, 1, 2, 3])
    level = {1: rng.choice([0.2, 0.5, 0.8, "<1"]), 2: rng.choice([2, 3, 4]), 3: rng.choice([8, 12, 20])}[sev]
    p, t = rng.choice([(60, 30), (72, 30), (45, 30), (90, 30)])
    right, left = rng.choice([(1, 2), (2, 1), (1, 1), (2, 2)])
    inhib = rng.choice([1, 2, 2, 2])
    circ = rng.choice([1, 1, 2])
    cons = rng.choice([1, 2])
    fam = rng.choice([1, 2])
    row = {
        "patient_name": f"Patient Fictif {i:02d}", "city": "Ville fictive", "file_number": f"FX-{i:03d}",
        "birth_date": datetime(year, rng.randint(1, 12), rng.randint(1, 28)),
        "age_recorded": 2024 - year,
        "sex": 1, "consanguinity": cons, "consanguinity_degree": "2 eme degré" if cons == 1 else None,
        "family_similar_cases": fam, "family_case_relation": "frère" if fam == 1 else None,
        "age_first_signs": f"{max(1, months_diag // 2)} mois", "age_at_diagnosis_text": f"{months_diag} mois" if months_diag < 12 else f"{months_diag // 12} an{'s' if months_diag >= 24 else ''}" + (f" + {months_diag % 12} mois" if months_diag % 12 else ""),
        "age_at_diagnosis_months": months_diag,
        "aptt_patient_control": f"{p}/{t}", "aptt_ratio": int(p / t * 100) / 100,
        "clinical_form": form, "severity": sev,
        "fviii_level": level if form == 1 else rng.choice([80, 100, 120]), "fix_level": level if form == 2 else None,
        "hemarthrosis": 1 if 1 in (right, left) else rng.choice([1, 2]),
        "hemarthrosis_elbow_right": right, "hemarthrosis_elbow_left": left, "hemarthrosis_elbow_any": 1 if 1 in (right, left) else 2,
        "circumcision": circ, "circumcision_age": f"{rng.randint(1, 6)} ans" if circ == 1 else None,
        "inhibitor": inhib, "inhibitor_responder": (rng.choice([1, 2]) if inhib == 1 else None),
        "ddn": datetime(2024, rng.randint(1, 12), 1),
        "comment_1": None, "comment_2": None,
    }
    if inhib == 1:
        row["inhibitor_titer"] = 2.0 if row["inhibitor_responder"] == 1 else 40
    return row


# (classe, nom, fonction de perturbation, opportunité visée, étiquette attendue)
def _perturbations():
    return [
        ("variation", "censure_avec_espace", lambda r: r.update(fviii_level="< 1 %") if r["clinical_form"] == 1 and r["severity"] == 1 else None, "UNC:fviii_level", "acceptable_variation"),
        ("variation", "unite_explicite", lambda r: r.update(fviii_level=f"{r['fviii_level']}%") if isinstance(r["fviii_level"], (int, float)) else None, "UNC:fviii_level", "acceptable_variation"),
        ("variation", "virgule_decimale", lambda r: r.update(fviii_level=str(r["fviii_level"]).replace(".", ",")) if isinstance(r["fviii_level"], float) else None, "UNC:fviii_level", "acceptable_variation"),
        ("variation", "date_annee_seule", lambda r: r.update(ddn=2024), "UNC:ddn", "acceptable_variation"),
        ("variation", "date_mois_texte", lambda r: r.update(ddn="mars 2024"), "UNC:ddn", "acceptable_variation"),
        ("injected", "dependance_degre", lambda r: r.update(consanguinity=2, consanguinity_degree="3 eme degré"), "COH:H01_consanguinity_degree_dependency", "confirmed_anomaly"),
        ("injected", "ratio_tca_faux", lambda r: r.update(aptt_ratio=round(r["aptt_ratio"] + 0.4, 2)), "COH:H08_aptt_ratio_arithmetic", "confirmed_anomaly"),
        ("injected", "age_diag_discordant", lambda r: r.update(age_at_diagnosis_months=r["age_at_diagnosis_months"] + 12), "COH:H07_age_diagnosis_text_vs_months", "confirmed_anomaly"),
        ("injected", "synthese_coude", lambda r: r.update(hemarthrosis_elbow_any=2 if r["hemarthrosis_elbow_any"] == 1 else 1), "COH:H05_elbow_synthesis", "confirmed_anomaly"),
        ("injected", "code_hors_domaine", lambda r: r.update(sex=7), "UNC:sex", "confirmed_anomaly"),
        ("injected", "texte_dans_nombre", lambda r: r.update(fviii_level="beaucoup"), "UNC:fviii_level", "confirmed_anomaly"),
        # cas sans règle déterministe (compatibilité commentaire / champ structuré)
        ("injected", "commentaire_contradictoire", lambda r: r.update(hemarthrosis=1, hemarthrosis_elbow_right=1, hemarthrosis_elbow_any=1,
                                                                       comment_1="n'a jamais présenté d'hémarthrose à ce jour"), "COH:N01", "confirmed_anomaly"),
        ("variation", "commentaire_compatible", lambda r: r.update(hemarthrosis=1, hemarthrosis_elbow_right=1, hemarthrosis_elbow_any=1,
                                                                    comment_1="hémarthroses répétées du coude droit"), "COH:N01", "acceptable_variation"),
        ("undecidable", "inconnu_explicite", lambda r: r.update(fviii_level="???"), "UNC:fviii_level", "unresolved"),
        ("undecidable", "plage_de_dates", lambda r: r.update(ddn="23-24"), "UNC:ddn", "unresolved"),
        ("undecidable", "nombre_sans_unite_de_duree", lambda r: r.update(age_first_signs="2"), "UNC:age_first_signs", "unresolved"),
    ]


def generate(d: Dictionary, n_rows: int, seed: int, out_dir: str | Path) -> dict:
    out = Path(out_dir)
    out.mkdir(parents=True, exist_ok=True)
    rng = random.Random(seed)
    perts = _perturbations()
    rows, truth = [], []
    for i in range(1, n_rows + 1):
        r = _clean_row(rng, i)
        applied = None
        if i % 3 != 0:  # deux lignes sur trois reçoivent une perturbation, une sur trois reste propre
            for _ in range(10):
                cls, name, fn, target, label = perts[rng.randrange(len(perts))]
                before = dict(r)
                res = fn(r)
                if r != before:
                    applied = {"row_index": i, "class": cls, "name": name, "target": target, "expected_label": label}
                    break
                r.update(before)
        rows.append(r)
        truth.append(applied or {"row_index": i, "class": "clean", "name": None, "target": None, "expected_label": None})
        # attentes inhérentes au dictionnaire (indépendantes de la perturbation) : un titre d'inhibiteur sans unité,
        # SI le dictionnaire ne fixe pas l'unité, DOIT être signalé (incertitude « unité absente »)
        if d.fields["inhibitor_titer"].unit is None and r.get("inhibitor_titer") is not None \
                and not (applied and applied["target"] == "UNC:inhibitor_titer"):
            truth.append({"row_index": i, "class": "inherent", "name": "unite_absente_dictionnaire",
                          "target": "UNC:inhibitor_titer", "expected_label": "confirmed_anomaly"})
    # classeur : intitulés du dictionnaire à leurs positions
    wb = openpyxl.Workbook()
    ws = wb.active
    ws.title = "Feuil1"
    by_col = {f.source_col: f for f in d.fields.values()}
    ws.append([by_col[c].original_header if c in by_col else f"autre_{c}" for c in range(1, N_COLS + 1)])
    for r in rows:
        ws.append([r.get(by_col[c].field_id) if c in by_col else None for c in range(1, N_COLS + 1)])
    path = out / "synthetic_registre.xlsx"
    wb.save(path)
    meta = {"seed": seed, "n_rows": n_rows, "classes": sorted({t["class"] for t in truth}),
            "warning": "Données entièrement artificielles. Résultats = validation logicielle, pas estimation des erreurs réelles.",
            "truth": truth}
    (out / "synthetic_truth.json").write_text(json.dumps(meta, indent=1, ensure_ascii=False), encoding="utf-8")
    return {"workbook": str(path), "truth": meta}


def reference_from_truth(opps: list[dict], truth: dict, row_to_sid: dict[int, str]) -> list[dict]:
    """Construit une annotation « adjudiquée » à partir de la vérité terrain (expérience artificielle uniquement).
    Opportunité visée par la perturbation -> étiquette attendue ; autres opportunités B/C -> acceptable_variation."""
    target = {}
    for t in truth["truth"]:
        if t["target"]:
            sid = row_to_sid[t["row_index"] + 1]  # ligne Excel = index + 1 (en-tête)
            fam, key = t["target"].split(":")
            target[f"{fam}:{sid}:{key}"] = t["expected_label"]
    out = []
    for o in opps:
        if o["family"] == "A_normalisation":
            continue
        lab = target.get(o["opportunity_id"], "acceptable_variation")
        out.append({**{k: "" for k in ANNOTATION_COLUMNS}, **o, "adjudicated_label": lab,
                    "notes": "référence artificielle (vérité terrain du générateur)"})
    return out


def write_reference(rows: list[dict], path: str | Path) -> None:
    with open(path, "w", newline="", encoding="utf-8-sig") as fh:
        w = csv.DictWriter(fh, fieldnames=ANNOTATION_COLUMNS, delimiter=";")
        w.writeheader()
        for r in rows:
            w.writerow({k: r.get(k, "") for k in ANNOTATION_COLUMNS})
