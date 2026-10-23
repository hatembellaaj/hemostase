#!/usr/bin/env python3
"""
Kit RAISE pour le contrôle qualité multi-agent (Maître/Esclaves) via la passerelle QA.

  python raise/raise_kit.py build
      -> raise/dist/ : prompts système assemblés (à coller dans RAISE) + toolbox_tools.json
  python raise/raise_kit.py register --raise-url https://raise.example.org --auth "Bearer <jeton admin>" \
         --hqa-url http://<hôte>:8095 --hqa-key <HQA_API_KEY>
      -> crée les outils Toolbox et les secrets globaux HQA_API_URL / HQA_API_KEY dans RAISE
  python raise/raise_kit.py run --system RAISE-B3 --subset dev --master <id assistant Coordinateur> ...
      -> une session de la passerelle, une conversation RAISE par dossier, coûts relevés, session close

Dépendances : bibliothèque standard uniquement (exécutable hors du venv du projet).
"""
from __future__ import annotations

import argparse
import json
import os
import sys
import time
import urllib.error
import urllib.request
from pathlib import Path

HERE = Path(__file__).resolve().parent
PROMPTS = HERE / "prompts"
DIST = HERE / "dist"
COMMON_MARK = "[COLLER ICI 00_regles_communes.md]"

# ------------------------------------------------------------------------------------------ outils Toolbox
# Paramètres UNIQUEMENT de type string : le lanceur Python de la Toolbox RAISE insère input_data avec json.dumps dans
# du code Python ; null / true / false y deviennent des noms Python invalides. Les charges utiles complexes passent donc
# par une chaîne JSON (payload_json) décodée par le script.
_PRELUDE = r'''
import json, os, urllib.request, urllib.error, urllib.parse
_BASE = os.environ["HQA_API_URL"].rstrip("/") + "/api/qa"
_CONSIGNE = "ERREUR technique ou refus : ne JAMAIS conclure a l'absence d'anomalie ; signaler et ne pas inventer."
def _q(x):
    return urllib.parse.quote(str(x).strip(), safe="")
def _call(method, path, body=None):
    req = urllib.request.Request(_BASE + path, method=method,
        data=None if body is None else json.dumps(body).encode("utf-8"),
        headers={"X-API-Key": os.environ["HQA_API_KEY"], "Content-Type": "application/json"})
    try:
        with urllib.request.urlopen(req, timeout=60) as r:
            txt = r.read().decode("utf-8")
            return json.loads(txt) if txt else {"ERREUR": "reponse vide", "consigne": _CONSIGNE}
    except urllib.error.HTTPError as e:
        return {"ERREUR": "HTTP %d" % e.code, "detail": e.read().decode("utf-8", "replace")[:800], "consigne": _CONSIGNE}
    except Exception as e:
        return {"ERREUR": type(e).__name__, "detail": str(e)[:300], "consigne": _CONSIGNE}
def _payload(key, role):
    try:
        return json.loads(input_data.get(key) or "")
    except ValueError as e:
        _call("POST", "/sessions/%s/cases/%s/invalid-output" % (_q(input_data["session_id"]), _q(input_data["study_id"])),
              {"role": role, "detail": "JSON invalide dans %s : %s" % (key, e)})
        return None
_S = "/sessions/%s/cases/%s" % (_q(input_data.get("session_id", "")), _q(input_data.get("study_id", "")))
'''

_IDS = {"session_id": {"type": "string", "description": "Identifiant de session (SESSION=… dans la tâche)"},
        "study_id": {"type": "string", "description": "Identifiant d'étude du dossier (DOSSIER=…, ex. S012)"}}

