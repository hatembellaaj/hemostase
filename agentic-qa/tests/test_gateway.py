"""Passerelle QA pour orchestrateurs externes (RAISE) — données artificielles uniquement."""
import json
from datetime import datetime
from pathlib import Path

import pytest
from fastapi.testclient import TestClient

from conftest import base_row, write_rows
from hqa import gateway

KEY = "k" * 32
H = {"X-API-Key": KEY}


@pytest.fixture()
def client(cfg, d, tmp_path, monkeypatch):
    p = write_rows(tmp_path, d, [
        base_row(patient_name="Zorglub Q", file_number="SECRET-77", birth_date=datetime(2010, 4, 17),
                 comment_1="hémarthroses répétées, vu avec frère Barnabé"),
        base_row(patient_name="Fictif B", fviii_level="beaucoup"),
    ])
    cfg["paths"]["workbook"] = str(p)
    cfg["llm"]["allow_registry_data"] = "yes"
    cfg["llm"]["authorization_ref"] = "test : données artificielles"
    monkeypatch.setenv("HQA_API_KEY", KEY)
    gateway.STORE.sessions.clear()
    gateway.STORE.load(cfg)
    yield TestClient(gateway.app)
    gateway.STORE.cfg = None


def _open(client):
    r = client.post("/api/qa/sessions", json={"system_label": "RAISE-B3", "subset": "all", "model": "test"}, headers=H)
    assert r.status_code == 200, r.text
    sid = r.json()["session_id"]
    cases = client.get(f"/api/qa/sessions/{sid}/cases", headers=H).json()
    return sid, [c["study_id"] for c in cases]


def test_auth_and_llm_guard(client):
    assert client.get("/api/qa/dictionary/rules").status_code == 401
    assert client.get("/api/qa/dictionary/rules", headers={"X-API-Key": "faux"}).status_code == 401
    gateway.STORE.cfg["llm"]["allow_registry_data"] = "no"
    r = client.post("/api/qa/sessions", json={"system_label": "RAISE-B3", "subset": "all"}, headers=H)
    assert r.status_code == 403  # aucune donnée servie sans autorisation explicite
    gateway.STORE.cfg["llm"]["allow_registry_data"] = "yes"
    gateway.STORE.cfg["llm"]["authorization_ref"] = None
    r = client.post("/api/qa/sessions", json={"system_label": "RAISE-B3", "subset": "all"}, headers=H)
    assert r.status_code == 403  # autorisation non documentée : refus


def test_case_view_is_pseudonymized(client):
    sid, ids = _open(client)
    blob = "".join(json.dumps(client.get(f"/api/qa/sessions/{sid}/cases/{i}", headers=H).json(), ensure_ascii=False) for i in ids)
    for bad in ["Zorglub", "SECRET-77", "2010-04-17", "Barnabé"]:
        assert bad not in blob, bad
    assert "deterministic_findings" in blob


def test_validation_bounds_and_exports(client, cfg):
    sid, ids = _open(client)
    sid_narr = next(i for i in ids if "hémarthroses" in json.dumps(client.get(f"/api/qa/sessions/{sid}/cases/{i}", headers=H).json(), ensure_ascii=False))
    base = f"/api/qa/sessions/{sid}/cases/{sid_narr}"
    good = {"rule_id": "N01_narrative_vs_structured", "fields": ["comment_1", "hemarthrosis"], "category": "B_coherence", "subtype": "x",
            "evidence": [{"field_id": "comment_1", "quoted_value": "hémarthroses répétées"}], "justification": "j",
            "action": {"kind": "correct_value", "detail": "mettre 1"}}
    forged = dict(good, evidence=[{"field_id": "comment_1", "quoted_value": "texte inventé"}])
    inactive = dict(good, rule_id="H01_consanguinity_degree_dependency")
    r = client.post(f"{base}/candidates", json={"candidates": [good, forged, inactive]}, headers=H).json()
    assert [a["index"] for a in r["accepted"]] == [0] and r["rejected_indexes"] == [1, 2]
    aid = r["accepted"][0]["anomaly_id"]
    # un verdict par candidate exigé
    assert client.post(f"{base}/verdicts", json={"verdicts": []}, headers=H).status_code == 422
    v = client.post(f"{base}/verdicts", json={"verdicts": [{"anomaly_id": aid, "decision": "reject", "reason": "r", "fixable": True}]}, headers=H).json()
    assert v["revision_allowed"] is True
    assert client.post(f"{base}/candidates", json={"candidates": [good]}, headers=H).status_code == 200  # révision unique
    assert client.post(f"{base}/candidates", json={"candidates": [good]}, headers=H).status_code == 429
    t = client.post(f"{base}/tools/run_rule", json={"args": {"rule_id": "H01_consanguinity_degree_dependency"}}, headers=H).json()
    assert t["active"] is False
    assert client.post(f"{base}/finish", json={"status": "completed"}, headers=H).status_code == 200
    # l'autre dossier n'est pas terminé -> technical_failure, jamais « aucune anomalie »
    out = client.post(f"/api/qa/sessions/{sid}/close", headers=H).json()
    assert out["unfinished"] == 1
    run_dir = Path(out["run_dir"])
    res = [json.loads(l) for l in open(run_dir / "case_results.jsonl", encoding="utf-8")]
    st = {r["study_id"]: r["status"] for r in res}
    assert st[sid_narr] == "completed" and list(st.values()).count("technical_failure") == 1
    agent = [a for r in res for a in r["anomalies"] if a["produced_by"] == "agent:coherence"]
    assert len(agent) == 1 and agent[0]["action"]["kind"] == "review" and not agent[0]["simulated"]
    blob = "".join(f.read_text(encoding="utf-8") for f in run_dir.iterdir())
    for bad in ["Zorglub", "SECRET-77", "2010-04-17", "Barnabé"]:
        assert bad not in blob, bad
    man = json.loads((run_dir / "manifest.json").read_text())
    assert man["mode"] == "RAISE-B3" and man["llm"]["orchestrator"] == "external"


def test_normalizer_cannot_invent(client):
    sid, ids = _open(client)
    target = None
    for i in ids:
        v = client.get(f"/api/qa/sessions/{sid}/cases/{i}", headers=H).json()
        if "fviii_level" in v["cells_needing_normalization"]:
            target = i
    base = f"/api/qa/sessions/{sid}/cases/{target}"
    r = client.post(f"{base}/normalizations", json={"proposals": [
        {"field_id": "fviii_level", "decision": "normalized", "state": "observed", "value_type": "number", "value": 50},
        {"field_id": "sex", "decision": "normalized", "state": "observed", "value": 1},  # champ non demandé
    ]}, headers=H).json()
    # champ non demandé rejeté ; la lecture d'un texte libre reste une proposition NON validée (étiquetée comme telle)
    assert r["rejected_by_code"] == 1 and r["kept"] == ["fviii_level"]
    client.post(f"{base}/normalizations", json={"proposals": []}, headers=H)
    assert client.post(f"{base}/normalizations", json={"proposals": []}, headers=H).status_code == 429
