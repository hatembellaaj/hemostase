"""
Statistiques : moteur d'agrégation générique + présélections par type de fiche.

Une requête d'agrégation (« spec ») :
    {
      "x":       "severite",                 # champ en abscisse (énumération, booléen, nombre, date)
      "xBin":    10,                         # largeur de classe pour un nombre ; "year" pour une date
      "fields":  ["epistaxis", ...],         # à la place de x : compare plusieurs champs oui/non
      "series":  "sexe",                     # optionnel : répartition par un second champ
      "measure": {"op": "count"}             # ou {"op": "avg"|"min"|"max"|"sum", "field": "hb"}
      "filters": [{"field": "sexe", "op": "in", "value": ["FEMININ"]},
                  {"field": "ageActuel", "op": "between", "value": [0, 18]}],
      "includeMissing": true                 # garder la catégorie « Non renseigné »
    }
"""
from __future__ import annotations

import math
import re
from statistics import median

from fastapi import HTTPException
from sqlalchemy import Integer, Table, and_, case, cast, func, or_, select

from .db import get_engine

MISSING = "__missing__"
MISSING_LABEL = "Non renseigné"
DIMENSION_TYPES = ("enum", "boolean", "integer", "float", "date", "datetime")
NUMERIC = ("integer", "float")
YES_VALUES = ("OUI", "FAITE", "FAIT", "POSITIVE", "POSITIF")


def clean_label(label: str) -> str:
    """« 1 - Hémarthrose » (numérotation des formulaires) -> « Hémarthrose »"""
    return re.sub(r"^\s*\d+\s*-\s*", "", label or "")


def _fields(ent: dict) -> dict[str, dict]:
    return {f["name"]: f for f in ent["fields"]}


def _field(ent: dict, name: str, allowed=DIMENSION_TYPES) -> dict:
    f = _fields(ent).get(name)
    if not f or f["type"] not in allowed:
        raise HTTPException(400, f"Champ non utilisable pour une statistique : {name}")
    return f


def _nice_width(lo: float, hi: float, target: int = 10) -> float:
    span = hi - lo
    if span <= 0:
        return 1
    raw = span / target
    mag = 10 ** math.floor(math.log10(raw))
    for m in (1, 2, 2.5, 5, 10):
        if raw <= m * mag:
            w = m * mag
            break
    return int(w) if float(w).is_integer() else w


def _dimension(table: Table, f: dict, binning, conn):
    """Expression SQL de regroupement + fonction de libellé + ordre des clés."""
    col = table.c[f["name"]]
    t = f["type"]
    if t == "enum":
        order = [o["value"] for o in f["options"]]
        labels = {o["value"]: o["label"] for o in f["options"]}
        return col, (lambda k: labels.get(k, str(k))), order, None
    if t == "boolean":
        return col, (lambda k: "Oui" if k in (True, 1) else "Non"), [True, False], None
    if t in ("date", "datetime"):
        expr = cast(func.extract("year", col), Integer)
        return expr, (lambda k: str(k)), None, "year"
    # nombre : classes de largeur fixe
    if binning in (None, "", "auto"):
        lo, hi = conn.execute(select(func.min(col), func.max(col))).one()
        if lo is None:
            binning = 1
        else:
            binning = _nice_width(float(lo), float(hi))
    w = float(binning)
    if w <= 0:
        raise HTTPException(400, "Largeur de classe invalide")
    # floor() n'existe pas partout (SQLite) : CASE pour les négatifs
    q = col / w
    floor = case((q >= 0, cast(q, Integer)), else_=cast(q, Integer) - 1)
    expr = floor * w
    def label(k, w=w, integer=f["type"] == "integer"):
        k = float(k)
        fmt = lambda v: f"{v:g}".replace(".", ",")  # noqa: E731
        if integer and w.is_integer():
            return fmt(k) if w == 1 else f"{fmt(k)}–{fmt(k + w - 1)}"
        return f"{fmt(k)} à < {fmt(k + w)}"
    return expr, label, None, w


