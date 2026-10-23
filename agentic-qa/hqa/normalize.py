"""Normalisation déterministe d'une cellule selon le dictionnaire. Ne complète jamais une valeur manquante."""
from __future__ import annotations

from .cases import Case, safe_value
from .dictionary import Dictionary, FieldDef
from .parsers import (
    ParseFailure,
    fold,
    is_unknown_marker,
    parse_code,
    parse_date,
    parse_duration,
    parse_number,
    parse_ratio_text,
)
from .schemas import NormalizedValue, ValueState


def _applicability_known_false(f: FieldDef, case: Case, d: Dictionary) -> bool:
    """Non applicable uniquement si la condition ET les codes dont elle dépend sont confirmés."""
    a = f.applicability
    if not a or a.status != "confirmed":
        return False
    parent = d.fields[a.field]
    if not parent.codes_confirmed():
        return False
    pv = case.cells[a.field].raw_value
    return isinstance(pv, (int, float)) and int(pv) != a.code


def normalize_cell(case: Case, d: Dictionary, field_id: str) -> NormalizedValue:
    f = d.fields[field_id]
    cell = case.cells[field_id]
    v = cell.raw_value
    src = [cell.ref]
    base = dict(field_id=field_id, sources=src, method="deterministic")
    if v is None or (isinstance(v, str) and not v.strip()):
        if _applicability_known_false(f, case, d):
            return NormalizedValue(**base, state=ValueState.NOT_APPLICABLE, justification="condition d'applicabilité confirmée non remplie")
        return NormalizedValue(**base, state=ValueState.EMPTY, justification="cellule vide (pas une anomalie en soi)")
    if is_unknown_marker(v, f.unknown_markers or None):
        return NormalizedValue(**base, state=ValueState.UNKNOWN, value_type="none",
                               justification=f"marqueur d'inconnu explicite « {v} »")
    t = f.type
    if t in ("text", "narrative"):
        return NormalizedValue(**base, state=ValueState.OBSERVED, value_type="text", value=safe_value(case, d, field_id),
                               justification="texte libre conservé (expurgé)")
    if t == "code":
        p = parse_code(v, f.allowed_values, multi=f.multi_valued)
        if isinstance(p, ParseFailure):
            variant = (f.text_variants or {}).get(fold(v)) if isinstance(v, str) else None
            issues = [p.reason] + (["variante_lexicale_non_confirmee"] if variant else [])
            if variant and variant.get("status") == "confirmed":
                return NormalizedValue(**base, state=ValueState.OBSERVED, value_type="text", value=fold(v),
                                       justification=f"variante lexicale confirmée : {variant['meaning']}")
            return NormalizedValue(**base, state=ValueState.UNRESOLVED, value_type="none", issues=issues,
                                   justification=f"valeur non interprétable comme code ({p.detail})")
        state = ValueState.UNRESOLVED if "code_non_defini" in p.issues else ValueState.OBSERVED
        return NormalizedValue(**base, state=state, value_type="code", value=p.value, issues=p.issues,
                               justification=p.justification if state == ValueState.OBSERVED else "code absent du dictionnaire")
    if t in ("number", "integer"):
        p = parse_number(v, f.unit)
        if isinstance(p, ParseFailure):
            return NormalizedValue(**base, state=ValueState.UNRESOLVED, issues=[p.reason], justification=f"nombre non lisible ({p.detail})")
        issues = list(p.issues)
        if t == "integer" and not float(p.value).is_integer():
            issues.append("entier_attendu")
        if f.unit and f.unit_status != "confirmed":
            issues.append("unite_non_confirmee")
        return NormalizedValue(**base, state=ValueState.OBSERVED, value_type="number", value=p.value, unit=p.unit,
                               comparator=p.comparator, numeric_precision=p.numeric_precision, issues=issues, justification=p.justification)
    if t == "ratio":
        p = parse_ratio_text(v)
        if isinstance(p, ParseFailure):
            return NormalizedValue(**base, state=ValueState.UNRESOLVED, issues=[p.reason], justification=f"paire non lisible ({p.detail})")
        return NormalizedValue(**base, state=ValueState.OBSERVED, value_type="ratio", value=p.value, unit=p.unit,
                               numeric_precision=p.numeric_precision, justification=p.justification)
    if t == "duration":
        p = parse_duration(v)
        if isinstance(p, ParseFailure):
            return NormalizedValue(**base, state=ValueState.UNRESOLVED, issues=[p.reason], justification=f"durée non lisible ({p.detail})")
        return NormalizedValue(**base, state=ValueState.OBSERVED, value_type="duration", value=p.value, unit="components",
                               issues=p.issues, justification=p.justification)
    if t == "date":
        p = parse_date(v)
        if isinstance(p, ParseFailure):
            return NormalizedValue(**base, state=ValueState.UNRESOLVED, issues=[p.reason], justification=f"date non lisible ({p.detail})")
        return NormalizedValue(**base, state=ValueState.OBSERVED, value_type="date", value=p.value,
                               temporal_precision=p.temporal_precision, issues=p.issues, justification=p.justification)
    return NormalizedValue(**base, state=ValueState.UNRESOLVED, justification=f"type {t} non géré")


def normalize_case(case: Case, d: Dictionary) -> dict[str, NormalizedValue]:
    return {fid: normalize_cell(case, d, fid) for fid, f in d.fields.items() if f.in_scope and f.type != "identifier"}
