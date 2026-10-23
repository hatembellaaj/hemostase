"""
Parseurs déterministes des formats autorisés.

Principe : un parseur renvoie soit une représentation structurée, soit un échec explicite (ParseFailure).
Il ne devine jamais : une année seule reste une année, « <1% » garde l'opérateur, la borne et l'unité,
un nombre à deux chiffres dans un champ multi-codes reste ambigu.
"""
from __future__ import annotations

import re
import unicodedata
from dataclasses import dataclass, field
from datetime import date, datetime
from typing import Any

from .schemas import Comparator, TemporalPrecision

DEFAULT_UNKNOWN_MARKERS = ["?", "??", "???", "????", "np", "nf", "nf?", "non trouve", "non precise", "non trouvé", "non précisé"]


def fold(s: Any) -> str:
    """minuscules, sans accents, espaces normalisés"""
    t = unicodedata.normalize("NFKD", str(s)).encode("ascii", "ignore").decode().lower()
    return re.sub(r"\s+", " ", t).strip()


@dataclass
class ParseFailure:
    reason: str  # code court : not_a_number, multiple_values, ambiguous_multicode…
    detail: str = ""


@dataclass
class Parsed:
    value_type: str
    value: Any
    unit: str | None = None
    comparator: Comparator | None = None
    numeric_precision: int | None = None
    temporal_precision: TemporalPrecision | None = None
    issues: list[str] = field(default_factory=list)
    justification: str = ""


def is_unknown_marker(v: Any, markers: list[str] | None = None) -> bool:
    if not isinstance(v, str):
        return False
    return fold(v) in {fold(m) for m in (markers or DEFAULT_UNKNOWN_MARKERS)}


def _decimals(s: str) -> int:
    m = re.search(r"[.,](\d+)", s)
    return len(m.group(1)) if m else 0


# ---------------------------------------------------------------------------
# Nombres (avec unité, valeurs censurées)
# ---------------------------------------------------------------------------
NUM_RE = re.compile(r"^\s*(<=|>=|≤|≥|<|>)?\s*(\d+(?:[.,]\d+)?)\s*(%|g/dl|g/l|ui/kg|ui|ub|/mm3)?\s*$", re.I)


def parse_number(v: Any, expected_unit: str | None = None) -> Parsed | ParseFailure:
    if isinstance(v, bool):
        return ParseFailure("not_a_number", "booléen")
    if isinstance(v, (int, float)):
        prec = 0 if isinstance(v, int) or float(v).is_integer() else len(repr(float(v)).split(".")[1])
        p = Parsed("number", float(v), unit=expected_unit, comparator=Comparator.EQ, numeric_precision=prec,
                   justification="nombre saisi directement ; unité celle du dictionnaire" if expected_unit else "nombre saisi directement")
        if expected_unit is None:
            p.issues.append("unite_absente")
        return p
    if not isinstance(v, str):
        return ParseFailure("not_a_number", type(v).__name__)
    m = NUM_RE.match(v)
    if not m:
        return ParseFailure("not_a_number", "format non reconnu")
    op_txt, num, unit = m.group(1), m.group(2), m.group(3)
    comp = {None: Comparator.EQ, "<": Comparator.LT, ">": Comparator.GT, "<=": Comparator.LE, "≤": Comparator.LE,
            ">=": Comparator.GE, "≥": Comparator.GE}[op_txt]
    unit_norm = unit.lower() if unit else None
    issues = []
    if unit_norm and expected_unit and unit_norm != expected_unit.lower():
        issues.append("unite_differente_du_dictionnaire")
    if not unit_norm and not expected_unit:
        issues.append("unite_absente")
    return Parsed(
        "number", float(num.replace(",", ".")), unit=unit_norm or expected_unit, comparator=comp,
        numeric_precision=_decimals(num), issues=issues,
        justification=("valeur censurée : opérateur et borne conservés" if comp != Comparator.EQ else "nombre lu dans le texte")
        + (" ; unité explicite" if unit_norm else ""),
    )


def parse_ratio_text(v: Any) -> Parsed | ParseFailure:
    """« 64/30 » (temps patient / temps témoin) -> (64.0, 30.0)"""
    if not isinstance(v, str):
        return ParseFailure("not_a_ratio", type(v).__name__)
    m = re.match(r"^\s*(\d+(?:[.,]\d+)?)\s*/\s*(\d+(?:[.,]\d+)?)\s*$", v)
    if not m:
        return ParseFailure("not_a_ratio", "format non reconnu")
    a, b = (float(x.replace(",", ".")) for x in m.groups())
    return Parsed("ratio", {"numerator": a, "denominator": b}, unit="s", numeric_precision=max(_decimals(m.group(1)), _decimals(m.group(2))),
                  justification="paire « patient/témoin » lue telle quelle")


# ---------------------------------------------------------------------------
# Durées (âges exprimés en texte)
# ---------------------------------------------------------------------------
DUR_RE = re.compile(r"(\d+(?:[.,]\d+)?)\s*(jours?|j\b|mois|ans?|annees?)", re.I)


def parse_duration(v: Any) -> Parsed | ParseFailure:
    """« 2 mois », « 1 an + 8 mois », « 18 MOIS », « 1jour » -> composantes {years, months, days}."""
    if isinstance(v, (int, float)) and not isinstance(v, bool):
        return ParseFailure("unit_missing", "nombre sans unité de durée")
    if not isinstance(v, str):
        return ParseFailure("not_a_duration", type(v).__name__)
    t = fold(v)
    parts = DUR_RE.findall(t)
    if not parts:
        if t in ("naissance", "a la naissance"):
            return Parsed("duration", {"years": 0, "months": 0, "days": 0}, unit="components",
                          justification="« naissance » = durée nulle", issues=["texte_converti"])
        return ParseFailure("not_a_duration", "format non reconnu")
    leftover = DUR_RE.sub("", t).replace("+", "").replace("et", "").strip(" ,")
    comp = {"years": 0.0, "months": 0.0, "days": 0.0}
    for num, unit in parts:
        x = float(num.replace(",", "."))
        key = "days" if unit.startswith("j") else "months" if unit == "mois" else "years"
        comp[key] += x
    issues = ["texte_residuel"] if leftover else []
    return Parsed("duration", {k: (int(x) if x.is_integer() else x) for k, x in comp.items()}, unit="components",
                  justification="durée décomposée sans conversion jours↔mois", issues=issues)


