"""Outils déterministes typés exposés aux agents (mêmes outils pour B1, B2, B3)."""
from __future__ import annotations

from typing import Any

from .cases import Case, safe_value
from .dictionary import Dictionary
from .parsers import ParseFailure, date_bounds, duration_months_exact, parse_code, parse_date, parse_duration, parse_number, parse_ratio_text
from .rules import RuleEngine
from .schemas import NormalizedValue

TOOL_SPECS = {
    "get_dictionary_entry": "args: {field_id} -> définition, type, unité et statut, codes et statut, applicabilité",
    "parse_value": "args: {field_id, text} -> analyse déterministe du texte selon le type du champ",
    "compare_numbers": "args: {a, op, b} -> booléen (op parmi < <= = >= >)",
    "duration_to_months": "args: {years, months, days} -> mois exacts ou null si jours présents",
    "date_interval": "args: {date} (AAAA, AAAA-MM, AAAA-MM-JJ) -> intervalle [début, fin]",
    "list_relations": "args: {field_id} -> règles mentionnant le champ, avec statut et activité",
    "run_rule": "args: {rule_id} -> résultat de la règle sur ce dossier (si active)",
}


class ToolBox:
    def __init__(self, case: Case, d: Dictionary, norm: dict[str, NormalizedValue], engine: RuleEngine):
        self.case, self.d, self.norm, self.engine = case, d, norm, engine
        self.log: list[dict] = []

    def call(self, name: str, args: dict) -> dict:
        fn = getattr(self, f"t_{name}", None)
        if fn is None or name not in TOOL_SPECS:
            res = {"error": f"outil inconnu : {name}"}
        else:
            try:
                res = fn(**(args or {}))
            except TypeError as e:
                res = {"error": f"arguments invalides : {e}"}
        self.log.append({"tool": name, "args": args, "ok": "error" not in res})
        return res

    def t_get_dictionary_entry(self, field_id: str) -> dict:
        f = self.d.fields.get(field_id)
        if not f or f.type == "identifier":
            return {"error": "champ inconnu ou non accessible"}
        return {
            "field_id": f.field_id, "name": f.contextual_name, "header": f.original_header, "column": f.source_letter,
            "type": f.type, "unit": f.unit, "unit_status": f.unit_status, "definition_status": f.definition_status,
            "definition_note": f.definition_note,
            "codes": {str(k): {"label": c.label, "status": c.status} for k, c in (f.code_map or {}).items()},
            "applicability": f.applicability.model_dump() if f.applicability else None,
            "unknown_markers": f.unknown_markers,
        }

    def t_parse_value(self, field_id: str, text: Any) -> dict:
        f = self.d.fields.get(field_id)
        if not f:
            return {"error": "champ inconnu"}
        p = {"date": parse_date, "duration": parse_duration, "ratio": parse_ratio_text}.get(f.type)
        if f.type in ("number", "integer"):
            r = parse_number(text, f.unit)
        elif f.type == "code":
            r = parse_code(text, f.allowed_values, f.multi_valued)
        elif p:
            r = p(text)
        else:
            return {"result": "texte libre", "value": str(text)}
        if isinstance(r, ParseFailure):
            return {"parsed": False, "reason": r.reason, "detail": r.detail}
        return {"parsed": True, "value_type": r.value_type, "value": r.value, "unit": r.unit,
                "comparator": r.comparator.value if r.comparator else None,
                "temporal_precision": r.temporal_precision.value if r.temporal_precision else None, "issues": r.issues}

    def t_compare_numbers(self, a: float, op: str, b: float) -> dict:
        ops = {"<": a < b, "<=": a <= b, "=": a == b, ">=": a >= b, ">": a > b}
        return {"result": ops[op]} if op in ops else {"error": "opérateur invalide"}

    def t_duration_to_months(self, years: float = 0, months: float = 0, days: float = 0) -> dict:
        return {"months": duration_months_exact({"years": years, "months": months, "days": days})}

    def t_date_interval(self, date: str) -> dict:
        try:
            lo, hi = date_bounds(date)
        except (ValueError, TypeError):
            return {"error": "date partielle invalide"}
        return {"start": lo.isoformat(), "end": hi.isoformat()}

    def t_list_relations(self, field_id: str) -> dict:
        out = []
        for r in self.d.rules.values():
            fids = set(r.fields) | ({r.when_filled} if r.when_filled else set()) | set(r.when_filled_any or []) | ({r.requires["field"]} if r.requires else set())
            if field_id in fids:
                out.append({"rule_id": r.id, "status": r.status, "active": r.id in self.engine.active, "description": r.description})
        return {"relations": out}

    def t_run_rule(self, rule_id: str) -> dict:
        r = self.d.rules.get(rule_id)
        if not r:
            return {"error": "règle inconnue"}
        if rule_id not in self.engine.active:
            return {"active": False, "status": r.status, "note": "règle non active : ne peut pas fonder une anomalie"}
        fn = getattr(self.engine, f"_r_{rule_id.split('_')[0]}", None)
        res = [{"fields": o.fields, "result": o.result, "reason": o.reason} for o, *_ in (fn(r, self.case, self.norm) if fn else [])]
        return {"active": True, "outcomes": res}

    def quoted_matches(self, field_id: str, quoted: Any) -> bool:
        if field_id not in self.case.cells:
            return False
        sv = safe_value(self.case, self.d, field_id)
        if sv is None:
            return quoted in (None, "", "null")
        q = str(quoted).strip()
        return q == sv.strip() or (len(q) >= 4 and q in sv)
