"""Import d'un registre Excel depuis l'interface (administrateurs seulement), avec codage des noms et prénoms.

Le registre visé est désigné dans l'adresse ({slug}) ; seuls les registres qui ont un convertisseur (IMPORTERS) sont
importables. Le classeur est envoyé tel quel dans le corps de la requête (pas de formulaire multipart).
  GET  /api/admin/import                          -> registres, import disponible ou non, fiches aux noms non codés
  POST /api/admin/import/{slug}/preview           -> contrôle et aperçu, rien n'est écrit
  POST /api/admin/import/{slug}/report            -> classeur de contrôle (.xlsx)
  POST /api/admin/import/{slug}/correspondence    -> correspondance code ↔ identité du fichier (.xlsx, confidentiel)
  POST /api/admin/import/{slug}                   -> import en une seule transaction (tout ou rien), noms codés
  POST /api/admin/import/{slug}/code-existing     -> code les noms en clair déjà en base ; renvoie la correspondance
Une fiche déjà présente (même code, donc même nom, prénom et date de naissance) n'est jamais importée deux fois.
"""
from datetime import date
from io import BytesIO
from types import ModuleType
from zipfile import BadZipFile

from fastapi import APIRouter, Depends, HTTPException, Request, Response
from openpyxl.utils.exceptions import InvalidFileException
from sqlalchemy import insert, or_, select, update

from .. import import_hemophilie
from ..db import get_engine
from ..fiches import get_entity, validate_payload
from ..import_hemophilie import ImportResult
from ..pseudonym import PseudoKeyMissing, check_key, code, correspondence_workbook, is_code, stored_code
from ..schema import entities
from ..security import require_admin

router = APIRouter(prefix="/api/admin/import", tags=["import"], dependencies=[Depends(require_admin)])

# registre -> convertisseur du classeur Excel (parse_workbook, build_report)
IMPORTERS: dict[str, ModuleType] = {"hemophilie": import_hemophilie}

MAX_BYTES = 10 * 1024 * 1024
XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"


def _xlsx(content: bytes, filename: str) -> Response:
    return Response(content, media_type=XLSX, headers={"Content-Disposition": f'attachment; filename="{filename}"'})


def _importer(slug: str) -> ModuleType:
    get_entity(slug)  # 404 si le registre n'existe pas
    if slug not in IMPORTERS:
        raise HTTPException(404, "Aucun import Excel n'est encore disponible pour ce registre.")
    return IMPORTERS[slug]


def _require_key() -> None:
    try:
        check_key()
    except PseudoKeyMissing as e:
        raise HTTPException(503, str(e) + " Ajoutez PSEUDO_KEY dans le fichier .env du serveur.")


async def _parse(slug: str, request: Request) -> ImportResult:
    importer = _importer(slug)
    _require_key()
    body = await request.body()
    if not body:
        raise HTTPException(400, "Aucun fichier reçu.")
    if len(body) > MAX_BYTES:
        raise HTTPException(413, "Fichier trop volumineux (10 Mo au maximum).")
    try:
        return importer.parse_workbook(BytesIO(body))
    except (BadZipFile, InvalidFileException, KeyError, ValueError, IndexError):
        raise HTTPException(400, "Fichier illisible : un classeur Excel .xlsx est attendu.")


def _existing_codes(slug: str) -> set[str]:
    _, table = get_entity(slug)
    with get_engine().connect() as conn:
        rows = conn.execute(select(table.c.nom, table.c.prenom, table.c.dateNaissance)).all()
    return {stored_code(slug, r.nom, r.prenom, r.dateNaissance) for r in rows if r.nom or r.prenom}


def _file_code(slug: str, d: dict) -> str:
    return code(slug, d.get("nom"), d.get("prenom"), d.get("dateNaissance"))


def _count_clear_names(slug: str) -> int:
    _, table = get_entity(slug)
    with get_engine().connect() as conn:
        rows = conn.execute(select(table.c.nom, table.c.prenom).where(or_(table.c.nom.isnot(None), table.c.prenom.isnot(None)))).all()
    return sum(1 for r in rows if not (is_code(r.nom) and not r.prenom))


@router.get("")
def registries():
    out = []
    for ent in entities():
        out.append({"slug": ent["slug"], "title": ent["title"], "disponible": ent["slug"] in IMPORTERS,
                    "nonCodees": _count_clear_names(ent["slug"])})
    return out


