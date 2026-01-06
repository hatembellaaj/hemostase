#!/usr/bin/env python3
"""CSV importer for fiche hémophilie data with anonymised identities.

The script expects a CSV file where columns use the simplified names listed
in ``COLUMN_MAP`` below. Mandatory values are:
- patient_prenom
- patient_nom
- patient_sexe
- patient_etat_civil
- patient_couverture_sociale
- patient_diagnostic
- fiche_dossier_number
- fiche_prise_en_charge

Names are hashed before they are sent to the API so raw identities never
reach the database.
"""
from __future__ import annotations

import argparse
import csv
import hashlib
import os
import sys
from typing import Any, Dict

import requests

# Mapping between incoming CSV columns and DTO fields.
COLUMN_MAP = {
    # Patient columns
    "patient_prenom": "prenom",
    "patient_nom": "nom",
    "patient_sexe": "sexe",
    "patient_etat_civil": "etatCivil",
    "patient_couverture_sociale": "couvertureSociale",
    "patient_diagnostic": "diagnostic",
    "patient_nom_jeune_fille": "nomJeuneFille",
    "patient_profession": "profession",
    "patient_origine": "origine",
    "patient_autre_origine": "autreOrigine",
    "patient_adresse": "adresse",
    "patient_telephone": "telephone",
    "patient_prenom_pere": "prenomPere",
    "patient_profession_pere": "professionPere",
    "patient_nom_prenom_mere": "nomPrenomMere",
    "patient_profession_mere": "professionMere",
    "patient_annee_diagnostic": "anneeDiagnostic",
    "patient_consentement_registre": "consentementRegistre",
    # Fiche hémophilie columns
    "fiche_dossier_number": "dossierNumber",
    "fiche_prene_en_charge": "priseEnCharge",
    "fiche_prise_en_charge": "priseEnCharge",  # preferred spelling
    "fiche_ordre_number": "ordreNumber",
    "fiche_index_number": "indexNumber",
    "fiche_forme_hemophilie": "formeHemophilie",
    "fiche_date_test_confirmation": "dateTestConfirmation",
    "fiche_nbre_freres": "nbreFreres",
    "fiche_nbre_soeurs": "nbreSoeurs",
    "fiche_debut_prophylaxie": "debutProphylaxie",
    "fiche_type_traitement_substitutif": "typeTraitementSubstitutif",
    "fiche_serologie_hbc": "serologieHBC",
    "fiche_serologie_hvc": "serologieHVC",
    "fiche_serologie_hiv": "serologieHIV",
}

ENUM_FIELDS = {
    "sexe",
    "etatCivil",
    "couvertureSociale",
    "diagnostic",
    "origine",
    "consentementRegistre",
    "priseEnCharge",
    "formeHemophilie",
    "typeTraitementSubstitutif",
    "serologieHBC",
    "serologieHVC",
    "serologieHIV",
}

API_PATIENTS = "/api/patients"
API_FICHES = "/api/fiche-hemophilies"

PATIENT_FIELDS = {
    "prenom",
    "nom",
    "nomJeuneFille",
    "profession",
    "sexe",
    "dateNaissance",
    "ageActuel",
    "origine",
    "autreOrigine",
    "adresse",
    "telephone",
    "etatCivil",
    "couvertureSociale",
    "prenomPere",
    "professionPere",
    "nomPrenomMere",
    "professionMere",
    "diagnostic",
    "autreDiagnostic",
    "anneeDiagnostic",
    "consentementRegistre",
}


def anonymise(value: str, salt: str) -> str:
    if not value:
        return value
    raw = f"{salt}{value.strip()}".encode()
    return hashlib.sha256(raw).hexdigest()[:12]


def normalise_enum(value: str | None) -> str | None:
    if not value:
        return None
    return value.strip().upper().replace(" ", "_")


def map_row(row: Dict[str, str], salt: str) -> Dict[str, Dict[str, Any]]:
    patient_payload: Dict[str, Any] = {}
    fiche_payload: Dict[str, Any] = {}

    for csv_key, dto_key in COLUMN_MAP.items():
        if csv_key not in row or row[csv_key] == "":
            continue

        if dto_key in {"prenom", "nom", "nomJeuneFille", "prenomPere", "nomPrenomMere"}:
            patient_payload[dto_key] = anonymise(row[csv_key], salt)
            continue

        value: Any = row[csv_key].strip()
        if dto_key in ENUM_FIELDS:
            value = normalise_enum(value)

        if dto_key in PATIENT_FIELDS:
            patient_payload[dto_key] = value
        else:
            fiche_payload[dto_key] = value

    return {"patient": patient_payload, "fiche": fiche_payload}


def post_json(api_base: str, token: str, path: str, payload: Dict[str, Any]) -> Dict[str, Any]:
    response = requests.post(
        f"{api_base}{path}",
        json=payload,
        headers={"Authorization": f"Bearer {token}", "Content-Type": "application/json"},
        timeout=30,
    )
    try:
        response.raise_for_status()
    except requests.HTTPError as exc:  # pragma: no cover - utility script
        print(f"HTTP {response.status_code} on {path}: {response.text}", file=sys.stderr)
        raise exc
    return response.json()


def import_csv(csv_path: str, api_base: str, token: str, salt: str) -> None:
    with open(csv_path, newline="", encoding="utf-8") as handle:
        reader = csv.DictReader(handle)
        for index, row in enumerate(reader, start=1):
            mapped = map_row(row, salt)
            patient = post_json(api_base, token, API_PATIENTS, mapped["patient"])
            fiche_payload = mapped["fiche"]
            fiche_payload["patientId"] = patient["id"]
            post_json(api_base, token, API_FICHES, fiche_payload)
            print(f"Imported ligne {index}: patient={patient['id']}")


def main() -> int:
    parser = argparse.ArgumentParser(description="Import fiche hémophilie CSV into Hemostase API")
    parser.add_argument("csv", help="CSV file exported from Excel")
    parser.add_argument("--api-base", default=os.getenv("HEMOSTASE_API", "http://localhost:8080"), help="Base URL of the Hemostase API")
    parser.add_argument("--token", default=os.getenv("HEMOSTASE_TOKEN"), help="JWT token with write access")
    parser.add_argument("--salt", default=os.getenv("HEMOSTASE_SALT", ""), help="Salt used before hashing names")
    args = parser.parse_args()

    if not args.token:
        print("A JWT token is required (--token or HEMOSTASE_TOKEN)", file=sys.stderr)
        return 1

    import_csv(args.csv, args.api_base.rstrip("/"), args.token, args.salt)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
