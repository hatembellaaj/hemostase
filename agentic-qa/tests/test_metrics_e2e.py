"""Métriques sur de petits cas vérifiables à la main, et bout-en-bout sur données artificielles."""
import json
from pathlib import Path

from hqa.metrics import detection_metrics, paired_bootstrap


def test_detection_metrics_hand_checked():
    family = {f"COH:S{i}:R": "B_coherence" for i in range(1, 8)}
    ref = {"COH:S1:R": "confirmed_anomaly", "COH:S2:R": "confirmed_anomaly", "COH:S3:R": "confirmed_anomaly",
           "COH:S4:R": "acceptable_variation", "COH:S5:R": "not_applicable", "COH:S6:R": "unresolved", "COH:S7:R": "confirmed_anomaly"}
    dec = {"COH:S1:R": "flag", "COH:S2:R": "no_flag", "COH:S3:R": "flag", "COH:S4:R": "flag", "COH:S5:R": "no_flag",
           "COH:S6:R": "flag", "COH:S7:R": "failure"}
    m = detection_metrics(dec, ref, family)["B_coherence"]
    # TP = S1,S3 ; FP = S4 ; FN = S2 ; TN = S5 ; S6 exclu (non résolu) ; S7 sans décision
    assert (m["tp"], m["fp"], m["fn"], m["tn"]) == (2, 1, 1, 1)
    assert m["n_reference_unresolved_excluded"] == 1 and m["n_no_decision_failure"] == 1
    assert abs(m["precision"] - 2 / 3) < 1e-12 and abs(m["recall_decided"] - 2 / 3) < 1e-12
    assert abs(m["recall_global"] - 2 / 4) < 1e-12  # l'échec compte comme manqué
    assert abs(m["coverage"] - 5 / 6) < 1e-12


def test_no_reference_gives_na_not_zero():
    m = detection_metrics({}, {}, {})
    assert m["global"]["status"] == "no_reference"


def test_paired_bootstrap_deterministic():
    f = lambda s, ids: sum(int(i) for i in ids) / len(ids) + (0.1 if s == "B" else 0)  # noqa: E731
    a = paired_bootstrap(["1", "2", "3", "4"], f, ["A", "B"], n=200, seed=3)
    b = paired_bootstrap(["1", "2", "3", "4"], f, ["A", "B"], n=200, seed=3)
    assert a == b
    lo, hi = a["paired_differences"]["B-A"]
    assert abs(lo - 0.1) < 1e-9 and abs(hi - 0.1) < 1e-9  # appariement : la différence est exactement 0,1


def test_synthetic_end_to_end(cfg, tmp_path, monkeypatch):
    import hqa.cli as cli

    monkeypatch.setattr(cli, "ROOT", tmp_path)
    (tmp_path / "examples").mkdir()
    cli.cmd_synthetic(type("A", (), {"rows": 30, "seed": 42, "simulate_llm": False})(), cfg)
    evals = list(Path(cfg["paths"]["runs_dir"]).glob("synthetic-eval-*/metrics.json"))
    assert evals
    m = json.loads(evals[0].read_text())
    assert m["artificial_experiment"] is True
    det = next(iter(m["systems"].values()))["detection"]
    assert det["global"]["n_positive_reference"] > 0
    # chaque erreur injectée qui viole une règle déterministe active doit être détectée par B0 : contrôle d'implémentation
    truth = json.loads((tmp_path / "examples" / "synthetic_truth.json").read_text())
    injected = [t for t in truth["truth"] if t["class"] == "injected"]
    n01 = [t for t in injected if t["target"] == "COH:N01"]
    # B0 (règles seules) détecte toutes les erreurs couvertes par une règle déterministe et AUCUNE contradiction
    # commentaire / champ : FN attendus = nombre de contradictions injectées (le banc ne favorise pas B0)
    assert injected and det["global"]["fn"] == len(n01)
    assert det["global"]["fp"] == 0
    assert (tmp_path / "examples" / "synthetic_registre.xlsx").exists()


def test_exports_contain_no_identifiers(cfg, tmp_path, monkeypatch, d):
    import hqa.cli as cli
    from conftest import base_row, write_rows
    from datetime import datetime

    p = write_rows(tmp_path, d, [base_row(patient_name="Zorglub Q", file_number="SECRET-77", birth_date=datetime(2010, 4, 17),
                                          consanguinity=2, consanguinity_degree="cousin Barnabé", comment_1="vu par frère Barnabé")])
    ns = type("A", (), {"mode": "B0", "subset": "all", "workbook": str(p), "enable_hypothesis_rules": True, "coordinator": "policy", "prompt_version": "v1"})()
    rid = cli.cmd_run(ns, cfg)
    run_dir = Path(cfg["paths"]["runs_dir"]) / rid
    blob = "".join(f.read_text(encoding="utf-8", errors="ignore") for f in run_dir.iterdir())
    for bad in ["Zorglub", "SECRET-77", "2010-04-17", "Barnabé"]:
        assert bad not in blob, bad
    man = json.loads((run_dir / "manifest.json").read_text())
    assert man["llm"]["status"] == "non exécuté" and man["simulated_outputs"] is False
    assert any("hypothèse" in n for n in man["notes"])


def test_single_expert_reference_and_blind_reannotation():
    from hqa.opportunities import intra_rater, reannotation_sample, single_expert_reference

    def row(o, sid, lab):
        return {"opportunity_id": o, "study_id": sid, "family": "B_coherence", "target": "t", "fields": "f",
                "question": "q", "values_shown": "v", "annotator_A_label": lab, "annotator_A_expected_value": "",
                "annotator_B_label": "", "annotator_B_expected_value": "", "adjudicated_label": "", "adjudicated_expected_value": "", "notes": "n"}
    first = [row(f"COH:S{i:02d}:x", f"S{i:02d}", "confirmed_anomaly" if i % 2 else "acceptable_variation") for i in range(10)]
    s1, s2 = reannotation_sample(first, 0.2, 5), reannotation_sample(first, 0.2, 5)
    assert s1 == s2 and len({r["study_id"] for r in s1}) == 2           # reproductible, 20 % des dossiers
    assert all(not r["annotator_A_label"] and not r["notes"] for r in s1)  # aveugle : première annotation non montrée
    ref = single_expert_reference(first)
    assert [r["adjudicated_label"] for r in ref] == [r["annotator_A_label"] for r in first]
    # réannotation : 3 accords sur 4, calcul à la main -> po 0,75 ; pe = 0,5*0,25 + 0,5*0,75 = 0,5 ; kappa 0,5
    f = {r["opportunity_id"]: r for r in first[:4]}
    labs = ["acceptable_variation", "confirmed_anomaly", "acceptable_variation", "acceptable_variation"]
    second = {o: dict(r, annotator_A_label=l) for (o, r), l in zip(f.items(), labs)}
    assert intra_rater(f, second) == {"n": 4, "observed_agreement": 0.75, "cohen_kappa": 0.5}