@router.post("/{slug}/preview")
async def preview(slug: str, request: Request):
    """Contrôle le classeur et décrit ce qui serait importé ; la base n'est pas modifiée."""
    res = await _parse(slug, request)
    existing = _existing_codes(slug)
    apercu = []
    for line, d in res.fiches:
        naissance = d.get("dateNaissance")
        c = _file_code(slug, d)
        apercu.append({
            "ligne": line, "code": c, "nom": d.get("nom"), "prenom": d.get("prenom"),
            "dateNaissance": naissance.isoformat() if isinstance(naissance, date) else None,
            "formeClinique": d.get("formeClinique"), "severite": d.get("severite"),
            "dejaPresente": c in existing,
        })
    nouvelles = sum(not a["dejaPresente"] for a in apercu)
    return {
        "valide": not res.errors, "erreurs": res.errors,
        "nbFiches": len(res.fiches), "nbNouvelles": nouvelles, "nbDejaPresentes": len(apercu) - nouvelles,
        "alertes": [{k: (str(v) if v is not None else None) for k, v in a.items()} for a in res.alerts],
        "apercu": apercu,
    }


@router.post("/{slug}/report")
async def report(slug: str, request: Request):
    res = await _parse(slug, request)
    if not res.header:
        raise HTTPException(400, "; ".join(res.errors) or "Classeur vide.")
    buf = BytesIO()
    _importer(slug).build_report(res).save(buf)
    return _xlsx(buf.getvalue(), f"controle_import_{slug}.xlsx")


@router.post("/{slug}/correspondence")
async def correspondence(slug: str, request: Request):
    """Correspondance code ↔ identité des fiches du fichier (recalculable à tout moment avec la même clé)."""
    res = await _parse(slug, request)
    rows = [{"code": _file_code(slug, d), "nom": d.get("nom"), "prenom": d.get("prenom"),
             "dateNaissance": d.get("dateNaissance"), "origine": f"ligne Excel {line}"} for line, d in res.fiches]
    return _xlsx(correspondence_workbook(rows, f"fichier importé, registre {slug}"), f"correspondance_{slug}.xlsx")


@router.post("/{slug}")
async def run_import(slug: str, request: Request):
    """Importe les fiches nouvelles en une seule transaction, noms codés ; refuse tout si une ligne est invalide."""
    res = await _parse(slug, request)
    if res.errors:
        raise HTTPException(400, {"message": "Import refusé : le fichier contient des erreurs.", "erreurs": res.errors})
    ent, table = get_entity(slug)
    existing = _existing_codes(slug)
    rows, invalid = [], []
    for line, d in res.fiches:
        c = _file_code(slug, d)
        if c in existing:
            continue
        existing.add(c)
        try:
            # le nom est remplacé par le code et le prénom vidé ; mêmes règles que le formulaire de saisie
            rows.append(validate_payload(ent, {**d, "nom": c, "prenom": None}))
        except HTTPException as e:
            invalid.append(f"Ligne {line} : {e.detail.get('fieldErrors') if isinstance(e.detail, dict) else e.detail}")
    if invalid:
        raise HTTPException(400, {"message": "Import refusé : des fiches ne respectent pas les règles de saisie.", "erreurs": invalid})
    if rows:
        with get_engine().begin() as conn:
            conn.execute(insert(table), rows)
    return {"importees": len(rows), "ignoreesDejaPresentes": len(res.fiches) - len(rows), "alertes": len(res.alerts)}


@router.post("/{slug}/code-existing")
def code_existing(slug: str):
    """Code les noms encore en clair des fiches de ce registre (tous registres) ; renvoie la correspondance."""
    get_entity(slug)
    _require_key()
    _, table = get_entity(slug)
    done = []
    with get_engine().begin() as conn:
        rows = conn.execute(select(table.c.id, table.c.nom, table.c.prenom, table.c.dateNaissance)).all()
        for r in rows:
            if not (r.nom or r.prenom) or (is_code(r.nom) and not r.prenom):
                continue
            c = code(slug, r.nom, r.prenom, r.dateNaissance)
            conn.execute(update(table).where(table.c.id == r.id).values(nom=c, prenom=None))
            done.append({"code": c, "nom": r.nom, "prenom": r.prenom, "dateNaissance": r.dateNaissance, "origine": f"fiche n° {r.id}"})
    if not done:
        raise HTTPException(409, "Aucune fiche à coder : tous les noms de ce registre sont déjà codés.")
    return _xlsx(correspondence_workbook(done, f"fiches existantes du registre {slug}"), f"correspondance_existantes_{slug}.xlsx")
