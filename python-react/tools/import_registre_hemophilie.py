#!/usr/bin/env python3
"""
Import du registre Excel (feuille « Feuil1 », 165 colonnes codées) dans la table fiche_hemophilie,
en ligne de commande. Même code que l'import depuis l'interface (backend/app/import_hemophilie.py).

Usage :
    pip install openpyxl
    python tools/import_registre_hemophilie.py registre.xlsx import_hemophilie.sql controle_import.xlsx
"""
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent.parent / "backend"))

from app.import_hemophilie import main  # noqa: E402

if __name__ == "__main__":
    if len(sys.argv) != 4:
        print(__doc__)
        sys.exit(1)
    main(*sys.argv[1:])
