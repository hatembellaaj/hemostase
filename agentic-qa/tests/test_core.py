"""Lecture, traçabilité, normalisation, règles, découpage, confidentialité (données artificielles)."""
import json

import pytest

from conftest import DEV_STATUSES, base_row
from hqa.cases import llm_view
from hqa.dictionary import load_dictionary
from hqa.normalize import normalize_case
from hqa.rules import RuleEngine
from hqa.schemas import ValueState
from hqa.split import make_split, save_split, subset
from hqa.workbook import sha256_file


def test_source_file_untouched_and_traceable(mini, d):
    m = mini([base_row(), base_row(patient_name="Fictif B")])
    before = sha256_file(m["path"])
    c = m["cases"][0]
    n = normalize_case(c, d)
    ref = n["fviii_level"].sources[0]
    assert ref.file_sha256 == before and ref.sheet == "Feuil1" and ref.col_letter == "AI" and ref.row == 2
    assert ref.original_header == "facteur VIII" and ref.field_id == "fviii_level"
    assert sha256_file(m["path"]) == before
    assert m["wb"].empty_rows  # la ligne vide de l'étendue est détectée, pas transformée en dossier
    assert len(m["cases"]) == 2


def test_untitled_column_keeps_position_id(mini, d):
    m = mini([base_row()])
    assert m["wb"].rows[2][61].ref.field_id == "col_BJ"  # colonne 62 sans titre


def test_states_empty_unknown_unresolved(mini, d):
    m = mini([base_row(fviii_level="???", fix_level="beaucoup", inhibitor_titer=None)])
    n = normalize_case(m["cases"][0], d)
    assert n["fviii_level"].state == ValueState.UNKNOWN
    assert n["fix_level"].state == ValueState.UNRESOLVED
    assert n["inhibitor_titer"].state == ValueState.EMPTY  # vide : ni anomalie ni « non applicable »


def test_not_applicable_requires_confirmed_definitions(tmp_path, mini, d):
    m = mini([base_row(circumcision=2)])
    n = normalize_case(m["cases"][0], d)
    assert n["circumcision_age"].state == ValueState.EMPTY  # applicabilité à l'état d'hypothèse
    # dictionnaire modifié : codes et applicabilité confirmés
    import yaml
    root = __import__("pathlib").Path(__file__).parent.parent
    fy = yaml.safe_load(open(root / "dictionary" / "fields.yaml", encoding="utf-8"))
    for f in fy["fields"]:
        if f["field_id"] == "circumcision":
            f.update(definition_status="confirmed", approved_by="test")
            for c in f["code_map"].values():
                c["status"] = "confirmed"
        if f["field_id"] == "circumcision_age":
            f["applicability"]["status"] = "confirmed"
    p = tmp_path / "fields_confirmed.yaml"
    p.write_text(yaml.safe_dump(fy, allow_unicode=True))
    d2 = load_dictionary(p, __import__("pathlib").Path(__file__).parent.parent / "dictionary" / "rules.yaml")
    n2 = normalize_case(m["cases"][0], d2)
    assert n2["circumcision_age"].state == ValueState.NOT_APPLICABLE


def test_hypothesis_rules_inactive_by_default(mini, d):
    m = mini([base_row(consanguinity=2, consanguinity_degree="2 eme degré")], statuses=["structural", "approved"])
    anomalies, outcomes = m["engine"].run(m["cases"][0], normalize_case(m["cases"][0], d))
    assert not any(a.rule_id.startswith("H01") for a in anomalies)
    assert any(o.rule_id.startswith("H01") and o.result == "inactive" for o in outcomes)


def test_approved_rule_requires_confirmed_definitions(tmp_path, d):
    import yaml
    ry = yaml.safe_load(open(__import__("pathlib").Path(__file__).parent.parent / "dictionary" / "rules.yaml"))
    for r in ry["rules"]:
        if r["id"].startswith("H01"):
            r.update(status="approved", approved_by="x", approved_on="2026-10-18")
    p = tmp_path / "rules.yaml"
    p.write_text(yaml.safe_dump(ry, allow_unicode=True))
    d2 = load_dictionary(__import__("pathlib").Path(__file__).parent.parent / "dictionary" / "fields.yaml", p)
    with pytest.raises(ValueError):
        d2.active_rules(["structural", "approved"])  # définitions non confirmées -> refus


def test_dependency_and_arithmetic_rules(mini, d):
    m = mini([
        base_row(consanguinity=2, consanguinity_degree="2 eme degré"),           # H01 violée
        base_row(patient_name="B", aptt_patient_control="65/30", aptt_ratio=2.16),  # 2.1667 tronqué : OK
        base_row(patient_name="C", aptt_patient_control="65/30", aptt_ratio=2.17),  # arrondi : OK
        base_row(patient_name="D", aptt_patient_control="65/30", aptt_ratio=2.30),  # écart réel
        base_row(patient_name="E", age_at_diagnosis_text="1 an + 8 mois", age_at_diagnosis_months=20),
        base_row(patient_name="F", age_at_diagnosis_text="11 ans", age_at_diagnosis_months=120),
        base_row(patient_name="G", age_at_diagnosis_text="3 jours", age_at_diagnosis_months=0),
    ])
    res = {}
    for c in m["cases"]:
        a, o = m["engine"].run(c, normalize_case(c, d))
        res[c.excel_row] = ({x.rule_id for x in a}, o)
    assert "H01_consanguinity_degree_dependency" in res[2][0]
    assert "H08_aptt_ratio_arithmetic" not in res[3][0] and "H08_aptt_ratio_arithmetic" not in res[4][0]
    assert "H08_aptt_ratio_arithmetic" in res[5][0]
    assert "H07_age_diagnosis_text_vs_months" not in res[6][0]
    assert "H07_age_diagnosis_text_vs_months" in res[7][0]
    assert any(o.rule_id.startswith("H07") and o.result == "not_evaluable" for o in res[8][1])  # jours : pas de conclusion


