"""Dictionnaire de données et règles (YAML éditables), avec contrôle des statuts d'approbation."""
from __future__ import annotations

import hashlib
from pathlib import Path
from typing import Any, Literal

import yaml
from pydantic import BaseModel, Field, model_validator

Status = Literal["confirmed", "hypothesis", "missing"]


class CodeDef(BaseModel):
    label: str
    status: Status = "hypothesis"


class Applicability(BaseModel):
    field: str
    code: int
    status: Status = "hypothesis"


class FieldDef(BaseModel):
    field_id: str
    source_col: int
    source_letter: str
    original_header: str | None
    contextual_name: str
    type: Literal["identifier", "date", "integer", "number", "code", "text", "duration", "ratio", "narrative"]
    unit: str | None = None
    unit_status: Status = "missing"
    definition_status: Status = "hypothesis"
    definition_note: str | None = None
    approved_by: str | None = None
    approved_on: str | None = None
    unknown_markers: list[str] = Field(default_factory=list)
    allowed_values: list[int] | None = None
    code_map: dict[int, CodeDef] | None = None
    text_variants: dict[str, dict] | None = None
    applicability: Applicability | None = None
    multi_valued: bool = False
    privacy_role: Literal["direct_identifier", "quasi_identifier"] | None = None
    in_scope: bool = True

    @model_validator(mode="after")
    def _approval(self):
        if self.definition_status == "confirmed" and not self.approved_by:
            raise ValueError(f"{self.field_id} : statut 'confirmed' sans approved_by")
        return self

    def codes_confirmed(self) -> bool:
        return bool(self.code_map) and self.definition_status == "confirmed" and all(c.status == "confirmed" for c in self.code_map.values())


class RuleDef(BaseModel):
    id: str
    version: str
    family: Literal["A_normalisation", "B_coherence", "C_incertitude"]
    kind: str
    status: Literal["structural", "hypothesis", "proposed_reference", "approved"]
    description: str
    deterministic: bool = True
    applies_to: Any = None
    fields: list[str] = Field(default_factory=list)
    when_filled: str | None = None
    when_filled_any: list[str] | None = None
    requires: dict | None = None
    params: dict = Field(default_factory=dict)
    reference: str | None = None
    depends_on_definitions: list[str] = Field(default_factory=list)
    approved_by: str | None = None
    approved_on: str | None = None

    @model_validator(mode="after")
    def _approval(self):
        if self.status == "approved" and not (self.approved_by and self.approved_on):
            raise ValueError(f"Règle {self.id} : 'approved' exige approved_by et approved_on")
        return self


class Dictionary(BaseModel):
    version: str
    fields: dict[str, FieldDef]
    rules: dict[str, RuleDef]
    fields_sha256: str
    rules_sha256: str

    def by_col(self) -> dict[int, FieldDef]:
        return {f.source_col: f for f in self.fields.values()}

    def active_rules(self, active_statuses: list[str]) -> list[RuleDef]:
        out = []
        for r in self.rules.values():
            if r.status not in active_statuses:
                continue
            if r.status == "approved":
                # une règle approuvée exige que ses définitions le soient aussi
                for dep in r.depends_on_definitions:
                    fid = dep.split(".")[0]
                    f = self.fields.get(fid)
                    if f is None or f.definition_status != "confirmed":
                        raise ValueError(f"Règle {r.id} approuvée mais {dep} n'est pas confirmé dans fields.yaml")
            out.append(r)
        return out


def _sha(path: Path) -> str:
    return hashlib.sha256(path.read_bytes()).hexdigest()


def load_dictionary(fields_path: str | Path, rules_path: str | Path) -> Dictionary:
    fields_path, rules_path = Path(fields_path), Path(rules_path)
    fy = yaml.safe_load(fields_path.read_text(encoding="utf-8"))
    ry = yaml.safe_load(rules_path.read_text(encoding="utf-8"))
    fields = {f["field_id"]: FieldDef(**f) for f in fy["fields"]}
    rules = {r["id"]: RuleDef(**r) for r in ry["rules"]}
    for r in rules.values():
        for fid in r.fields + ([r.when_filled] if r.when_filled else []) + (r.when_filled_any or []):
            if fid not in fields:
                raise ValueError(f"Règle {r.id} : champ inconnu {fid}")
    return Dictionary(version=fy["version"], fields=fields, rules=rules,
                      fields_sha256=_sha(fields_path), rules_sha256=_sha(rules_path))
