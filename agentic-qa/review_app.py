"""
Interface légère de revue et d'annotation (Streamlit).

    streamlit run review_app.py

Trois écrans :
  1. Annotation en aveugle : un expert (A ou B) étiquette les opportunités SANS voir les sorties des systèmes.
     Chaque expert écrit dans son propre fichier (indépendance).
  2. Adjudication : fusion des deux annotations, désaccords à trancher.
  3. Revue des anomalies candidates d'une exécution : confirmer / rejeter / laisser non résolu, autoriser une
     correction (enregistrée seulement : le registre n'est jamais modifié). Revue chronométrée par dossier.
"""
from __future__ import annotations

import csv
import json
import time
from datetime import datetime, timezone
from pathlib import Path

import streamlit as st

ROOT = Path(__file__).resolve().parent
DERIVED = ROOT / "data" / "derived"
RUNS = ROOT / "runs"
LABELS = ["", "confirmed_anomaly", "acceptable_variation", "not_applicable", "unresolved"]
LABEL_FR = {"": "—", "confirmed_anomaly": "Anomalie confirmée", "acceptable_variation": "Variation acceptable",
            "not_applicable": "Non applicable", "unresolved": "Non résolu"}


def read_csv(p: Path) -> list[dict]:
    with open(p, encoding="utf-8-sig") as fh:
        return list(csv.DictReader(fh, delimiter=";"))


def write_csv(p: Path, rows: list[dict]) -> None:
    with open(p, "w", newline="", encoding="utf-8-sig") as fh:
        w = csv.DictWriter(fh, fieldnames=list(rows[0]), delimiter=";")
        w.writeheader()
        w.writerows(rows)


st.set_page_config(page_title="Revue qualité — registre", layout="wide")
st.title("Contrôle qualité du registre")
st.caption("Outil d'aide à la revue des données. Aucune décision thérapeutique ; le registre original n'est jamais modifié.")
screen = st.sidebar.radio("Écran", ["Annotation en aveugle", "Adjudication", "Revue des anomalies"])

# ------------------------------------------------------------------------------------------------ annotation
if screen == "Annotation en aveugle":
    templates = sorted(p for p in DERIVED.glob("annotation_*.csv") if not p.stem.endswith(("_A", "_B", "_adjudicated")))
    if not templates:
        st.info("Créez d'abord un gabarit : python -m hqa.cli annotation-template --subset test")
        st.stop()
    tpl = st.sidebar.selectbox("Gabarit", templates, format_func=lambda p: p.name)
    who = st.sidebar.selectbox("Annotateur", ["A", "B"])
    out = tpl.with_name(f"{tpl.stem}_{who}.csv")
    rows = read_csv(out if out.exists() else tpl)
    fam = st.sidebar.multiselect("Familles", sorted({r["family"] for r in rows}), default=sorted({r["family"] for r in rows}))
    cases = sorted({r["study_id"] for r in rows})
    sid = st.sidebar.selectbox("Dossier", cases)
    col = f"annotator_{who}_label"
    done = sum(1 for r in rows if r[col])
    st.sidebar.progress(done / len(rows), text=f"{done} / {len(rows)} opportunités étiquetées")
    st.subheader(f"Dossier {sid}")
    st.caption("Les sorties des systèmes ne sont pas affichées sur cet écran.")
    changed = False
    for i, r in enumerate(rows):
        if r["study_id"] != sid or r["family"] not in fam:
            continue
        with st.container(border=True):
            st.markdown(f"**{r['question']}**  \n`{r['opportunity_id']}`")
            st.text(r["values_shown"] or "(vide)")
            c1, c2, c3 = st.columns([2, 3, 3])
            lab = c1.selectbox("Étiquette", LABELS, index=LABELS.index(r[col]) if r[col] in LABELS else 0,
                               format_func=LABEL_FR.get, key=f"l{i}")
            exp = c2.text_input("Valeur attendue (JSON, normalisation)", r[f"annotator_{who}_expected_value"], key=f"v{i}",
                                help='ex. {"state": "observed", "value": 1, "comparator": "<", "unit": "%"}') if r["family"] == "A_normalisation" else ""
            note = c3.text_input("Note", r["notes"], key=f"n{i}")
            if exp:
                try:
                    json.loads(exp)
                except json.JSONDecodeError:
                    c2.error("JSON invalide")
            if (lab, exp, note) != (r[col], r[f"annotator_{who}_expected_value"], r["notes"]):
                r[col], r[f"annotator_{who}_expected_value"], r["notes"] = lab, exp, note
                changed = True
    if st.button("Enregistrer", type="primary") or changed:
        write_csv(out, rows)
        st.success(f"Enregistré dans {out.name}")

