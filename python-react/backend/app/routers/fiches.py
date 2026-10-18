from fastapi import APIRouter, Depends, HTTPException, Query, Request, Response
from sqlalchemy import delete, func, insert, select, update

from ..db import get_engine
from ..fiches import build_list_query, select_keyed, get_entity, serialize, to_csv, validate_payload
from ..schema import entities, load_schema
from ..stats import aggregate, overview
from ..security import get_current_user

router = APIRouter(prefix="/api", tags=["fiches"], dependencies=[Depends(get_current_user)])

RESERVED = {"page", "size", "sort", "q"}


@router.get("/schema")
def get_schema():
    """Description complète des fiches : sections, champs, libellés, options, conditions."""
    return load_schema()


@router.get("/stats")
def stats():
    out = []
    with get_engine().connect() as conn:
        for ent in entities():
            _, table = get_entity(ent["slug"])
            n = conn.execute(select(func.count()).select_from(table)).scalar_one()
            out.append({"slug": ent["slug"], "title": ent["title"], "count": n})
    return out


@router.get("/stats/{slug}/overview")
def stats_overview(slug: str):
    """Chiffres clés et graphiques prédéfinis d'un registre."""
    ent, table = get_entity(slug)
    return overview(ent, table)


@router.post("/stats/{slug}/aggregate")
def stats_aggregate(slug: str, spec: dict):
    """Agrégation à la demande (explorateur de l'interface)."""
    ent, table = get_entity(slug)
    return aggregate(ent, table, spec)


@router.get("/fiches/{slug}")
def list_fiches(
    slug: str,
    request: Request,
    response: Response,
    page: int = Query(0, ge=0),
    size: int = Query(20, ge=1, le=500),
    sort: str | None = None,
    q: str | None = None,
):
    ent, table = get_entity(slug)
    filters = {k: v for k, v in request.query_params.items() if k not in RESERVED}
    stmt, count = build_list_query(ent, table, q, sort, filters)
    with get_engine().connect() as conn:
        total = conn.execute(count).scalar_one()
        rows = conn.execute(stmt.offset(page * size).limit(size)).mappings().all()
    response.headers["X-Total-Count"] = str(total)
    return [serialize(r) for r in rows]


@router.get("/fiches/{slug}/export.csv")
def export_csv(slug: str, request: Request, sort: str | None = None, q: str | None = None):
    ent, table = get_entity(slug)
    filters = {k: v for k, v in request.query_params.items() if k not in RESERVED}
    stmt, _ = build_list_query(ent, table, q, sort, filters)
    with get_engine().connect() as conn:
        rows = conn.execute(stmt).mappings().all()
    return Response(
        to_csv(ent, rows),
        media_type="text/csv; charset=utf-8",
        headers={"Content-Disposition": f'attachment; filename="{ent["table"]}.csv"'},
    )


@router.get("/fiches/{slug}/{fiche_id}")
def get_fiche(slug: str, fiche_id: int):
    _, table = get_entity(slug)
    with get_engine().connect() as conn:
        row = conn.execute(select_keyed(table).where(table.c.id == fiche_id)).mappings().first()
    if not row:
        raise HTTPException(404, "Fiche introuvable")
    return serialize(row)


@router.post("/fiches/{slug}", status_code=201)
def create_fiche(slug: str, payload: dict):
    ent, table = get_entity(slug)
    if payload.get("id"):
        raise HTTPException(400, "Une nouvelle fiche ne peut pas déjà avoir un ID")
    data = validate_payload(ent, payload)
    with get_engine().begin() as conn:
        new_id = conn.execute(insert(table).values(**data)).inserted_primary_key[0]
    return get_fiche(slug, new_id)


@router.put("/fiches/{slug}/{fiche_id}")
def update_fiche(slug: str, fiche_id: int, payload: dict):
    ent, table = get_entity(slug)
    if payload.get("id") not in (None, fiche_id):
        raise HTTPException(400, "ID incohérent")
    data = validate_payload(ent, payload)
    with get_engine().begin() as conn:
        if conn.execute(update(table).where(table.c.id == fiche_id).values(**data)).rowcount == 0:
            raise HTTPException(404, "Fiche introuvable")
    return get_fiche(slug, fiche_id)


@router.delete("/fiches/{slug}/{fiche_id}", status_code=204)
def delete_fiche(slug: str, fiche_id: int):
    _, table = get_entity(slug)
    with get_engine().begin() as conn:
        if conn.execute(delete(table).where(table.c.id == fiche_id)).rowcount == 0:
            raise HTTPException(404, "Fiche introuvable")
    return Response(status_code=204)