def _yes(table: Table, f: dict):
    col = table.c[f["name"]]
    if f["type"] == "boolean":
        return col == True  # noqa: E712
    return col.in_(YES_VALUES)


def _filters(ent: dict, table: Table, filters: list[dict]):
    conds = []
    for flt in filters or []:
        f = _field(ent, flt.get("field", ""))
        col = table.c[f["name"]]
        op, val = flt.get("op", "in"), flt.get("value")
        if op == "in":
            vals = val if isinstance(val, list) else [val]
            parts = []
            if MISSING in vals:
                parts.append(col.is_(None))
            real = [v for v in vals if v != MISSING]
            if f["type"] == "boolean":
                real = [v in (True, "true", 1, "1") for v in real]
            if real:
                parts.append(col.in_(real))
            if parts:
                conds.append(or_(*parts))
        elif op == "between" and isinstance(val, list) and len(val) == 2:
            lo, hi = val
            if f["type"] in ("date", "datetime"):
                year = cast(func.extract("year", col), Integer)
                if lo not in (None, ""):
                    conds.append(year >= int(lo))
                if hi not in (None, ""):
                    conds.append(year <= int(hi))
            else:
                if lo not in (None, ""):
                    conds.append(col >= float(lo))
                if hi not in (None, ""):
                    conds.append(col <= float(hi))
        else:
            raise HTTPException(400, f"Filtre invalide sur {f['name']}")
    return and_(*conds) if conds else None


def _measure(ent: dict, table: Table, measure: dict | None):
    measure = measure or {"op": "count"}
    op = measure.get("op", "count")
    if op == "count":
        return func.count(), "Nombre de fiches", None
    f = _field(ent, measure.get("field", ""), NUMERIC)
    col = table.c[f["name"]]
    fn = {"avg": func.avg, "min": func.min, "max": func.max, "sum": func.sum}.get(op)
    if not fn:
        raise HTTPException(400, f"Mesure inconnue : {op}")
    names = {"avg": "Moyenne", "min": "Minimum", "max": "Maximum", "sum": "Somme"}
    return fn(col), f"{names[op]} de « {clean_label(f['label'])} »", col


def _key(v):
    if v is None:
        return MISSING
    if isinstance(v, float) and v.is_integer():
        return int(v)
    return v