TOOLS = [
    {"name": "qa_lire_dossier", "display_name": "QA — Lire le dossier (pseudonymisé)",
     "description": "Renvoie la vue pseudonymisée d'un dossier : valeurs et normalisation par champ, cellules à normaliser, "
                    "présence de commentaires, anomalies déterministes déjà trouvées, candidates d'agents et vérification, "
                    "règles actives, budget restant. À appeler en premier.",
     "props": {}, "body": 'output_data = _call("GET", _S)'},
    {"name": "qa_outil_deterministe", "display_name": "QA — Outil déterministe",
     "description": "Exécute un outil déterministe sur le dossier. tool parmi : get_dictionary_entry {field_id}, "
                    "parse_value {field_id, text}, compare_numbers {a, op, b}, duration_to_months {years, months, days}, "
                    "date_interval {date}, list_relations {field_id}, run_rule {rule_id}. args_json = arguments en JSON.",
     "props": {"tool": {"type": "string", "description": "nom de l'outil"},
               "args_json": {"type": "string", "description": 'arguments JSON, ex. {"field_id": "fviii_level"}'},
               "role": {"type": "string", "description": "normalizer | coherence | verifier | coordinator | single_agent"}},
     "body": 'a = _payload("args_json", input_data.get("role") or "coherence")\n'
             'output_data = _call("POST", _S + "/tools/" + _q(input_data["tool"]), {"args": a, "role": input_data.get("role") or "coherence"}) '
             'if a is not None else {"ERREUR": "args_json invalide", "consigne": _CONSIGNE}'},
    {"name": "qa_soumettre_normalisations", "display_name": "QA — Soumettre des normalisations",
     "description": 'Soumet les propositions de normalisation (payload_json = {"proposals": [...]}) ; la passerelle les valide '
                    "et renvoie kept / ambiguous / rejected_by_code. Maximum 2 soumissions par dossier.",
     "props": {"payload_json": {"type": "string", "description": "objet JSON {\"proposals\": [...]} sérialisé"}},
     "body": 'p = _payload("payload_json", "normalizer")\n'
             'output_data = _call("POST", _S + "/normalizations", p) if p is not None else {"ERREUR": "payload_json invalide", "consigne": _CONSIGNE}'},
    {"name": "qa_soumettre_candidates", "display_name": "QA — Soumettre des anomalies candidates",
     "description": 'Soumet les anomalies candidates avec preuves (payload_json = {"candidates": [...]}). Chaque soumission '
                    "remplace la précédente ; la passerelle rejette règle inactive, champ inconnu, citation non conforme.",
     "props": {"payload_json": {"type": "string", "description": "objet JSON {\"candidates\": [...]} sérialisé"}},
     "body": 'p = _payload("payload_json", "coherence")\n'
             'output_data = _call("POST", _S + "/candidates", p) if p is not None else {"ERREUR": "payload_json invalide", "consigne": _CONSIGNE}'},
    {"name": "qa_soumettre_verdicts", "display_name": "QA — Soumettre des verdicts de vérification",
     "description": 'Soumet un verdict par candidate d\'agent (payload_json = {"verdicts": [{anomaly_id, decision, reason, fixable}]}).',
     "props": {"payload_json": {"type": "string", "description": "objet JSON {\"verdicts\": [...]} sérialisé"}},
     "body": 'p = _payload("payload_json", "verifier")\n'
             'output_data = _call("POST", _S + "/verdicts", p) if p is not None else {"ERREUR": "payload_json invalide", "consigne": _CONSIGNE}'},
    {"name": "qa_signaler_sortie_invalide", "display_name": "QA — Signaler une sortie invalide",
     "description": "Enregistre qu'un agent délégué n'a pas répondu ou a renvoyé une sortie inexploitable (jamais une absence d'anomalie).",
     "props": {"role": {"type": "string", "description": "normalizer | coherence | verifier"},
               "detail": {"type": "string", "description": "une phrase"}},
     "body": 'output_data = _call("POST", _S + "/invalid-output", {"role": input_data.get("role") or "?", "detail": input_data.get("detail") or ""})'},
    {"name": "qa_terminer_dossier", "display_name": "QA — Terminer le dossier",
     "description": "Clôt le traitement du dossier. status : completed | stopped_budget | stopped_no_progress | technical_failure.",
     "props": {"status": {"type": "string", "description": "completed | stopped_budget | stopped_no_progress | technical_failure"},
               "note": {"type": "string", "description": "une phrase"}},
     "body": 'output_data = _call("POST", _S + "/finish", {"status": input_data.get("status") or "technical_failure", "note": input_data.get("note") or ""})'},
]

REQUIRED = {"qa_outil_deterministe": ["tool", "args_json"], "qa_soumettre_normalisations": ["payload_json"],
            "qa_soumettre_candidates": ["payload_json"], "qa_soumettre_verdicts": ["payload_json"],
            "qa_signaler_sortie_invalide": ["role", "detail"], "qa_terminer_dossier": ["status"]}