def duration_months_exact(d: dict) -> float | None:
    """Conversion exacte uniquement années/mois (1 an = 12 mois). Avec des jours : None (pas d'équivalence exacte)."""
    if d.get("days"):
        return None
    return d.get("years", 0) * 12 + d.get("months", 0)


# ---------------------------------------------------------------------------
# Dates (précision conservée)
# ---------------------------------------------------------------------------
MONTHS = {"janv": 1, "jan": 1, "fev": 2, "mars": 3, "mar": 3, "avr": 4, "mai": 5, "juin": 6, "juil": 7, "aout": 8,
          "sept": 9, "sep": 9, "oct": 10, "nov": 11, "dec": 12}


def parse_date(v: Any) -> Parsed | ParseFailure:
    if isinstance(v, datetime):
        d = v.date()
        issues = ["jour_egal_1_precision_peut_etre_mensuelle"] if d.day == 1 else []
        return Parsed("date", d.isoformat(), temporal_precision=TemporalPrecision.DAY, issues=issues,
                      justification="date Excel (précision au jour déclarée par le format)")
    if isinstance(v, date):
        return Parsed("date", v.isoformat(), temporal_precision=TemporalPrecision.DAY, justification="date")
    if isinstance(v, int) and not isinstance(v, bool):
        if 1900 <= v <= 2100:
            return Parsed("date", f"{v:04d}", temporal_precision=TemporalPrecision.YEAR,
                          justification="année seule : aucune date au jour n'est inventée")
        return ParseFailure("not_a_date", "entier hors plage d'années")
    if not isinstance(v, str):
        return ParseFailure("not_a_date", type(v).__name__)
    t = fold(v)
    years = re.findall(r"(?:19|20)\d{2}", t)
    if len(years) > 1:
        return ParseFailure("multiple_values", "plusieurs années dans la même cellule")
    if not years:
        return ParseFailure("not_a_date", "aucune année")
    y = int(years[0])
    issues = ["marqueur_incertitude"] if "?" in t else []
    m = re.match(r"^(\d{1,2})[/.-](\d{1,2})[/.-]((?:19|20)\d{2})$", t)
    if m:
        dd, mm = int(m.group(1)), int(m.group(2))
        try:
            return Parsed("date", date(y, mm, dd).isoformat(), temporal_precision=TemporalPrecision.DAY,
                          justification="date texte jj/mm/aaaa", issues=issues)
        except ValueError:
            return ParseFailure("invalid_date", "jour/mois impossible")
    month = next((n for k, n in MONTHS.items() if re.search(rf"\b{k}", t)), None)
    if month:
        return Parsed("date", f"{y:04d}-{month:02d}", temporal_precision=TemporalPrecision.MONTH,
                      justification="mois et année en texte", issues=issues)
    rest = re.sub(r"(?:19|20)\d{2}", "", t).replace("?", "").strip()
    if rest:
        return ParseFailure("not_a_date", "texte non reconnu autour de l'année")
    return Parsed("date", f"{y:04d}", temporal_precision=TemporalPrecision.YEAR,
                  justification="année seule : aucune date au jour n'est inventée", issues=issues)


def date_bounds(iso: str) -> tuple[date, date]:
    """Intervalle couvert par une date partielle."""
    parts = [int(x) for x in iso.split("-")]
    if len(parts) == 3:
        d = date(*parts)
        return d, d
    if len(parts) == 2:
        y, m = parts
        nxt = date(y + (m == 12), m % 12 + 1, 1)
        return date(y, m, 1), date.fromordinal(nxt.toordinal() - 1)
    return date(parts[0], 1, 1), date(parts[0], 12, 31)


# ---------------------------------------------------------------------------
# Codes
# ---------------------------------------------------------------------------
def parse_code(v: Any, defined_codes: list[int] | None, multi: bool = False) -> Parsed | ParseFailure:
    """Code numérique. defined_codes = codes dont la signification est documentée (même à l'état d'hypothèse)."""
    if isinstance(v, bool):
        return ParseFailure("not_a_code", "booléen")
    if isinstance(v, float) and v.is_integer():
        v = int(v)
    if isinstance(v, int):
        if multi and v >= 10:
            return ParseFailure("ambiguous_multicode", "nombre à plusieurs chiffres : code unique ou liste de codes ?")
        codes = [v]
    elif isinstance(v, float):
        return ParseFailure("ambiguous_multicode" if multi else "not_a_code", "nombre décimal dans un champ codé")
    elif isinstance(v, str) and re.fullmatch(r"\s*\d+(\s+\d+)*\s*", v):
        codes = [int(x) for x in v.split()]
        if len(codes) > 1 and not multi:
            return ParseFailure("multiple_values", "plusieurs codes dans un champ à valeur unique")
    else:
        return ParseFailure("text_in_coded_field", "texte libre dans un champ codé")
    issues = []
    if defined_codes is not None and any(c not in defined_codes for c in codes):
        issues.append("code_non_defini")
    return Parsed("code", codes if multi else codes[0], issues=issues,
                  justification="code lu tel quel ; signification issue du dictionnaire uniquement")