def aggregate(ent: dict, table: Table, spec: dict) -> dict:
    include_missing = spec.get("includeMissing", True)
    with get_engine().connect() as conn:
        where = _filters(ent, table, spec.get("filters", []))
        mexpr, mlabel, mcol = _measure(ent, table, spec.get("measure"))
        base_count = select(func.count()).select_from(table)
        if where is not None:
            base_count = base_count.where(where)
        total = conn.execute(base_count).scalar_one()

        series_f = None
        if spec.get("series"):
            series_f = _field(ent, spec["series"])
            s_expr, s_label, s_order, _ = _dimension(table, series_f, spec.get("seriesBin"), conn)

        # --- mode « plusieurs champs oui/non » : une barre par champ
        if spec.get("fields"):
            fs = [_field(ent, n, ("enum", "boolean")) for n in spec["fields"]]
            cats, cells = [], {}
            for f in fs:
                stmt = select(*( [s_expr.label("s")] if series_f else [] ), mexpr.label("v")).select_from(table)
                cond = _yes(table, f) if where is None else and_(where, _yes(table, f))
                stmt = stmt.where(cond)
                if series_f:
                    stmt = stmt.group_by(s_expr)
                for row in conn.execute(stmt):
                    sk = _key(row.s) if series_f else "value"
                    if sk == MISSING and not include_missing:
                        continue
                    cells.setdefault(f["name"], {})[sk] = _num(row.v)
                cats.append({"key": f["name"], "label": clean_label(f["label"])})
            series = _series_list(series_f, s_label, s_order, cells) if series_f else [{"key": "value", "label": mlabel}]
            return {"x": {"label": "Champ"}, "measure": mlabel, "categories": cats, "series": series,
                    "cells": cells, "total": total}

        x_f = _field(ent, spec.get("x", ""))
        x_expr, x_label, x_order, x_bin = _dimension(table, x_f, spec.get("xBin"), conn)
        cols = [x_expr.label("x")] + ([s_expr.label("s")] if series_f else []) + [mexpr.label("v")]
        stmt = select(*cols).select_from(table)
        if where is not None:
            stmt = stmt.where(where)
        stmt = stmt.group_by(x_expr, *([s_expr] if series_f else []))
        cells: dict = {}
        keys = []
        for row in conn.execute(stmt):
            xk = _key(row.x)
            sk = _key(row.s) if series_f else "value"
            if not include_missing and (xk == MISSING or sk == MISSING):
                continue
            cells.setdefault(xk, {})[sk] = _num(row.v)
            if xk not in keys:
                keys.append(xk)

    def sort_key(k):
        if k == MISSING:
            return (2, 0, "")
        if x_order:
            return (0, x_order.index(k) if k in x_order else len(x_order), str(k))
        return (0, k if isinstance(k, (int, float)) else 0, str(k))

    keys.sort(key=sort_key)
    if x_order and x_f["type"] == "enum" and spec.get("showEmpty", False):
        keys = [k for k in x_order] + [k for k in keys if k not in x_order]
    cats = [{"key": _ser(k), "label": MISSING_LABEL if k == MISSING else x_label(k)} for k in keys]
    cells = {_ser(k): {_ser(s): v for s, v in d.items()} for k, d in cells.items()}
    series = _series_list(series_f, s_label, s_order, cells) if series_f else [{"key": "value", "label": mlabel}]
    return {
        "x": {"field": x_f["name"], "label": clean_label(x_f["label"]), "bin": x_bin},
        "measure": mlabel,
        "categories": cats,
        "series": series,
        "cells": cells,
        "total": total,
    }


def _ser(k):
    return "true" if k is True else "false" if k is False else k


def _num(v):
    if v is None:
        return None
    v = float(v)
    return int(v) if v.is_integer() else round(v, 2)


def _series_list(f, label, order, cells) -> list[dict]:
    present = []
    for d in cells.values():
        for k in d:
            if k not in present:
                present.append(k)
    def norm(k):
        return True if k == "true" else False if k == "false" else k
    ordered = [k for k in (order or []) if _ser(k) in present]
    ordered = [_ser(k) for k in ordered] + sorted([k for k in present if k not in [_ser(o) for o in (order or [])] and k != MISSING], key=str)
    if MISSING in present:
        ordered.append(MISSING)
    return [{"key": k, "label": MISSING_LABEL if k == MISSING else label(norm(k))} for k in ordered]


# ---------------------------------------------------------------------------------------
# Vue d'ensemble : chiffres clés + graphiques prédéfinis
# ---------------------------------------------------------------------------------------
COMMON_CHARTS = [
    {"id": "age", "title": "Âge actuel", "type": "bar", "x": "ageActuel", "xBin": 10, "series": "sexe"},
    {"id": "origine", "title": "Origine géographique", "type": "bar", "x": "origine", "horizontal": True},
    {"id": "couverture", "title": "Couverture sociale", "type": "pie", "x": "couvertureSociale"},
    {"id": "consanguinite", "title": "Parents consanguins", "type": "pie", "x": "parentsConsanguins"},
    {"id": "anneeDiag", "title": "Année du diagnostic", "type": "bar", "x": "anneeDiagnostic", "xBin": 5},
]

