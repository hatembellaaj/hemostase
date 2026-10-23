"""Agents et orchestration avec un FAUX modèle (sorties étiquetées simulées) : on teste le logiciel, pas l'efficacité."""
import json

import pytest

from conftest import DEV_STATUSES, base_row
from hqa.agents import Agents, Prompts
from hqa.config import ROOT
from hqa.llm import FakeLLM, LLMTechnicalError, OpenAICompatibleClient, RegistryDataNotAuthorized
from hqa.pipelines import RunContext, run_case

LIMITS = {"max_normalization_attempts": 2, "max_revisions_after_verification": 1, "max_llm_calls_per_case": 6,
          "max_tokens_per_case": 100000, "max_seconds_per_case": 60, "max_technical_retries": 2, "stop_after_steps_without_progress": 2}


def role_of(system: str) -> str:
    for r in ("normalisation", "cohérence", "vérification", "coordinateur", "agent unique"):
        if f"RÔLE : agent de {r}" in system or f"RÔLE : {r}" in system or r in system.split("RÔLE :")[1][:40]:
            return r
    return "?"


def responder(script: dict):
    """script : rôle -> liste de réponses (str ou Exception) consommées dans l'ordre."""
    def f(system, user):
        r = role_of(system)
        q = script.get(r, [])
        return q.pop(0) if q else json.dumps({"proposals": []} if r == "normalisation" else {"candidates": []} if r == "cohérence" else {"verdicts": []})
    return f


def ctx_for(mode, d, engine, fake, limits=LIMITS):
    agents = Agents(fake, Prompts(ROOT / "prompts"), d, 500, "year_month")
    return RunContext("t", mode, d, engine, limits, agents)


def test_registry_data_guard():
    with pytest.raises(RegistryDataNotAuthorized):
        OpenAICompatibleClient("http://localhost:11434/v1", "m", allow_registry_data="no")
    # données artificielles : pas d'autorisation registre requise
    assert OpenAICompatibleClient("http://localhost:11434/v1", "m", allow_registry_data="no", artificial_data=True).artificial_data


def test_real_llm_on_artificial_data_only(cfg, tmp_path, monkeypatch):
    import hqa.cli as cli

    seen = []

    def fake_make_client(c, artificial_data=False):
        seen.append(artificial_data)
        return FakeLLM(lambda s, u: '{"proposals": [], "candidates": [], "verdicts": [], "action": "final"}')

    monkeypatch.setattr(cli, "ROOT", tmp_path)
    monkeypatch.setattr(cli, "make_client", fake_make_client)
    (tmp_path / "examples").mkdir()
    cli.cmd_synthetic(type("A", (), {"rows": 10, "seed": 1, "simulate_llm": False, "llm": True, "modes": ["B3"]})(), cfg)
    assert seen == [True]
    # un autre classeur que le classeur artificiel généré est refusé avant tout appel
    import argparse
    ns = argparse.Namespace(mode="B3", subset="all", workbook=str(tmp_path / "examples" / "synthetic_registre.xlsx"),
                            enable_hypothesis_rules=False, coordinator="policy", prompt_version="v1", artificial_sha256="0" * 64)
    with pytest.raises(SystemExit):
        cli.cmd_run(ns, cfg)
    assert seen == [True]


def test_invalid_json_is_not_absence_of_anomaly(mini, d):
    m = mini([base_row(comment_1="il ne saigne pas")])
    fake = FakeLLM(responder({"cohérence": ["pas du json"]}))
    r = run_case(ctx_for("B3", d, m["engine"], fake), m["cases"][0])
    assert r.status == "technical_failure" and r.invalid_outputs == 1 and r.simulated


def test_api_failure_retried_then_failure(mini, d):
    m = mini([base_row(comment_1="x")])
    fake = FakeLLM(lambda s, u: LLMTechnicalError("HTTP 503"))
    r = run_case(ctx_for("B2", d, m["engine"], fake), m["cases"][0])
    assert r.status == "technical_failure"
    assert len(fake.calls) == 1 + LIMITS["max_technical_retries"]


def test_call_budget_is_enforced(mini, d):
    m = mini([base_row(comment_1="x")])
    limits = dict(LIMITS, max_llm_calls_per_case=1)
    tool_call = json.dumps({"action": "tool", "tool": "get_dictionary_entry", "args": {"field_id": "severity"}})
    fake = FakeLLM(lambda s, u: tool_call)  # l'agent unique demande des outils sans fin
    r = run_case(ctx_for("B1", d, m["engine"], fake, limits), m["cases"][0])
    assert r.status == "stopped_budget" and r.llm_calls == 1


def test_b3_routes_adaptively_b2_does_not(mini, d):
    m = mini([base_row(), base_row(patient_name="B", comment_1="commentaire")])
    calls_b3, calls_b2 = [], []
    for mode, store in (("B3", calls_b3), ("B2", calls_b2)):
        for c in m["cases"]:
            fake = FakeLLM(responder({}))
            run_case(ctx_for(mode, d, m["engine"], fake), c)
            store.append([role_of(s) for s, _ in fake.calls])
    assert calls_b3[0] == []  # rien à normaliser, pas de commentaire : aucun appel
    assert calls_b3[1] == ["cohérence"]  # commentaire présent : cohérence seulement
    assert calls_b2[0] == ["normalisation", "cohérence"] == calls_b2[1][:2]  # workflow fixe