ASSISTANTS = {
    "QA Hémophilie — Coordinateur (B3)": {"slug": "coordinateur", "prompt": "10_maitre_coordinateur.md", "delegates": ["Normalisation", "Cohérence", "Vérification"],
                                          "tools": ["qa_lire_dossier", "qa_terminer_dossier", "qa_signaler_sortie_invalide"]},
    "QA Hémophilie — Coordinateur sans vérificateur (B3-noverif)": {"slug": "coordinateur_noverif", "prompt": "10_maitre_coordinateur.md", "delegates": ["Normalisation", "Cohérence"],
                                          "tools": ["qa_lire_dossier", "qa_terminer_dossier", "qa_signaler_sortie_invalide"]},
    "QA Hémophilie — Normalisation": {"slug": "normalisation", "prompt": "20_esclave_normalisation.md", "delegates": [],
                                      "tools": ["qa_lire_dossier", "qa_outil_deterministe", "qa_soumettre_normalisations", "qa_signaler_sortie_invalide"]},
    "QA Hémophilie — Cohérence": {"slug": "coherence", "prompt": "30_esclave_coherence.md", "delegates": [],
                                  "tools": ["qa_lire_dossier", "qa_outil_deterministe", "qa_soumettre_candidates", "qa_signaler_sortie_invalide"]},
    "QA Hémophilie — Vérification": {"slug": "verification", "prompt": "40_esclave_verification.md", "delegates": [],
                                     "tools": ["qa_lire_dossier", "qa_outil_deterministe", "qa_soumettre_verdicts", "qa_signaler_sortie_invalide"]},
    "QA Hémophilie — Agent unique (B1)": {"slug": "agent_unique", "prompt": "50_agent_unique_B1.md", "delegates": [], "tools": [t["name"] for t in TOOLS]},
}


def toolbox_definitions() -> list[dict]:
    out = []
    for t in TOOLS:
        props = {**_IDS, **t["props"]}
        out.append({"name": t["name"], "display_name": t["display_name"], "description": t["description"], "script_type": "python",
                    "script_content": _PRELUDE.strip() + "\n" + t["body"] + "\n",
                    "input_schema": {"type": "object", "properties": props, "required": ["session_id", "study_id"] + REQUIRED.get(t["name"], [])},
                    "timeout_seconds": 60, "is_enabled": True})
    return out


def assembled_prompt(name: str) -> str:
    common = (PROMPTS / "00_regles_communes.md").read_text(encoding="utf-8")
    common = "\n".join(l for l in common.splitlines() if not l.startswith("<!--"))
    text = (PROMPTS / name).read_text(encoding="utf-8")
    body = text.split("-->", 1)[1] if text.startswith("<!--") else text
    return body.replace(COMMON_MARK, common.strip()).strip() + "\n"


def cmd_build(_):
    DIST.mkdir(exist_ok=True)
    (DIST / "toolbox_tools.json").write_text(json.dumps(toolbox_definitions(), indent=1, ensure_ascii=False), encoding="utf-8")
    for name, a in ASSISTANTS.items():
        txt = assembled_prompt(a["prompt"])
        head, rest = (txt.split("<<VERIF>>", 1) + [""])[:2]
        if rest:
            verif, tail = rest.split("<</VERIF>>", 1)
            txt = head + (("Étape 3 — Pas de vérification dans cette configuration (ablation) : aucune révision ; passe à l'étape 5.\n")
                          if "noverif" in name else verif.lstrip("\n")) + tail.lstrip("\n")
        (DIST / f"prompt_{a['slug']}.txt").write_text(txt, encoding="utf-8")
    (DIST / "assistants.json").write_text(json.dumps(ASSISTANTS, indent=1, ensure_ascii=False), encoding="utf-8")
    for t in toolbox_definitions():  # contrôle : chaque script est du Python valide
        compile("input_data = {}\noutput_data = {}\n" + t["script_content"], t["name"], "exec")
    print(f"{len(TOOLS)} outils, {len(ASSISTANTS)} prompts -> {DIST}")