def test_severity_threshold_respects_precision_and_censoring(mini, d):
    m = mini([
        base_row(severity=1, fviii_level=1),       # « 1 » = [0,5 ; 1,5[ : à cheval -> non évaluable
        base_row(patient_name="B", severity=1, fviii_level="<1%"),
        base_row(patient_name="C", severity=3, fviii_level=0.12),
    ])
    out = [m["engine"].run(c, normalize_case(c, d)) for c in m["cases"]]
    assert any(o.rule_id.startswith("K01") and o.result == "not_evaluable" for o in out[0][1])
    assert any(o.rule_id.startswith("K01") and o.result == "pass" for o in out[1][1])
    assert any(a.rule_id.startswith("K01") for a in out[2][0])
    assert all(a.rule_status == "proposed_reference" for a in out[2][0] if a.rule_id.startswith("K01"))


def test_age_vs_birth_uses_confirmed_reference_year_only(mini, d):
    from datetime import datetime
    m = mini([base_row(birth_date=datetime(2000, 12, 31), age_recorded=24), base_row(birth_date=datetime(2000, 5, 1), age_recorded=25),
              base_row(birth_date=datetime(2000, 5, 1), age_recorded=26), base_row(birth_date=datetime(2000, 5, 1), age_recorded=3)])
    res = [{x.rule_id: x.result for x in m["engine"].run(c, normalize_case(c, d))[1]}["H11_age_recorded_vs_birth"] for c in m["cases"]]
    # âge calculé en 2024 (24) ou en 2025 (25) : conforme, même né le 31 décembre (comparaison à l'année) ;
    # 26 ou 3 ne correspondent à aucune année de calcul : signalés
    assert res == ["pass", "pass", "fail", "fail"]
    # sans référence, la règle ne conclut pas
    rule = d.rules["H11_age_recorded_vs_birth"]
    saved = dict(rule.params)
    rule.params.update(reference_years=None, reference_date=None)
    try:
        _, o = m["engine"].run(m["cases"][3], normalize_case(m["cases"][3], d))
        assert any(x.rule_id.startswith("H11") and x.result == "not_evaluable" and "référence" in x.reason for x in o)
    finally:
        rule.params.clear(); rule.params.update(saved)


def test_no_merge_on_name_only(mini, d):
    from datetime import datetime
    m = mini([base_row(patient_name="Fictif X", birth_date=datetime(2001, 1, 1)),
              base_row(patient_name="fictif x", birth_date=datetime(2005, 1, 1))])
    assert len(m["cases"]) == 2 and m["cases"][0].study_id != m["cases"][1].study_id
    # une ligne = un patient : même nom, dates de naissance différentes -> personnes distinctes, aucune alerte
    a, _ = m["engine"].run(m["cases"][0], normalize_case(m["cases"][0], d))
    assert not [x for x in a if x.rule_id.startswith("S04")]
    # même nom ET même date de naissance -> double saisie possible signalée, jamais fusionnée
    m = mini([base_row(patient_name="Fictif X", birth_date=datetime(2001, 1, 1)),
              base_row(patient_name="fictif x", birth_date=datetime(2001, 1, 1))])
    assert len(m["cases"]) == 2
    a, _ = m["engine"].run(m["cases"][0], normalize_case(m["cases"][0], d))
    s04 = [x for x in a if x.rule_id.startswith("S04")]
    assert s04 and s04[0].action.kind == "review" and "aucune fusion" in s04[0].justification


def test_split_reproducible_disjoint_grouped(synth):
    s1 = make_split(synth["cases"], 1, 10, "x")
    s2 = make_split(synth["cases"], 1, 10, "x")
    assert s1["dev"] == s2["dev"] and not set(s1["dev"]) & set(s1["test"])
    assert len(s1["dev"]) + len(s1["test"]) == len(synth["cases"])
    dev = subset(synth["cases"], s1, "dev")
    assert {c.study_id for c in dev} == set(s1["dev"])


def test_same_name_rows_stay_in_same_subset(mini, d):
    rows = [base_row(patient_name=f"P{i}") for i in range(12)] + [base_row(patient_name="Dup"), base_row(patient_name="dup")]
    m = mini(rows)
    for seed in range(20):
        s = make_split(m["cases"], seed, 5, "x")
        dup = [c.study_id for c in m["cases"] if c.excel_row in (14, 15)]
        assert (dup[0] in s["dev"]) == (dup[1] in s["dev"])


def test_frozen_split_cannot_be_overwritten(tmp_path, synth):
    s = make_split(synth["cases"], 1, 10, "x")
    s["frozen"] = True
    p = tmp_path / "split.json"
    p.write_text(json.dumps(s))
    with pytest.raises(RuntimeError):
        save_split(make_split(synth["cases"], 2, 10, "x"), p)


def test_llm_view_contains_no_direct_identifier(mini, d):
    from datetime import datetime
    m = mini([base_row(patient_name="Zorglub Q", file_number="SECRET-77", birth_date=datetime(2010, 4, 17),
                       family_similar_cases=1, family_case_relation="frère Barnabé")])
    c = m["cases"][0]
    view = json.dumps(llm_view(c, d, normalize_case(c, d)), ensure_ascii=False)
    for bad in ["Zorglub", "SECRET-77", "2010-04-17", "Barnabé"]:
        assert bad not in view, bad
    assert "[NOM]" in view and "2010-04" in view