SPECIFIC = {
    "hemophilie": [
        {"title": "Forme clinique et sévérité", "type": "bar", "x": "formeClinique", "series": "severite", "stacked": True},
        {"title": "Prise en charge thérapeutique", "type": "pie", "x": "priseEnChargeTherapeutique"},
        {"title": "Traitement substitutif", "type": "pie", "x": "modaliteTraitement"},
        {"title": "Taux moyen de FVIII selon la sévérité, hémophilie A (%)", "type": "bar", "x": "severite",
         "measure": {"op": "avg", "field": "dosageFacteurVIII"}, "filters": [{"field": "formeClinique", "op": "in", "value": ["A"]}]},
        {"title": "Taux moyen de FIX selon la sévérité, hémophilie B (%)", "type": "bar", "x": "severite",
         "measure": {"op": "avg", "field": "dosageFacteurIX"}, "filters": [{"field": "formeClinique", "op": "in", "value": ["B"]}]},
        {"title": "Manifestations cliniques (patients concernés)", "type": "bar", "horizontal": True,
         "fields": ["hemarthroseMC", "hematomeSuperficiel", "hematomePsoas", "hemorragiesCutaneoMuqueuses", "hemorragieVisceres", "saignementSNC", "accidentsHemorragiquesGraves"]},
        {"title": "Complications", "type": "bar", "horizontal": True,
         "fields": ["orthopediques", "inhibiteurs", "serologieHVC", "serologieHBC", "serologieHIV"]},
        {"title": "Inhibiteurs selon la sévérité", "type": "bar", "x": "severite", "series": "inhibiteurs", "stacked": True},
        {"title": "Vie sociale", "type": "bar", "x": "vieSociale"},
    ],
    "thrombopathie": [
        {"title": "Symptômes hémorragiques", "type": "bar", "horizontal": True,
         "fields": ["ecchymose", "epistaxis", "gingivorragies", "hematome", "menoMetrorragies", "hemorragieVisceres"]},
        {"title": "Agrégométrie plaquettaire", "type": "pie", "x": "aggregometriePlaquettaire"},
        {"title": "Étude génétique", "type": "pie", "x": "etudeGenetique"},
        {"title": "Traitements", "type": "bar", "horizontal": True,
         "fields": ["transfusionCps", "facteurVIIRecombinant", "dicynone", "exacyl", "traitementOestroprogestatif", "transfusionCgr", "traitementMartial"]},
        {"title": "Complications", "type": "bar", "horizontal": True, "fields": ["thrombose", "alloImmunisationHLA"]},
    ],
    "von-willebrand": [
        {"title": "Type de maladie de Willebrand", "type": "pie", "x": "typeMvW"},
        {"title": "Sous-type", "type": "bar", "x": "sousTypeMvW", "series": "sexe", "stacked": True},
        {"title": "Symptômes révélateurs", "type": "bar", "horizontal": True,
         "fields": ["symptomeEcchymose", "symptomeEpistaxis", "symptomeGingivorragies", "symptomeMetrorragies", "symptomeMelena",
                    "symptomeHemoptysie", "symptomeHemorragiePostAmygdalectomie", "symptomeHemorragiePostAvulsionDentaire", "symptomeHemorragieChuteCordon"]},
        {"title": "FVW:Ag moyen selon le type (%)", "type": "bar", "x": "typeMvW", "measure": {"op": "avg", "field": "fvwag"}},
        {"title": "FVW:Act moyen selon le type (%)", "type": "bar", "x": "typeMvW", "measure": {"op": "avg", "field": "fvwact"}},
        {"title": "Traitements", "type": "bar", "horizontal": True,
         "fields": ["desmopressine", "antifibrinolytiques", "concentreFVIIIFVW", "concentreFVW", "concentreFVIII", "pfc", "cryoprecipite", "oestroprogestatifs", "traitementMartial"]},
        {"title": "Statut social", "type": "bar", "x": "statutSocial"},
    ],
}
for slug, factor in (("facteur-vii", "facteurVII"), ("facteur-viii", None), ("facteur-xiii", "facteurXIII"),
                     ("facteur-xi", "facteurXI"), ("facteur-xviii", None)):
    SPECIFIC[slug] = [
        *([{"title": f"Taux de {factor.replace('facteur', 'facteur ')} (%)", "type": "bar", "x": factor, "xBin": 10}] if factor else []),
        {"title": "Confirmation moléculaire", "type": "pie", "x": "confirmationMoleculaire"},
        {"title": "Type de mutation", "type": "bar", "x": "mutationType"},
        {"title": "Zygotie", "type": "pie", "x": "mutationZygote"},
        {"title": "Traitements", "type": "bar", "horizontal": True,
         "fields": ["facteurVIIRecombinant", "facteurVIIIRecombinant", "concentreFacteurXIII", "traitementConcentreFacteurXI",
                    "plasmaFraisCongele", "traitementPFC", "cryoprecipite", "traitementCryoprecipite", "dicynone", "traitementDicynone",
                    "exacyl", "traitementExacyl", "traitementMartial", "transfusionCGR"]},
        {"title": "Complications", "type": "bar", "horizontal": True,
         "fields": ["inhibiteurs", "inhibiteurNeutralisant", "infectionsVirales", "thrombose", "reactionAllergique", "alloImmunisation", "sequelles"]},
        {"title": "Vie sociale", "type": "bar", "x": "vieSociale"},
        {"title": "Statut social", "type": "bar", "x": "statutSocial"},
    ]

