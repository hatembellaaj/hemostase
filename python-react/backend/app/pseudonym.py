"""Codage (pseudonymisation) du nom et du prénom des patients.

Le nom est remplacé par un code stable, par exemple « HEM-3F9A21C4E7 », et le prénom est vidé. Le code est un HMAC-SHA256
du nom, du prénom et de la date de naissance normalisés, calculé avec la clé secrète PSEUDO_KEY :
  - le même patient obtient toujours le même code (pas de doublon lors d'un nouvel import) ;
  - sans la clé, le code ne permet pas de retrouver le nom ;
  - la correspondance code ↔ nom n'est JAMAIS enregistrée dans la base : elle est remise à l'administrateur sous forme
    de classeur, et peut être recalculée à tout moment à partir du fichier source et de la clé.
La partie hexadécimale ne dépend pas du registre : un même patient porte le même code dans tous les registres (seul le
préfixe change). Pseudonymisation n'est pas anonymisation : date de naissance, ville ou dossier restent identifiants.
"""
import hashlib
import hmac
import re
import unicodedata
from datetime import date
from io import BytesIO

import openpyxl
from openpyxl.styles import Font, PatternFill

PREFIX = {
    "hemophilie": "HEM", "thrombopathie": "THR", "von-willebrand": "VWD", "facteur-vii": "F7",
    "facteur-viii": "F8", "facteur-xi": "F11", "facteur-xiii": "F13", "facteur-xviii": "F18",
}
CODE_RE = re.compile(r"^[A-Z0-9]{2,4}-[0-9A-F]{10}$")
MIN_KEY_LENGTH = 32


class PseudoKeyMissing(RuntimeError):
    pass


def _norm(s) -> str:
    s = unicodedata.normalize("NFKD", str(s or "")).encode("ascii", "ignore").decode()
    return re.sub(r"\s+", " ", s.lower()).strip()


def _key(explicit: str | None = None) -> bytes:
    if explicit is None:
        from .config import get_settings   # import tardif : l'outil en ligne de commande n'a pas besoin de la config

        explicit = get_settings().pseudo_key
    k = explicit or ""
    if len(k) < MIN_KEY_LENGTH:
        raise PseudoKeyMissing(
            f"PSEUDO_KEY absente ou trop courte ({MIN_KEY_LENGTH} caractères minimum) : les noms ne peuvent pas être codés."
        )
    return k.encode()


def check_key() -> None:
    _key()


def is_code(value) -> bool:
    return bool(value) and bool(CODE_RE.match(str(value)))


def code(slug: str, nom, prenom, naissance: date | None, key: str | None = None) -> str:
    msg = f"{_norm(nom)}|{_norm(prenom)}|{naissance.isoformat() if naissance else ''}"
    digest = hmac.new(_key(key), msg.encode(), hashlib.sha256).hexdigest()[:10].upper()
    return f"{PREFIX.get(slug, 'PAT')}-{digest}"


def stored_code(slug: str, nom, prenom, naissance: date | None) -> str:
    """Code d'une fiche déjà en base : son nom s'il est déjà codé, sinon le code calculé à partir du nom en clair."""
    return str(nom) if is_code(nom) and not prenom else code(slug, nom, prenom, naissance)


def correspondence_workbook(rows: list[dict], source: str) -> bytes:
    """rows : {code, nom, prenom, dateNaissance, origine}. Classeur confidentiel, à conserver hors du serveur."""
    wb = openpyxl.Workbook()
    ws = wb.active
    ws.title = "Correspondance"
    ws.append([f"CONFIDENTIEL : correspondance code ↔ identité ({source}). Ne pas enregistrer sur le serveur ni partager."])
    ws["A1"].font = Font(bold=True, color="9B1C1C")
    head = ["Code", "Nom", "Prénom", "Date de naissance", "Origine"]
    ws.append(head)
    for c in ws[2]:
        c.font, c.fill = Font(bold=True, color="FFFFFF"), PatternFill("solid", fgColor="1C2433")
    for r in rows:
        d = r.get("dateNaissance")
        ws.append([r["code"], r.get("nom"), r.get("prenom"), d.isoformat() if isinstance(d, date) else d, r.get("origine")])
    for col, w in zip("ABCDE", (18, 28, 22, 18, 24)):
        ws.column_dimensions[col].width = w
    ws.freeze_panes = "A3"
    buf = BytesIO()
    wb.save(buf)
    return buf.getvalue()
