"""Validation, calculs automatiques et accès générique aux fiches."""
from __future__ import annotations

import csv
import io
from datetime import date, datetime, timezone

from fastapi import HTTPException
from sqlalchemy import Table, asc, desc, func, or_, select

from .db import fiche_tables
from .schema import entity_by_slug


def get_entity(slug: str) -> tuple[dict, Table]:
    ent = entity_by_slug().get(slug)
    if not ent:
        raise HTTPException(404, f"Type de fiche inconnu : {slug}")
    return ent, fiche_tables[slug]


# ---------------------------------------------------------------------------
# Conversion / validation
# ---------------------------------------------------------------------------
def _coerce(field: dict, value):
    if value is None or value == "":
        return None
    t = field["type"]
    try:
        if t in ("string", "enum"):
            return str(value)
        if t == "integer":
            if isinstance(value, bool):
                raise ValueError
            f = float(value)
            if not f.is_integer():
                raise ValueError
            return int(f)
        if t == "float":
            if isinstance(value, bool):
                raise ValueError
            return float(str(value).replace(",", "."))
        if t == "boolean":
            if isinstance(value, bool):
                return value
            if str(value).lower() in ("true", "1", "oui", "yes"):
                return True
            if str(value).lower() in ("false", "0", "non", "no"):
                return False
            raise ValueError
        if t == "date":
            if isinstance(value, date):
                return value
            return date.fromisoformat(str(value)[:10])
        if t == "datetime":
            return datetime.fromisoformat(str(value))
    except (TypeError, ValueError):
        raise _FieldError("Valeur invalide")
    return value


class _FieldError(Exception):
    pass


def validate_payload(ent: dict, payload: dict) -> dict:
    errors: dict[str, str] = {}
    out: dict = {}
    for f in ent["fields"]:
        name = f["name"]
        raw = payload.get(name)
        try:
            v = _coerce(f, raw)
        except _FieldError as e:
            errors[name] = str(e)
            continue
        if v is None and f.get("required"):
            errors[name] = "Ce champ est obligatoire."
            continue
        if v is not None:
            if f["type"] == "enum":
                allowed = {o["value"] for o in f.get("options", [])}
                if v not in allowed:
                    errors[name] = "Valeur non autorisée."
                    continue
            if f["type"] == "string" and f.get("maxLength") and len(v) > f["maxLength"]:
                errors[name] = f"Ce champ doit faire moins de {f['maxLength']} caractères."
                continue
            if "min" in f and isinstance(v, (int, float)) and v < f["min"]:
                errors[name] = f"Valeur minimale : {f['min']}"
                continue
            if "max" in f and isinstance(v, (int, float)) and v > f["max"]:
                errors[name] = f"Valeur maximale : {f['max']}"
                continue
        out[name] = v
    if errors:
        raise HTTPException(400, {"message": "Erreur de validation", "fieldErrors": errors})
    apply_computed(ent, out)
    return out


def age_from(birth: date | None, today: date | None = None) -> int | None:
    if not birth:
        return None
    today = today or date.today()
    age = today.year - birth.year - ((today.month, today.day) < (birth.month, birth.day))
    return age if age >= 0 else None


def apply_computed(ent: dict, data: dict) -> None:
    """Reproduit les calculs automatiques des formulaires Angular d'origine."""
    for c in ent.get("computed", []):
        if c["fn"] == "age":
            birth = data.get(c["args"][0])
            if birth is not None:
                data[c["field"]] = age_from(birth)
        elif c["fn"] == "ratio":
            num, den = data.get(c["args"][0]), data.get(c["args"][1])
            if num and den:
                data[c["field"]] = round(num / den, 2)


# ---------------------------------------------------------------------------
# Sérialisation
# ---------------------------------------------------------------------------
def serialize(row) -> dict:
    out = {}
    for k, v in dict(row).items():
        if isinstance(v, (date, datetime)):
            v = v.isoformat()
        out[k] = v
    return out


# ---------------------------------------------------------------------------
# Requêtes
# ---------------------------------------------------------------------------
def select_keyed(table: Table):
    """SELECT dont les colonnes sont étiquetées par le nom de champ (camelCase)."""
    return select(*[c.label(c.key) for c in table.c])


def build_list_query(ent: dict, table: Table, q: str | None, sort: str | None, filters: dict[str, str]):
    stmt = select_keyed(table)
    count = select(func.count()).select_from(table)
    conds = []
    if q:
        like = f"%{q.strip()}%"
        text_cols = [table.c[f["name"]] for f in ent["fields"] if f["name"] in ("dossierNumber", "nom", "prenom", "ordreNumber", "indexNumber", "telephone")]
        if text_cols:
            conds.append(or_(*[c.ilike(like) for c in text_cols]))
    fields = {f["name"]: f for f in ent["fields"]}
    for k, v in filters.items():
        if k in fields and v not in (None, ""):
            try:
                conds.append(table.c[k] == _coerce(fields[k], v))
            except _FieldError:
                raise HTTPException(400, f"Filtre invalide : {k}")
    for c in conds:
        stmt = stmt.where(c)
        count = count.where(c)
    order = []
    for part in (sort or "id,desc").split(";"):
        name, _, direction = part.partition(",")
        if name == "id" or name in fields:
            col = table.c[name]
            order.append(desc(col) if direction.lower() == "desc" else asc(col))
    if not order:
        order = [desc(table.c.id)]
    stmt = stmt.order_by(*order)
    return stmt, count


def to_csv(ent: dict, rows) -> str:
    buf = io.StringIO()
    names = ["id"] + [f["name"] for f in ent["fields"]]
    labels = ["ID"] + [f["label"] for f in ent["fields"]]
    w = csv.writer(buf, delimiter=";")
    w.writerow(labels)
    for r in rows:
        d = serialize(r)
        w.writerow(["" if d.get(n) is None else d.get(n) for n in names])
    return "﻿" + buf.getvalue()  # BOM pour Excel