# ------------------------------------------------------------------------------------------ HTTP
def _http(method: str, url: str, headers: dict, body: dict | None = None, timeout: float = 120):
    req = urllib.request.Request(url, method=method, data=None if body is None else json.dumps(body).encode(),
                                 headers={"Content-Type": "application/json", **headers})
    try:
        with urllib.request.urlopen(req, timeout=timeout) as r:
            t = r.read().decode()
            return r.status, (json.loads(t) if t else {})
    except urllib.error.HTTPError as e:
        return e.code, {"error": e.read().decode("utf-8", "replace")[:1000]}
    except Exception as e:  # réseau, délai
        return 0, {"error": f"{type(e).__name__}: {e}"}


def _auth(value: str) -> dict:
    # « Bearer <jeton> » -> Authorization ; sinon clé API RAISE dans X-API-Key
    return {"Authorization": value} if value.lower().startswith("bearer ") else {"X-API-Key": value}


def cmd_register(a):
    h = _auth(a.auth)
    base = a.raise_url.rstrip("/")
    ids = {}
    for t in toolbox_definitions():
        st, r = _http("POST", f"{base}/v2/toolbox/tools", h, t)
        ids[t["name"]] = r.get("id") if st in (200, 201) else None
        print(f"{t['name']}: HTTP {st} {r.get('id') or r.get('error', '')[:200]}")
    for name, value in (("HQA_API_URL", a.hqa_url), ("HQA_API_KEY", a.hqa_key)):
        st, r = _http("POST", f"{base}/v2/toolbox/secrets", h, {"name": name, "secret_type": "env_var", "value": value, "tool_id": None})
        print(f"secret {name}: HTTP {st}")
    (DIST / "toolbox_ids.json").parent.mkdir(exist_ok=True)
    (DIST / "toolbox_ids.json").write_text(json.dumps(ids, indent=1), encoding="utf-8")
    print("Identifiants -> raise/dist/toolbox_ids.json (à utiliser dans toolbox_config.selected_tool_ids de chaque assistant)")


def _cost(ct) -> dict:
    """Extrait ce qui est numérique de cost_tracking (structure propre à RAISE) ; absent = non mesuré."""
    if not isinstance(ct, dict):
        return {}
    flat = {}
    def walk(d, pre=""):
        for k, v in d.items():
            if isinstance(v, dict):
                walk(v, f"{pre}{k}.")
            elif isinstance(v, (int, float)):
                flat[f"{pre}{k}"] = v
    walk(ct)
    pick = lambda *names: next((flat[k] for k in flat for n in names if k.endswith(n)), None)  # noqa: E731
    out = {"tokens_in": pick("input_tokens", "prompt_tokens", "tokens_in"), "tokens_out": pick("output_tokens", "completion_tokens", "tokens_out"),
           "cost": pick("total_cost", "cost")}
    return {k: v for k, v in out.items() if v is not None}


def _invoke(base, h, assistant_id, message, label, timeout):
    st, th = _http("POST", f"{base}/v2/threads", h, {"name": label, "assistant_id": assistant_id})
    if st not in (200, 201):
        return None, f"création de conversation : HTTP {st} {th.get('error', '')[:200]}", {}
    t0 = time.monotonic()
    st, r = _http("POST", f"{base}/v2/interact/invocation", h, {"assistant_id": assistant_id, "thread_id": th["id"], "message": message}, timeout)
    lat = time.monotonic() - t0
    if st != 200:
        return None, f"invocation : HTTP {st} {r.get('error', '')[:200]}", {"latency_s": lat}
    return r.get("llm_output", ""), None, {**_cost(r.get("cost_tracking")), "latency_s": lat}