def test_b3_normalizes_only_unresolved_and_respects_attempt_limit(mini, d):
    m = mini([base_row(age_first_signs="asymptomatique")])
    amb = json.dumps({"proposals": []})  # aucune proposition : pas de progrès
    fake = FakeLLM(responder({"normalisation": [amb, amb, amb]}))
    run_case(ctx_for("B3", d, m["engine"], fake), m["cases"][0])
    norm_calls = [role_of(s) for s, _ in fake.calls].count("normalisation")
    assert norm_calls == LIMITS["max_normalization_attempts"]
    sent = json.loads(fake.calls[0][1])
    assert list(sent["cells"]) == ["age_first_signs"]


def test_evidence_must_match_source_and_rule_must_be_active(mini, d):
    m = mini([base_row(comment_1="hémarthroses répétées")])
    good = {"rule_id": "N01_narrative_vs_structured", "fields": ["comment_1", "hemarthrosis"], "category": "B_coherence", "subtype": "x",
            "evidence": [{"field_id": "comment_1", "quoted_value": "hémarthroses répétées"}], "justification": "j", "action": {"kind": "review", "detail": ""}}
    forged = dict(good, evidence=[{"field_id": "comment_1", "quoted_value": "texte inventé"}])
    inactive = dict(good, rule_id="H01_consanguinity_degree_dependency")
    correction = dict(good, action={"kind": "correct_value", "detail": "mettre 1"})
    fake = FakeLLM(responder({"cohérence": [json.dumps({"candidates": [good, forged, inactive, correction]})],
                              "vérification": [json.dumps({"verdicts": [{"candidate_index": 0, "decision": "needs_review", "reason": "périodes"},
                                                                         {"candidate_index": 1, "decision": "accept", "reason": ""}]})]}))
    engine = type(m["engine"])(d, ["structural", "approved"])
    r = run_case(ctx_for("B3", d, engine, fake), m["cases"][0])
    agent = [a for a in r.anomalies if a.produced_by == "agent:coherence"]
    assert len(agent) == 2  # preuve falsifiée et règle inactive rejetées
    assert all(a.action.kind == "review" for a in agent)  # pas de correction automatique
    assert agent[0].verification.value == "needs_review" and all(a.simulated for a in agent)


def test_normalizer_cannot_invent_precision_or_drop_censoring(mini, d):
    from hqa.agents import validate_proposals
    m = mini([base_row(ddn=2024, fviii_level="<1%", fix_level=None)])
    c = m["cases"][0]
    obj = {"proposals": [
        {"field_id": "ddn", "decision": "normalized", "state": "observed", "value_type": "date", "value": "2024-01-01", "temporal_precision": "day"},
        {"field_id": "fviii_level", "decision": "normalized", "state": "observed", "value_type": "number", "value": 1, "comparator": "="},
        {"field_id": "fix_level", "decision": "normalized", "state": "observed", "value_type": "number", "value": 3},
        {"field_id": "severity", "decision": "ambiguous", "ambiguity_reason": "code_non_defini"},
    ]}
    kept, amb, rejected = validate_proposals(obj, ["ddn", "fviii_level", "fix_level", "severity"], c, d)
    assert kept == [] and rejected == 3 and amb[0]["reason"] == "code_non_defini"


def test_verifier_rejection_triggers_single_revision(mini, d):
    m = mini([base_row(comment_1="hémarthroses répétées")])
    cand = {"rule_id": "N01_narrative_vs_structured", "fields": ["comment_1"], "category": "B_coherence", "subtype": "x",
            "evidence": [{"field_id": "comment_1", "quoted_value": "hémarthroses répétées"}], "justification": "j", "action": {"kind": "review", "detail": ""}}
    reject = json.dumps({"verdicts": [{"candidate_index": 0, "decision": "reject", "reason": "preuve faible", "fixable": True}]})
    fake = FakeLLM(responder({"cohérence": [json.dumps({"candidates": [cand]})] * 3, "vérification": [reject] * 3}))
    engine = type(m["engine"])(d, ["structural", "approved"])
    r = run_case(ctx_for("B3", d, engine, fake), m["cases"][0])
    roles = [role_of(s) for s, _ in fake.calls]
    assert roles == ["cohérence", "vérification", "cohérence", "vérification"]  # une seule révision
    assert r.llm_calls <= LIMITS["max_llm_calls_per_case"]


def test_ablation_without_verifier(mini, d):
    m = mini([base_row(comment_1="hémarthroses répétées")])
    cand = {"rule_id": "N01_narrative_vs_structured", "fields": ["comment_1"], "category": "B_coherence", "subtype": "x",
            "evidence": [{"field_id": "comment_1", "quoted_value": "hémarthroses répétées"}], "justification": "j", "action": {"kind": "review", "detail": ""}}
    fake = FakeLLM(responder({"cohérence": [json.dumps({"candidates": [cand]})]}))
    r = run_case(ctx_for("B3-noverif", d, type(m["engine"])(d, ["structural"]), fake), m["cases"][0])
    assert "vérification" not in [role_of(s) for s, _ in fake.calls]
    assert [a.verification.value for a in r.anomalies if a.produced_by.startswith("agent")] == ["not_verified"]