# ------------------------------------------------------------------------------------------------ adjudication
elif screen == "Adjudication":
    pairs = sorted(DERIVED.glob("annotation_*_A.csv"))
    pairs = [p for p in pairs if p.with_name(p.name.replace("_A.csv", "_B.csv")).exists()]
    if not pairs:
        st.info("Il faut les deux fichiers d'annotation (A et B).")
        st.stop()
    pa = st.sidebar.selectbox("Annotations", pairs, format_func=lambda p: p.name.replace("_A.csv", ""))
    A = {r["opportunity_id"]: r for r in read_csv(pa)}
    B = {r["opportunity_id"]: r for r in read_csv(pa.with_name(pa.name.replace("_A.csv", "_B.csv")))}
    out = pa.with_name(pa.name.replace("_A.csv", "_adjudicated.csv"))
    merged = []
    for oid, a in A.items():
        b = B.get(oid, {})
        r = dict(a)
        r["annotator_B_label"] = b.get("annotator_B_label", "")
        r["annotator_B_expected_value"] = b.get("annotator_B_expected_value", "")
        if not r.get("adjudicated_label") and r["annotator_A_label"] and r["annotator_A_label"] == r["annotator_B_label"]:
            r["adjudicated_label"] = r["annotator_A_label"]
            if r["annotator_A_expected_value"] == r["annotator_B_expected_value"]:
                r["adjudicated_expected_value"] = r["annotator_A_expected_value"]
        merged.append(r)
    dis = [r for r in merged if r["annotator_A_label"] != r["annotator_B_label"] and not r.get("adjudicated_label")]
    st.metric("Désaccords à trancher", len(dis))
    for i, r in enumerate(dis[:50]):
        with st.container(border=True):
            st.markdown(f"**{r['question']}** — `{r['opportunity_id']}`")
            st.text(r["values_shown"])
            st.write(f"A : {LABEL_FR.get(r['annotator_A_label'])} · B : {LABEL_FR.get(r['annotator_B_label'])}")
            r["adjudicated_label"] = st.selectbox("Décision", LABELS, format_func=LABEL_FR.get, key=f"a{i}")
            r["adjudicated_expected_value"] = st.text_input("Valeur attendue (JSON)", key=f"e{i}")
    if st.button("Enregistrer l'adjudication", type="primary"):
        write_csv(out, merged)
        st.success(f"{out.name} enregistré")

# ------------------------------------------------------------------------------------------------ revue
else:
    runs = sorted((p for p in RUNS.glob("*/anomalies_deidentified.csv")), reverse=True)
    if not runs:
        st.info("Aucune exécution : python -m hqa.cli run --mode B0 --subset dev")
        st.stop()
    rp = st.sidebar.selectbox("Exécution", runs, format_func=lambda p: p.parent.name)
    man = json.loads((rp.parent / "manifest.json").read_text(encoding="utf-8"))
    if man.get("simulated_outputs"):
        st.warning("Exécution avec FAUX modèle : sorties simulées, à ne pas utiliser comme résultat.")
    for n in man.get("notes", []):
        st.info(n)
    rows = read_csv(rp) if rp.read_text(encoding="utf-8-sig").strip() != "aucune ligne" else []
    reviewer = st.sidebar.text_input("Relecteur", "")
    log_path = rp.parent / "human_review.csv"
    log = read_csv(log_path) if log_path.exists() else []
    reviewed = {r["anomaly_id"] for r in log}
    cases = sorted({r["study_id"] for r in rows})
    if not cases:
        st.success("Aucune anomalie candidate dans cette exécution.")
        st.stop()
    sid = st.sidebar.selectbox("Dossier", cases)
    key = f"t_{rp.parent.name}_{sid}"
    st.session_state.setdefault(key, time.time())
    st.subheader(f"Dossier {sid}")
    for i, r in enumerate(x for x in rows if x["study_id"] == sid):
        with st.container(border=True):
            st.markdown(f"**{r['category']} — {r['subtype']}** · règle `{r['rule_id']}` ({r['rule_status']}) · {r['produced_by']} · vérification : {r['verification']}")
            st.write(r["justification"])
            st.code(r["evidence"].replace(" | ", "\n"))
            st.caption(f"Action proposée : {r['action']}")
            c1, c2 = st.columns([2, 3])
            dec = c1.radio("Décision", ["confirmed_anomaly", "acceptable_variation", "not_applicable", "unresolved"],
                           format_func=LABEL_FR.get, key=f"d{i}", horizontal=True, index=None)  # aucune présélection
            corr = c2.text_input("Correction autorisée (facultatif, enregistrée seulement)", key=f"c{i}")
            if st.button("Valider", key=f"b{i}", disabled=r["anomaly_id"] in reviewed or not reviewer or dec is None):
                log.append({"anomaly_id": r["anomaly_id"], "study_id": sid, "reviewer": reviewer, "decision": dec,
                            "authorized_correction": corr, "seconds_on_case": round(time.time() - st.session_state[key], 1),
                            "ts": datetime.now(timezone.utc).isoformat(timespec="seconds")})
                write_csv(log_path, log)
                st.rerun()
    if not reviewer:
        st.sidebar.warning("Indiquez le relecteur pour enregistrer.")
