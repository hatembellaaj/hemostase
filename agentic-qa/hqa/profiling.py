"""Profil descriptif agrégé : aucune valeur individuelle de colonne identifiante n'est exportée."""
from __future__ import annotations

import collections
from datetime import datetime

from .workbook import Workbook, slug

# Colonnes dont les valeurs ne doivent jamais apparaître, même agrégées (identifiants directs / quasi)
DEFAULT_SENSITIVE = {"nom et prenom", "numero du dossier", "ville", "date de naissance", "ddn"}


def _kind(v) -> str:
    if v is None:
        return "vide"
    if isinstance(v, bool):
        return "booléen"
    if isinstance(v, datetime):
        return "date Excel"
    if isinstance(v, int):
        return "entier"
    if isinstance(v, float):
        return "décimal"
    return "texte"


def profile(wb: Workbook, sensitive_headers: set[str] | None = None, top_k: int = 8, min_count_to_show: int = 3) -> dict:
    """Profil par colonne. Les valeurs ne sont listées que pour les colonnes non sensibles et
    seulement si elles apparaissent au moins `min_count_to_show` fois (pas de valeur rare = pas d'identification)."""
    sens = {slug(s).replace("_", " ") for s in (sensitive_headers or DEFAULT_SENSITIVE)}
    rows = wb.data_rows
    header_counts = collections.Counter(slug(h) for h in wb.headers if h)
    cols = []
    for c in range(1, wb.max_col + 1):
        h = wb.headers[c - 1]
        vals = [wb.cell(r, c).raw_value for r in rows]
        kinds = collections.Counter(_kind(v) for v in vals)
        nonnull = [v for v in vals if v is not None]
        is_sensitive = h is not None and slug(h).replace("_", " ") in sens
        free_text = sum(1 for v in nonnull if isinstance(v, str))
        col = {
            "col": c,
            "letter": wb.rows[rows[0]][c - 1].ref.col_letter,
            "header": h,
            "untitled": h is None,
            "repeated_header": bool(h) and header_counts[slug(h)] > 1,
            "n_filled": len(nonnull),
            "n_empty": len(vals) - len(nonnull),
            "kinds": dict(kinds),
            "mixed_types": len([k for k in kinds if k != "vide"]) > 1,
            "n_distinct": len({str(v) for v in nonnull}),
            "sensitive": is_sensitive,
        }
        if not is_sensitive:
            cnt = collections.Counter(str(v) if not isinstance(v, datetime) else "<date>" for v in nonnull)
            shown = [(k, n) for k, n in cnt.most_common(top_k) if n >= min_count_to_show]
            col["frequent_values"] = shown
            col["n_values_hidden_rare"] = sum(n for k, n in cnt.items() if n < min_count_to_show)
            nums = [v for v in nonnull if isinstance(v, (int, float)) and not isinstance(v, bool)]
            if nums and free_text == 0 and len({*nums}) > 10:
                s = sorted(nums)
                col["numeric_summary"] = {"min": s[0], "median": s[len(s) // 2], "max": s[-1]}
        cols.append(col)
    return {
        "file_sha256": wb.sha256,
        "sheet": wb.sheet,
        "n_header_columns": wb.max_col,
        "n_rows_in_sheet_extent": wb.max_row,
        "n_filled_data_rows": len(rows),
        "n_empty_rows_in_extent": len(wb.empty_rows),
        "untitled_columns": [c["letter"] for c in cols if c["untitled"]],
        "untitled_columns_with_data": [c["letter"] for c in cols if c["untitled"] and c["n_filled"]],
        "repeated_headers": sorted({c["header"] for c in cols if c["repeated_header"]}),
        "n_mixed_type_columns": sum(c["mixed_types"] for c in cols),
        "columns": cols,
    }


def profile_markdown(p: dict) -> str:
    lines = [
        "# Profil agrégé du registre",
        "",
        f"- Empreinte SHA-256 : `{p['file_sha256']}`",
        f"- Feuille : {p['sheet']}",
        f"- Colonnes : {p['n_header_columns']} ; lignes dans l'étendue : {p['n_rows_in_sheet_extent']} "
        f"(dont en-tête) ; lignes renseignées : {p['n_filled_data_rows']} ; lignes vides : {p['n_empty_rows_in_extent']}",
        f"- Colonnes sans titre : {', '.join(p['untitled_columns'])} (avec données : {', '.join(p['untitled_columns_with_data'])})",
        f"- Intitulés répétés : {', '.join(p['repeated_headers'])}",
        f"- Colonnes à types mélangés : {p['n_mixed_type_columns']}",
        "",
        "Les valeurs des colonnes identifiantes ne sont pas affichées ; pour les autres, seules les valeurs "
        "présentes au moins 3 fois le sont.",
        "",
        "| Col. | Intitulé | Renseignées | Types | Distinctes | Valeurs fréquentes |",
        "|---|---|---|---|---|---|",
    ]
    for c in p["columns"]:
        kinds = ", ".join(f"{k} {n}" for k, n in c["kinds"].items() if k != "vide")
        freq = "(masqué : colonne sensible)" if c["sensitive"] else "; ".join(f"{k} ×{n}" for k, n in c.get("frequent_values", []))
        h = (c["header"] or "(sans titre)").replace("|", "/")
        flag = " ⟲" if c["repeated_header"] else ""
        lines.append(f"| {c['letter']} | {h}{flag} | {c['n_filled']} | {kinds} | {c['n_distinct']} | {freq} |")
    lines.append("")
    lines.append("⟲ = intitulé répété ailleurs dans la feuille.")
    return "\n".join(lines)
