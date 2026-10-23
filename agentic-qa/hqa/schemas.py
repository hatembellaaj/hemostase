"""Modèles typés (Pydantic) : traçabilité des cellules, valeurs normalisées, anomalies, décisions."""
from __future__ import annotations

from datetime import datetime, timezone
from enum import Enum
from typing import Any, Literal

from pydantic import BaseModel, ConfigDict, Field


def now_iso() -> str:
    return datetime.now(timezone.utc).isoformat(timespec="seconds")


# ---------------------------------------------------------------------------
# Traçabilité
# ---------------------------------------------------------------------------
class SourceRef(BaseModel):
    """Référence exacte à une cellule du classeur source."""

    model_config = ConfigDict(frozen=True)
    file_sha256: str
    sheet: str
    row: int  # numéro de ligne Excel (1 = en-têtes)
    col: int  # numéro de colonne Excel (1 = A)
    col_letter: str
    original_header: str | None
    field_id: str  # identifiant stable (dictionnaire) ; pour une colonne sans titre : col_<lettre>

    @property
    def cell_id(self) -> str:
        return f"{self.sheet}!{self.col_letter}{self.row}"


class SourceCell(BaseModel):
    ref: SourceRef
    raw_value: Any = None  # valeur telle que lue (peut contenir un identifiant : jamais exportée telle quelle)
    raw_type: str  # str, int, float, datetime, bool, NoneType


# ---------------------------------------------------------------------------
# Normalisation
# ---------------------------------------------------------------------------
class ValueState(str, Enum):
    OBSERVED = "observed"  # valeur présente et interprétée
    UNKNOWN = "unknown"  # marqueur explicite d'inconnu (« ??? », « NP »…)
    NOT_APPLICABLE = "not_applicable"  # condition d'applicabilité APPROUVÉE non remplie
    UNRESOLVED = "unresolved"  # présente mais non interprétable avec certitude
    EMPTY = "empty"  # cellule vide : n'est pas une anomalie en soi


class Comparator(str, Enum):
    EQ = "="
    LT = "<"
    LE = "<="
    GT = ">"
    GE = ">="


class TemporalPrecision(str, Enum):
    DAY = "day"
    MONTH = "month"
    YEAR = "year"


class NormalizedValue(BaseModel):
    field_id: str
    state: ValueState
    value_type: Literal["number", "code", "date", "duration", "text", "boolean", "ratio", "none"] = "none"
    value: Any = None  # nombre, code, ISO date partielle ("2018", "2018-08", "2018-08-03"), durée en jours…
    unit: str | None = None
    comparator: Comparator | None = None
    numeric_precision: int | None = None  # nombre de décimales observées
    temporal_precision: TemporalPrecision | None = None
    justification: str  # courte
    method: Literal["deterministic", "llm", "human"] = "deterministic"
    sources: list[SourceRef]
    issues: list[str] = Field(default_factory=list)  # ex. "code_non_defini", "unite_absente"


# ---------------------------------------------------------------------------
# Anomalies candidates et décisions
# ---------------------------------------------------------------------------
class Category(str, Enum):
    NORMALIZATION = "A_normalisation"
    CONSISTENCY = "B_coherence"
    UNCERTAINTY = "C_incertitude"


class VerificationState(str, Enum):
    NOT_VERIFIED = "not_verified"
    ACCEPTED = "accepted"
    REJECTED = "rejected"
    NEEDS_REVIEW = "needs_review"
    DETERMINISTIC = "deterministic"  # issu d'une règle formelle exécutée par du code


class HumanState(str, Enum):
    PENDING = "pending"
    CONFIRMED = "confirmed_anomaly"
    ACCEPTABLE = "acceptable_variation"
    NOT_APPLICABLE = "not_applicable"
    UNRESOLVED = "unresolved"


class Evidence(BaseModel):
    ref: SourceRef
    quoted_value: str | None  # valeur citée (désidentifiée) ; vérifiée par code contre la source
    note: str | None = None


class ProposedAction(BaseModel):
    kind: Literal["review", "confirm_definition", "correct_value", "none"]
    detail: str
    proposed_value: Any = None  # jamais appliqué automatiquement


class CandidateAnomaly(BaseModel):
    anomaly_id: str
    study_id: str
    check_id: str
    rule_id: str | None
    rule_version: str | None
    rule_status: str | None  # approved / hypothesis / structural
    fields: list[str]
    category: Category
    subtype: str  # ex. parse_failure, undefined_code, dependency_violation, ratio_mismatch…
    evidence: list[Evidence]
    justification: str
    action: ProposedAction
    produced_by: str  # b0_rules / agent:coherence / single_agent…
    verification: VerificationState = VerificationState.NOT_VERIFIED
    human: HumanState = HumanState.PENDING
    simulated: bool = False  # True si produit par un faux modèle : exclu des résultats scientifiques


class DecisionLogEntry(BaseModel):
    ts: str = Field(default_factory=now_iso)
    run_id: str
    mode: str
    study_id: str
    step: int
    actor: str  # coordinator, normalizer, coherence, verifier, tool:<name>, human
    action: str
    inputs_summary: str  # désidentifié
    outcome: str
    llm_call: bool = False
    tokens_in: int | None = None
    tokens_out: int | None = None
    latency_s: float | None = None
    error: str | None = None
    simulated: bool = False


class CaseResult(BaseModel):
    """Résultat d'un système pour un dossier."""

    run_id: str
    mode: str
    study_id: str
    normalized: list[NormalizedValue]
    anomalies: list[CandidateAnomaly]
    status: Literal["completed", "stopped_budget", "stopped_no_progress", "technical_failure", "timeout"]
    llm_calls: int = 0
    tokens_in: int = 0
    tokens_out: int = 0
    latency_s: float = 0.0
    errors: list[str] = Field(default_factory=list)
    invalid_outputs: int = 0
    calls_by_role: dict[str, int] = Field(default_factory=dict)
    tokens_by_role: dict[str, int] = Field(default_factory=dict)
    simulated: bool = False
