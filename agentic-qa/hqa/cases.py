"""Dossiers d'étude (une ligne = un dossier ; aucune fusion) et vues désidentifiées."""
from __future__ import annotations

from dataclasses import dataclass, field
from datetime import datetime

from .dictionary import Dictionary
from .parsers import fold
from .privacy import Pseudonymizer, name_tokens, redact, residual_identifier_flags
from .schemas import SourceCell
from .workbook import Workbook

ID_PLACEHOLDER = "[identifiant]"


@dataclass
class Case:
    study_id: str
    excel_row: int
    cells: dict[str, SourceCell]  # field_id -> cellule (champs du dictionnaire uniquement)
    name_key: str  # clé de nom hachée (jamais le nom)
    redacted_text: dict[str, str] = field(default_factory=dict)  # field_id -> texte expurgé
    residual_flags: dict[str, list[str]] = field(default_factory=dict)


def safe_value(case: Case, d: Dictionary, field_id: str) -> str | None:
    """Valeur exportable/envoyable : identifiants masqués, textes expurgés."""
    f = d.fields[field_id]
    v = case.cells[field_id].raw_value
    if v is None:
        return None
    if f.privacy_role == "direct_identifier" or f.type == "identifier":
        return ID_PLACEHOLDER
    if field_id in case.redacted_text:
        return case.redacted_text[field_id]
    if isinstance(v, datetime):
        return v.date().isoformat()
    return str(v)


def build_cases(wb: Workbook, d: Dictionary, pseudo: Pseudonymizer) -> list[Case]:
    by_col = d.by_col()
    name_col = next(f.source_col for f in d.fields.values() if f.field_id == "patient_name")
    file_col = next((f.source_col for f in d.fields.values() if f.field_id == "file_number"), None)
    mapping = pseudo.assign(wb, name_col, file_col)
    tokens = name_tokens([str(wb.cell(r, name_col).raw_value or "") for r in wb.data_rows])
    cases = []
    for r in wb.data_rows:
        cells = {f.field_id: wb.cell(r, col) for col, f in by_col.items()}
        c = Case(study_id=mapping[r], excel_row=r, cells=cells,
                 name_key=pseudo.name_key(str(wb.cell(r, name_col).raw_value or "")))
        for fid, f in d.fields.items():
            v = cells[fid].raw_value
            if f.type in ("text", "narrative") and isinstance(v, str):
                red, _ = redact(v, tokens)
                c.redacted_text[fid] = red
                fl = residual_identifier_flags(red)
                if fl:
                    c.residual_flags[fid] = fl
        cases.append(c)
    return sorted(cases, key=lambda c: c.study_id)


def llm_view(case: Case, d: Dictionary, normalized: dict | None = None, date_generalization: str = "year_month") -> dict:
    """Ce que voit un modèle : identifiant d'étude, champs du périmètre, sans identifiant direct,
    dates généralisées au mois, commentaires expurgés."""
    fields = {}
    for fid, f in d.fields.items():
        if not f.in_scope or f.type == "identifier":
            continue
        v = safe_value(case, d, fid)
        if v is not None and f.type == "date" and date_generalization == "year_month" and len(v) == 10:
            v = v[:7] + " (jour masqué)"
        entry = {"name": f.contextual_name, "header": f.original_header, "column": f.source_letter, "value": v}
        if normalized and fid in normalized:
            n = normalized[fid]
            entry["normalized"] = {"state": n.state.value, "type": n.value_type, "comparator": n.comparator.value if n.comparator else None,
                                   "unit": n.unit, "precision": n.temporal_precision.value if n.temporal_precision else n.numeric_precision,
                                   "issues": n.issues}
            if f.type == "date" and n.value and date_generalization == "year_month":
                entry["normalized"]["value"] = str(n.value)[:7]
            elif f.type not in ("text", "narrative"):
                entry["normalized"]["value"] = n.value
        fields[fid] = entry
    return {"study_id": case.study_id, "fields": fields}


def same_name_groups(cases: list[Case]) -> list[list[str]]:
    groups: dict[str, list[str]] = {}
    for c in cases:
        groups.setdefault(c.name_key, []).append(c.study_id)
    return [sorted(g) for g in groups.values() if len(g) > 1]


def fold_key(s: str) -> str:
    return fold(s)