def cmd_run(a):
    hq = {"X-API-Key": a.hqa_key or os.environ.get("HQA_API_KEY", "")}
    qa = a.hqa_url.rstrip("/") + "/api/qa"
    rh = _auth(a.raise_auth)
    rb = a.raise_url.rstrip("/")
    st, s = _http("POST", f"{qa}/sessions", hq, {"system_label": a.system, "subset": a.subset, "model": a.model,
                                                  "prompt_version": a.prompt_version, "max_cases": a.limit or None,
                                                  "normalization_scope": "all_nonempty" if a.system.endswith("B2") else "needed"})
    if st != 200:
        sys.exit(f"session refusée : HTTP {st} {s}")
    sid = s["session_id"]
    st, cases = _http("GET", f"{qa}/sessions/{sid}/cases", hq)
    ids = [c["study_id"] for c in cases]
    print(f"session {sid} : {len(ids)} dossiers")
    for i, study in enumerate(ids, 1):
        msg = f"SESSION={sid} DOSSIER={study}"
        cost, err = {}, None
        if a.system.endswith("B2"):
            # workflow FIXE, sans coordinateur : normalisation -> cohérence -> vérification, un seul passage
            steps = [(a.normalizer, f"{msg} Normalise toutes les cellules listées dans cells_needing_normalization."),
                     (a.coherence, f"{msg} Recherche les incompatibilités non couvertes par les anomalies déterministes."),
                     (a.verifier, f"{msg} Vérifie chacune des candidates d'agent (anomaly_id) du dossier.")]
            for aid, m in steps:
                out, err, c = _invoke(rb, rh, aid, m, f"{sid}-{study}", a.timeout)
                for k, v in c.items():
                    cost[k] = cost.get(k, 0) + v
                if err:
                    break
            _http("POST", f"{qa}/sessions/{sid}/cases/{study}/finish", hq,
                  {"status": "technical_failure" if err else "completed", "note": err or "workflow fixe"})
        else:
            agent = a.master if a.system.startswith("RAISE-B3") else a.single
            out, err, cost = _invoke(rb, rh, agent, msg, f"{sid}-{study}", a.timeout)
        # dossier non clos par l'agent => échec technique (jamais « aucune anomalie »)
        _, cur = _http("GET", f"{qa}/sessions/{sid}/cases", hq)
        status = next((c["status"] for c in cur if c["study_id"] == study), "pending")
        if status == "pending":
            status = "technical_failure"
            _http("POST", f"{qa}/sessions/{sid}/cases/{study}/finish", hq,
                  {"status": status, "note": (err or "l'agent n'a pas terminé le dossier")[:200]})
        if cost:
            _http("POST", f"{qa}/sessions/{sid}/cases/{study}/cost", hq, cost)
        print(f"[{i}/{len(ids)}] {study} {status}{' — ' + err if err else ''}")
    st, r = _http("POST", f"{qa}/sessions/{sid}/close", hq)
    print(json.dumps(r, indent=1, ensure_ascii=False))


def main(argv=None):
    p = argparse.ArgumentParser(description=__doc__, formatter_class=argparse.RawDescriptionHelpFormatter)
    sp = p.add_subparsers(dest="cmd", required=True)
    sp.add_parser("build")
    r = sp.add_parser("register")
    r.add_argument("--raise-url", required=True)
    r.add_argument("--auth", required=True, help='"Bearer <jeton admin>" ou clé API RAISE')
    r.add_argument("--hqa-url", required=True, help="URL de la passerelle vue DEPUIS le backend RAISE, ex. http://10.0.0.5:8095")
    r.add_argument("--hqa-key", required=True)
    x = sp.add_parser("run")
    x.add_argument("--system", required=True, choices=["RAISE-B1", "RAISE-B2", "RAISE-B3", "RAISE-B3-noverif"])
    x.add_argument("--subset", default="dev", choices=["dev", "test", "all"])
    x.add_argument("--hqa-url", default="http://127.0.0.1:8095")
    x.add_argument("--hqa-key", default=None)
    x.add_argument("--raise-url", required=True)
    x.add_argument("--raise-auth", required=True)
    x.add_argument("--master", help="id de l'assistant Coordinateur (B3 ou B3-noverif)")
    x.add_argument("--single", help="id de l'assistant unique (B1)")
    x.add_argument("--normalizer"), x.add_argument("--coherence"), x.add_argument("--verifier")
    x.add_argument("--model", help="ex. azure/gpt-4o-prod (consigné dans le manifeste)")
    x.add_argument("--prompt-version", default="raise-v1")
    x.add_argument("--limit", type=int, default=0, help="nombre de dossiers (0 = tous) ; utile pour un essai")
    x.add_argument("--timeout", type=float, default=600)
    a = p.parse_args(argv)
    {"build": cmd_build, "register": cmd_register, "run": cmd_run}[a.cmd](a)


if __name__ == "__main__":
    main()