KPI_SPECIFIC = {
    "hemophilie": [("Formes sévères", "severite", "SEVERE"), ("Hémophilie B", "formeClinique", "B"), ("Avec inhibiteur", "inhibiteurs", "OUI")],
    "von-willebrand": [("Type 1", "typeMvW", "TYPE_1"), ("Ménorragies", "menoragie", "OUI")],
    "thrombopathie": [("Étude génétique", "etudeGenetique", "OUI")],
}


def _usable(ent: dict, chart: dict) -> dict | None:
    fs = _fields(ent)
    c = dict(chart)
    if "fields" in c:
        c["fields"] = [n for n in c["fields"] if n in fs and fs[n]["type"] in ("enum", "boolean")]
        return c if len(c["fields"]) >= 2 else None
    for key in ("x", "series"):
        if c.get(key) and (c[key] not in fs or fs[c[key]]["type"] not in DIMENSION_TYPES):
            if key == "series":
                c.pop("series")
            else:
                return None
    m = c.get("measure")
    if m and m.get("field") and (m["field"] not in fs or fs[m["field"]]["type"] not in NUMERIC):
        return None
    return c


def overview(ent: dict, table: Table) -> dict:
    fs = _fields(ent)
    with get_engine().connect() as conn:
        total = conn.execute(select(func.count()).select_from(table)).scalar_one()
        kpis = [{"label": "Fiches", "value": total, "format": "int"}]
        if "ageActuel" in fs:
            ages = [r[0] for r in conn.execute(select(table.c.ageActuel).where(table.c.ageActuel.is_not(None)))]
            if ages:
                kpis.append({"label": "Âge médian", "value": median(ages), "format": "years", "n": len(ages)})
                kpis.append({"label": "Moins de 18 ans", "value": sum(a < 18 for a in ages), "of": len(ages), "format": "share"})
        def share(label, field, value):
            if field in fs and any(o["value"] == value for o in fs[field].get("options", [])):
                col = table.c[field]
                known = conn.execute(select(func.count()).where(col.is_not(None))).scalar_one()
                hit = conn.execute(select(func.count()).where(col == value)).scalar_one()
                if known:
                    kpis.append({"label": label, "value": hit, "of": known, "format": "share"})
        share("Femmes", "sexe", "FEMININ")
        for label, field, value in KPI_SPECIFIC.get(ent["slug"], []):
            share(label, field, value)

    charts = []
    for i, c in enumerate(COMMON_CHARTS + SPECIFIC.get(ent["slug"], [])):
        c = _usable(ent, c)
        if not c:
            continue
        c.setdefault("id", f"c{i}")
        c["data"] = aggregate(ent, table, c)
        if c["data"]["categories"] and any(v for d in c["data"]["cells"].values() for v in d.values() if v):
            charts.append(c)
    return {"total": total, "kpis": kpis, "charts": charts}
