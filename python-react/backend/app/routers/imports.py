"""Import du registre Excel de l'hémophilie depuis l'interface (administrateurs seulement).

Le classeur est envoyé tel quel dans le corps de la requête (pas de formulaire multipart). Trois étapes :
  POST /api/admin/import/hemophilie/preview  -> contrôle et aperçu, rien n'est écrit
  POST /api/admin/import/hemophilie/report   -> classeur de contrôle (.xlsx)
  POST /api/admin/import/hemophilie          -> import en une seule transaction (tout ou rien)
Une fiche déjà présente (même nom, prénom et date de naissance) n'est jamais importée une seconde fois.
"""
from datetime import date
from io import BytesIO
from zipfile import BadZipFile

from fastapi import APIRouter, Depends, HTTPException, Request, Response
from openpyxl.utils.exceptions import InvalidFileException
from sqlalchemy import insert, select

from ..db import get_engine
from ..fiches import get_entity, validate_payload
from ..import_hemophilie import ImportResult, build_report, norm, parse_workbook
from ..security import require_admin

router = APIRouter(prefix="/api/admin/import", tags=["import"], dependencies=[Depends(require_admin)])

MAX_BYTES = 10 * 1024 * 1024
XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"


async def _parse(request: Request) -> ImportResult:
    body = await request.body()
    if not body:
        raise HTTPException(400, "Aucun fichier reçu.")
    if len(body) > MAX_BYTES:
        raise HTTPException(413, "Fichier trop volumineux (10 Mo au maximum).")
    try:
        return parse_workbook(BytesIO(body))
    except (BadZipFile, InvalidFileException, KeyError, ValueError, IndexError):
        raise HTTPException(400, "Fichier illisible : un classeur Excel .xlsx est attendu.")


def _key(nom, prenom, naissance) -> tuple:
    return norm(nom or ""), norm(prenom or ""), naissance


def _existing_keys() -> set[tuple]:
    _, table = get_entity("hemophilie")
    with get_engine().connect() as conn:
        rows = conn.execute(select(table.c.nom, table.c.prenom, table.c.dateNaissance)).all()
    return {_key(r.nom, r.prenom, r.dateNaissance) for r in rows}


def _summary(res: ImportResult) -> dict:
    existing = _existing_keys()
    apercu = []
    for line, d in res.fiches:
        naissance = d.get("dateNaissance")
        apercu.append({
            "ligne": line,
            "nom": d.get("nom"),
            "prenom": d.get("prenom"),
            "dateNaissance": naissance.isoformat() if isinstance(naissance, date) else None,
            "formeClinique": d.get("formeClinique"),
            "severite": d.get("severite"),
            "dejaPresente": _key(d.get("nom"), d.get("prenom"), naissance) in existing,
        })
    nouvelles = sum(not a["dejaPresente"] for a in apercu)
    return {
        "valide": not res.errors,
        "erreurs": res.errors,
        "nbFiches": len(res.fiches),
        "nbNouvelles": nouvelles,
        "nbDejaPresentes": len(apercu) - nouvelles,
        "alertes": [{k: (str(v) if v is not None else None) for k, v in a.items()} for a in res.alerts],
        "apercu": apercu,
    }


@router.post("/hemophilie/preview")
async def preview(request: Request):
    """Contrôle le classeur et décrit ce qui serait importé ; la base n'est pas modifiée."""
    return _summary(await _parse(request))


@router.post("/hemophilie/report")
async def report(request: Request):
    res = await _parse(request)
    if not res.header:
        raise HTTPException(400, "; ".join(res.errors) or "Classeur vide.")
    buf = BytesIO()
    build_report(res).save(buf)
    return Response(buf.getvalue(), media_type=XLSX,
                    headers={"Content-Disposition": 'attachment; filename="controle_import_hemophilie.xlsx"'})


@router.post("/hemophilie")
async def run_import(request: Request):
    """Importe les fiches nouvelles en une seule transaction ; refuse tout si une ligne est invalide."""
    res = await _parse(request)
    if res.errors:
        raise HTTPException(400, {"message": "Import refusé : le fichier contient des erreurs.", "erreurs": res.errors})
    ent, table = get_entity("hemophilie")
    existing = _existing_keys()
    rows, invalid = [], []
    for line, d in res.fiches:
        if _key(d.get("nom"), d.get("prenom"), d.get("dateNaissance")) in existing:
            continue
        try:
            rows.append(validate_payload(ent, d))   # mêmes règles que le formulaire de saisie
        except HTTPException as e:
            invalid.append(f"Ligne {line} : {e.detail.get('fieldErrors') if isinstance(e.detail, dict) else e.detail}")
    if invalid:
        raise HTTPException(400, {"message": "Import refusé : des fiches ne respectent pas les règles de saisie.", "erreurs": invalid})
    if rows:
        with get_engine().begin() as conn:
            conn.execute(insert(table), rows)
    return {"importees": len(rows), "ignoreesDejaPresentes": len(res.fiches) - len(rows), "alertes": len(res.alerts)}
