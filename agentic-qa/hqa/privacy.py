"""
Pseudonymisation (et non anonymisation) :
- identifiants d'étude aléatoires (sel secret conservé dans data/private) ;
- table de correspondance stockée séparément, jamais exportée ;
- expurgation des commentaires libres (noms de patients, prénoms après un lien de parenté) ;
- repérage des identifiants résiduels possibles pour revue humaine.

Le risque de réidentification n'est PAS nul : dates, lieux et combinaisons rares de caractéristiques
cliniques restent des quasi-identifiants.
"""
from __future__ import annotations

import hashlib
import json
import re
import secrets
from pathlib import Path

from .parsers import fold
from .workbook import Workbook

KINSHIP = r"(?:fr[eéè]res?|s(?:oe|œ)urs?|oncles?(?:\s+(?:maternels?|paternels?))?|cousins?|cousines?|tantes?|p[eèé]re|m[eèé]re|fils|fille|nom\s+de|nomm[ée]e?)"
# mots qui peuvent suivre un lien de parenté sans être un nom
NOT_A_NAME = {
    "maternel", "maternels", "paternel", "paternels", "decede", "dcd", "hemophile", "hemophiles", "et", "de", "du", "des",
    "la", "le", "les", "en", "a", "au", "aussi", "atteint", "atteints", "porteur", "suivi", "mineur", "severe", "modere",
    "decedes", "decedee", "avec", "sans", "qui", "est", "un", "une", "deux", "trois", "2", "3", "ainé", "aine", "cadet",
}
REDACTED = "[NOM]"


def name_tokens(names: list[str]) -> set[str]:
    toks = set()
    for n in names:
        for t in re.split(r"[\s\-']+", fold(n)):
            if len(t) >= 3 and not t.isdigit() and t not in NOT_A_NAME and t not in {"ben", "bel"}:
                toks.add(t)
    return toks


def redact(text: str, tokens: set[str]) -> tuple[str, int]:
    """Renvoie (texte expurgé, nombre de remplacements)."""
    if not text:
        return text, 0
    n = 0

    def kin(m):
        nonlocal n
        words = m.group(2).split()
        kept, i = [], 0
        while i < len(words) and (fold(words[i]) not in NOT_A_NAME) and re.match(r"^[A-Za-zÀ-ÿ]+$", words[i]) and i < 3:
            i += 1
        if i == 0:
            return m.group(0)
        n += 1
        return f"{m.group(1)} {REDACTED}" + ((" " + " ".join(words[i:])) if words[i:] else "")

    out = re.sub(rf"({KINSHIP})\s*:?\s+((?:(?:ben|bel|el)\s+)?[A-Za-zÀ-ÿ]+(?:\s+[A-Za-zÀ-ÿ]+){{0,3}})", kin, text, flags=re.I)

    def tok(m):
        nonlocal n
        if fold(m.group(0)) in tokens:
            n += 1
            return REDACTED
        return m.group(0)

    out = re.sub(r"[A-Za-zÀ-ÿ]{3,}", tok, out)
    return out, n


def residual_identifier_flags(text: str) -> list[str]:
    """Heuristiques de repérage pour revue humaine (ne prouvent rien, ne garantissent rien)."""
    flags = []
    if re.search(r"\b\d{2,}[/ ]\d{2,}\b", text):
        flags.append("numero_ou_reference")
    if re.search(r"\b(?:19|20)\d{2}\b", text):
        flags.append("date_ou_annee")
    if re.search(r"\b(?:hopital|hôpital|hao|chu|clinique|gafsa|sfax|tunis|gabes|kebili|tozeur|medenine|tataouine)\b", fold(text)):
        flags.append("lieu_ou_etablissement")
    if re.search(r"\b(?:tel|tél|telephone|@)\b", fold(text)):
        flags.append("contact")
    return flags


class Pseudonymizer:
    """Attribue des identifiants d'étude stables et garde la correspondance hors des exports."""

    def __init__(self, private_dir: str | Path, prefix: str = "S"):
        self.dir = Path(private_dir)
        self.dir.mkdir(parents=True, exist_ok=True)
        salt_file = self.dir / "salt.txt"
        if not salt_file.exists():
            salt_file.write_text(secrets.token_hex(16), encoding="utf-8")
        self.salt = salt_file.read_text(encoding="utf-8").strip()
        self.prefix = prefix
        self.map_file = self.dir / "id_map.json"

    def assign(self, wb: Workbook, name_col: int, file_col: int | None) -> dict[int, str]:
        """ligne Excel -> identifiant d'étude. Un identifiant par LIGNE (aucune fusion d'identité)."""
        keyed = []
        for r in wb.data_rows:
            h = hashlib.sha256(f"{self.salt}|{wb.sha256}|row{r}".encode()).hexdigest()
            keyed.append((h, r))
        keyed.sort()
        mapping = {r: f"{self.prefix}{i + 1:03d}" for i, (_, r) in enumerate(keyed)}
        private = {
            "file_sha256": wb.sha256,
            "warning": "Table de correspondance : données identifiantes. Ne jamais exporter ni versionner.",
            "rows": {
                sid: {
                    "excel_row": r,
                    "name": wb.cell(r, name_col).raw_value,
                    "file_number": wb.cell(r, file_col).raw_value if file_col else None,
                }
                for r, sid in mapping.items()
            },
        }
        self.map_file.write_text(json.dumps(private, ensure_ascii=False, indent=1, default=str), encoding="utf-8")
        return mapping

    def name_key(self, name: str) -> str:
        """Clé de nom hachée (pour regrouper sans exposer le nom)."""
        return hashlib.sha256(f"{self.salt}|name|{fold(name)}".encode()).hexdigest()[:12]
