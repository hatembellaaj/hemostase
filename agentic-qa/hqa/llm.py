"""
Adaptateur LLM compatible avec l'API « chat completions » d'OpenAI (Ollama, vLLM, LM Studio, llama.cpp
server, ou une API autorisée).

Garde-fou : aucune donnée du registre n'est envoyée si llm.allow_registry_data != "yes".
Les sorties d'un FakeLLM portent simulated=True et sont exclues des résultats scientifiques.
"""
from __future__ import annotations

import json
import re
import time
from dataclasses import dataclass, field
from typing import Callable, Protocol

import httpx


class LLMNotConfigured(RuntimeError):
    pass


class RegistryDataNotAuthorized(RuntimeError):
    pass


class LLMTechnicalError(RuntimeError):
    """Erreur réseau, délai dépassé, réponse HTTP invalide. N'est JAMAIS une absence d'anomalie."""


class InvalidModelOutput(RuntimeError):
    """Réponse non conforme au schéma attendu."""


@dataclass
class LLMResponse:
    text: str
    tokens_in: int
    tokens_out: int
    latency_s: float
    model: str
    simulated: bool = False
    finish_reason: str | None = None   # "length" : sortie coupée par max_tokens


class LLMClient(Protocol):
    model: str
    simulated: bool

    def chat(self, system: str, user: str, *, max_tokens: int) -> LLMResponse: ...


class OpenAICompatibleClient:
    simulated = False

    def __init__(self, base_url: str, model: str, api_key: str | None = None, temperature: float = 0.0,
                 seed: int | None = None, timeout_s: float = 60, allow_registry_data: str | None = "no",
                 artificial_data: bool = False):
        if not base_url or not model:
            raise LLMNotConfigured("HQA_LLM_BASE_URL et HQA_LLM_MODEL doivent être renseignés")
        # artificial_data=True : client réservé aux données entièrement artificielles (synthetic --llm) ; l'autorisation
        # registre n'est alors pas requise. Ne jamais l'activer pour un classeur issu du registre.
        self.artificial_data = artificial_data
        if not artificial_data and str(allow_registry_data).lower() != "yes":
            raise RegistryDataNotAuthorized(
                "Envoi de données du registre non autorisé : positionner HQA_ALLOW_REGISTRY_DATA_TO_LLM=yes "
                "après confirmation de l'environnement (modèle local ou API autorisée) et de l'autorisation de traitement."
            )
        self.base_url = base_url.rstrip("/")
        self.model = model
        self.api_key = api_key
        self.temperature = temperature
        self.seed = seed
        self.timeout_s = timeout_s
        # sorties brutes conservées pour diagnostic, uniquement sur données artificielles (voir cli.cmd_run)
        self.raw_log_path = None

    def chat(self, system: str, user: str, *, max_tokens: int) -> LLMResponse:
        headers = {"Content-Type": "application/json"}
        if self.api_key:
            headers["Authorization"] = f"Bearer {self.api_key}"
        body = {
            "model": self.model,
            "messages": [{"role": "system", "content": system}, {"role": "user", "content": user}],
            "temperature": self.temperature,
            "max_tokens": max_tokens,
            "response_format": {"type": "json_object"},
        }
        if self.seed is not None:
            body["seed"] = self.seed
        t0 = time.monotonic()
        try:
            r = httpx.post(f"{self.base_url}/chat/completions", json=body, headers=headers, timeout=self.timeout_s)
        except httpx.HTTPError as e:
            raise LLMTechnicalError(f"réseau : {type(e).__name__}") from e
        if r.status_code != 200:
            raise LLMTechnicalError(f"HTTP {r.status_code}")
        try:
            data = r.json()
            text = data["choices"][0]["message"]["content"] or ""
            finish = data["choices"][0].get("finish_reason")
            usage = data.get("usage") or {}
        except (ValueError, KeyError, IndexError) as e:
            raise LLMTechnicalError("réponse HTTP non conforme") from e
        resp = LLMResponse(text=text, tokens_in=int(usage.get("prompt_tokens") or 0), tokens_out=int(usage.get("completion_tokens") or 0),
                           latency_s=time.monotonic() - t0, model=data.get("model", self.model), finish_reason=finish)
        if self.artificial_data and self.raw_log_path:
            with open(self.raw_log_path, "a", encoding="utf-8") as fh:
                fh.write(json.dumps({"role": system.split("RÔLE :")[-1].split("\n")[0].strip(), "finish_reason": finish,
                                     "tokens_in": resp.tokens_in, "tokens_out": resp.tokens_out, "text": text}, ensure_ascii=False) + "\n")
        return resp


class FakeLLM:
    """Faux modèle déterministe POUR LES TESTS LOGICIELS UNIQUEMENT. Ses sorties sont étiquetées simulées.

    responder(system, user) -> str | Exception : permet de simuler réponses valides, invalides et pannes.
    """

    simulated = True

    def __init__(self, responder: Callable[[str, str], str | Exception], model: str = "fake-llm-SIMULATED"):
        self.responder = responder
        self.model = model
        self.calls: list[tuple[str, str]] = []

    def chat(self, system: str, user: str, *, max_tokens: int) -> LLMResponse:
        self.calls.append((system, user))
        out = self.responder(system, user)
        if isinstance(out, Exception):
            raise out
        return LLMResponse(text=out, tokens_in=len(system + user) // 4, tokens_out=len(out) // 4, latency_s=0.0,
                           model=self.model, simulated=True)


def extract_json(text: str) -> dict:
    """Extrait un objet JSON ; lève InvalidModelOutput sinon (jamais d'interprétation par défaut)."""
    t = text.strip()
    t = re.sub(r"^```(?:json)?\s*|\s*```$", "", t)
    try:
        obj = json.loads(t)
    except json.JSONDecodeError:
        m = re.search(r"\{.*\}", t, re.S)
        if not m:
            raise InvalidModelOutput("aucun objet JSON")
        try:
            obj = json.loads(m.group(0))
        except json.JSONDecodeError as e:
            raise InvalidModelOutput("JSON invalide") from e
    if not isinstance(obj, dict):
        raise InvalidModelOutput("objet JSON attendu")
    return obj


@dataclass
class Budget:
    """Bornes d'exécution d'un dossier. Toute consommation est mesurée, jamais estimée."""

    max_calls: int
    max_tokens: int
    max_seconds: float
    max_technical_retries: int
    started: float = field(default_factory=time.monotonic)
    calls: int = 0
    tokens_in: int = 0
    tokens_out: int = 0
    llm_seconds: float = 0.0
    technical_errors: int = 0
    invalid_outputs: int = 0
    calls_by_role: dict = field(default_factory=dict)
    tokens_by_role: dict = field(default_factory=dict)

    def can_call(self) -> tuple[bool, str]:
        if self.calls >= self.max_calls:
            return False, "budget d'appels épuisé"
        if self.tokens_in + self.tokens_out >= self.max_tokens:
            return False, "budget de tokens épuisé"
        if time.monotonic() - self.started >= self.max_seconds:
            return False, "délai maximal atteint"
        return True, ""

    def record(self, role: str, resp: LLMResponse) -> None:
        self.calls += 1
        self.tokens_in += resp.tokens_in
        self.tokens_out += resp.tokens_out
        self.llm_seconds += resp.latency_s
        self.calls_by_role[role] = self.calls_by_role.get(role, 0) + 1
        self.tokens_by_role[role] = self.tokens_by_role.get(role, 0) + resp.tokens_in + resp.tokens_out


class BudgetExhausted(RuntimeError):
    pass


def call_json(client: LLMClient, budget: Budget, role: str, system: str, user: str, max_tokens: int,
              validate: Callable[[dict], dict]) -> tuple[dict, LLMResponse]:
    """Appel borné : réessaie les erreurs techniques (dans la limite du budget) ; une sortie invalide est
    signalée (InvalidModelOutput) et n'est jamais convertie en « pas d'anomalie »."""
    attempts = 0
    while True:
        ok, why = budget.can_call()
        if not ok:
            raise BudgetExhausted(why)
        try:
            resp = client.chat(system, user, max_tokens=max_tokens)
        except LLMTechnicalError:
            budget.technical_errors += 1
            attempts += 1
            if attempts > budget.max_technical_retries:
                raise
            continue
        budget.record(role, resp)
        try:
            if resp.finish_reason == "length":
                raise InvalidModelOutput(f"sortie tronquée (limite de {max_tokens} tokens atteinte)")
            return validate(extract_json(resp.text)), resp
        except InvalidModelOutput:
            budget.invalid_outputs += 1
            raise
        except (ValueError, TypeError, KeyError) as e:
            budget.invalid_outputs += 1
            raise InvalidModelOutput(f"schéma non respecté : {e}") from e


def make_client(cfg: dict, artificial_data: bool = False) -> LLMClient:
    c = cfg["llm"]
    return OpenAICompatibleClient(c.get("base_url"), c.get("model"), c.get("api_key"), c.get("temperature", 0.0),
                                  c.get("seed"), c.get("request_timeout_s", 60), c.get("allow_registry_data"),
                                  artificial_data=artificial_data)
