#!/usr/bin/env python3
"""
Import du registre Excel (feuille « Feuil1 », 165 colonnes codées) dans la table fiche_hemophilie.

Produit :
  - un script SQL (INSERT) à exécuter dans la base MariaDB de l'application ;
  - un classeur de contrôle : correspondance des colonnes, codes décodés, alertes par patient,
    aperçu des fiches générées.

Usage :
    pip install openpyxl
    python tools/import_registre_hemophilie.py registre.xlsx import_hemophilie.sql controle_import.xlsx

Chaque fiche est validée avec les règles de l'application (énumérations, champs obligatoires,
longueurs maximales) avant d'être écrite dans le SQL.
"""
from __future__ import annotations

import json
import re
import sys
import unicodedata
from datetime import date, datetime, timedelta
from pathlib import Path

import openpyxl
from openpyxl.styles import Alignment, Font, PatternFill

# ----------------------------------------------------------------------------------------
# Paramètres à ajuster
# ----------------------------------------------------------------------------------------
# Champ obligatoire de l'application absent du fichier Excel
SERVICE_CLINIQUE_DEFAUT = "HTO"
# Valeur numérique enregistrée pour un taux de facteur noté « <1 » / « <1% »
VALEUR_INFERIEURE_A_1 = 0.5
DIAGNOSTIC = "HEMOPHILIE"

SCHEMA = Path(__file__).resolve().parent.parent / "backend" / "app" / "entities.json"
ENT = next(e for e in json.loads(SCHEMA.read_text(encoding="utf-8"))["entities"] if e["slug"] == "hemophilie")
FIELDS = {f["name"]: f for f in ENT["fields"]}

# ----------------------------------------------------------------------------------------
# Décodage des codes numériques du fichier Excel
# (déduits du croisement des colonnes ; voir l'onglet « Codes » du classeur de contrôle)
# ----------------------------------------------------------------------------------------
OUI_NON_NP = {1: "OUI", 2: "NON", 3: "NP"}
BOOL_12 = {1: True, 2: False}
REGION = {1: "SFAX", 2: "GABES", 3: "GAFSA", 4: "KEBILI", 5: "TOZEUR", 6: "MEDNINE", 7: "TATAOUINE",
          8: "SBZ", 9: "KASSERINE", 10: "MAHDIA", 11: "KAIRAOUANE", 12: "MONASTIR", 13: "SOUSSE", 14: "AUTRE"}
SEXE = {1: "MASCULIN", 2: "FEMININ"}
REGIME = {1: "CNAM", 2: "INDIGENT", 3: "NP"}
FORME_HEMO = {1: "SPORADIQUE", 2: "HEREDITAIRE", 3: "NP"}
CDD = {1: "FORTUITE", 2: "FAMILIALE", 3: "SYNDROME_HEMORRAGIQUE"}
FORME_CLINIQUE = {1: "A", 2: "B"}
SEVERITE = {1: "SEVERE", 2: "MODEREE", 3: "MINEURE"}
SPONT_PROV = {1: "SPONTANE", 2: "PROVOQUE"}
SUITE_OP = {1: "SIMPLE", 2: "HEMORRAGIQUE", 3: "NP"}
MUQUEUSES = {1: "CHUTE_DENTAIRE", 2: "PLAIE_LANGUE", 3: "GINGIVORRAGIES", 4: "EPISTAXIS", 5: "ECHYMOSES"}
VISCERES = {1: "HEMATEMESE", 2: "RECTORRAGIE", 3: "HEMATURIE"}
ACC_GRAVES = {1: "HEMATOMES_CEREBRALES", 2: "HEMOTHORAX", 3: "EDC_HEMORRAGIQUE", 4: "HEMATOME_COMPRESSIF"}
PEC = {1: "PROPHYLAXIE", 2: "A_LA_DEMANDE"}
PLASMA_RECOMB = {1: "PLASMATIQUE", 2: "RECOMBINANT"}
REPONDEUR = {1: "FAIBLE_REPONDEUR", 2: "FORT_REPONDEUR"}
SEROLOGIE = {1: "POSITIVE", 2: "NEGATIVE", 3: "NP"}
VIE_SOCIALE = {1: "CHOMEUR", 2: "SCOLARISE", 3: "TRAVAILLE"}
EVOL_INHIB = {1: "DISPARITION", 2: "PERSISTANCE"}
ORTHO_TYPE = {1: "synovite", 2: "arthropathieHemophiliqueChronique", 3: "pseudotumeurs", 4: "fractures"}

# Colonnes Excel attendues (index, en-tête) : protège contre un fichier dont les colonnes auraient bougé
EXPECTED = {0: "Nom et prénom", 1: "origine", 4: "date de naissance", 7: "sexe", 37: "sévérité",
            40: "hémarthrose", 90: "PSL:oui/non", 124: "inhibiteur", 152: "vie social", 161: "DDN"}


# ----------------------------------------------------------------------------------------
def norm(s) -> str:
    s = unicodedata.normalize("NFKD", str(s)).encode("ascii", "ignore").decode()
    return re.sub(r"\s+", " ", s.lower()).strip()


def is_empty(v) -> bool:
    return v is None or (isinstance(v, str) and norm(v) in ("", "?", "??", "???", "????", "np", "nf", "nf?", "non trouve"))


def as_int(v):
    if isinstance(v, bool):
        return None
    if isinstance(v, (int, float)) and float(v).is_integer():
        return int(v)
    if isinstance(v, str) and re.fullmatch(r"\s*\d+\s*", v):
        return int(v)
    return None


def codes(v) -> list[int]:
    """'1 2 3' -> [1,2,3] ; 12 -> [1,2] ; 2.4 -> [2,4] ; 3 -> [3]"""
    if v is None:
        return []
    if isinstance(v, float) and not v.is_integer():
        return [int(c) for c in str(v).replace(".", "") if c.isdigit()]
    s = str(int(v)) if isinstance(v, (int, float)) else str(v)
    if re.fullmatch(r"[\d\s.,]+", s.strip()):
        parts = re.findall(r"\d+", s)
        if len(parts) == 1 and len(parts[0]) > 1 and all(c in "12345" for c in parts[0]):
            return [int(c) for c in parts[0]]
        return [int(p) for p in parts]
    return []


MONTHS = {"janv": 1, "fev": 2, "mars": 3, "avr": 4, "mai": 5, "juin": 6, "juil": 7, "aout": 8,
          "sept": 9, "oct": 10, "nov": 11, "dec": 12}


def as_date(v):
    """datetime, année seule (2018) ou « aout 2018 » -> date ; renvoie (date, approximative?)"""
    if isinstance(v, datetime):
        return v.date(), False
    if isinstance(v, date):
        return v, False
    y = as_int(v)
    if y and 1900 < y < 2100:
        return date(y, 1, 1), True
    if isinstance(v, str):
        n = norm(v)
        m = re.search(r"(19|20)\d{2}", n)
        if m:
            month = next((num for k, num in MONTHS.items() if k in n), 1)
            return date(int(m.group(0)), month, 1), True
    return None, False


def duration_days(v):
    """'2 mois' / '1 an' / '18 MOIS' / '1 jour' / '4 ans' -> nombre de jours (approx.)"""
    if v is None:
        return None
    n = norm(v)
    if n in ("naissance", "a la naissance", "neonatal"):
        return 0
    m = re.search(r"(\d+(?:[.,]\d+)?)\s*(jour|j\b|mois|an)", n)
    if not m:
        return None
    x = float(m.group(1).replace(",", "."))
    unit = m.group(2)
    return round(x * (1 if unit.startswith("j") else 30.44 if unit == "mois" else 365.25))


def factor_level(v):
    """Taux de facteur en % : 0.3 / '0.40%' / '<1' / '<1%' / '???'"""
    if v is None:
        return None, None
    if isinstance(v, (int, float)):
        return float(v), None
    n = norm(v).replace("%", "").replace(",", ".")
    if n.startswith("<"):
        return VALEUR_INFERIEURE_A_1, f"« {v} » enregistré comme {VALEUR_INFERIEURE_A_1}"
    try:
        return float(n), None
    except ValueError:
        return None, f"valeur illisible « {v} »"


def tca(v):
    """'64/30' -> 64 (temps du patient, en secondes)"""
    if v is None:
        return None
    m = re.match(r"\s*(\d+(?:[.,]\d+)?)", str(v))
    return float(m.group(1).replace(",", ".")) if m else None


def oui_non_text(v):
    if v is None:
        return None
    if isinstance(v, (int, float)):
        return OUI_NON_NP.get(int(v))
    n = norm(v)
    if n.startswith("oui"):
        return "OUI"
    if n.startswith("non") or n == "pas encore":
        return "NON"
    if is_empty(v):
        return "NP"
    return None


# Articulations : texte libre -> booléens coudeDroit... (complications orthopédiques)
JOINTS = {"coude": ("coudeDroit", "coudeGauche"), "genou": ("genouDroit", "genouGauche"),
          "cheville": ("chevilleDroite", "chevilleGauche"), "epaule": ("epauleDroite", "epauleGauche"),
          "poignet": ("poignetDroit", "poignetGauche"), "hanche": ("hancheDroite", "hancheGauche")}


def parse_joints(text) -> tuple[dict, list[str]]:
    out, doubts = {}, []
    if text is None:
        return out, doubts
    n = norm(text)
    if "toutes les articulations" in n:
        return {f: True for pair in JOINTS.values() for f in pair}, doubts
    for chunk in re.split(r",|\+| et |;", n):
        for key, (right, left) in JOINTS.items():
            if not re.search(key[:-1] if key.endswith("e") else key, chunk):
                continue
            if re.search(r"\b2\s*" + key[:4], chunk) or "2 " + key[:4] in chunk:
                out[right] = out[left] = True
            elif re.search(r"\b(dt|dte|droit|droite)\b", chunk):
                out[right] = True
            elif re.search(r"\b(gche|gauche|g)\b", chunk):
                out[left] = True
            else:
                doubts.append(f"côté non précisé : « {chunk.strip()} »")
    return out, doubts


def symptom_flags(*texts) -> dict:
    """Texte des symptômes révélateurs -> cases à cocher de « Circonstances de découverte »"""
    n = " ".join(norm(t) for t in texts if t)
    flags = {}
    rules = [
        ("hemorragiePostCirconcision", r"circoncision"),
        ("hemarthrose", r"hemarthrose"),
        ("hematomePostTraumatique", r"hemato?me.*(traumat|forceps|prel|prlvt|injection)|(traumat).*hemato?me"),
        ("plaiePostTraumatique", r"plaie"),
        ("cutaneoMuqueux", r"e?c?chymose|gingivorr|epistaxis|langue|labial|otorragie|chute dentaire"),
        ("hemorragiePostChirurgie", r"chirurg|operatoire|extraction|biopsie"),
        ("hemorragieCerebrale", r"cerebr"),
        ("hemorragieDigestive", r"digesti|rectorr|hematemese|melena"),
    ]
    for field, rx in rules:
        if re.search(rx, n):
            flags[field] = True
    return flags


# ----------------------------------------------------------------------------------------
# Correspondance colonne Excel -> champ de la fiche (pour l'onglet « Correspondance »)
# ----------------------------------------------------------------------------------------
MAPPING = [
    # (index, champ(s) cible, règle, statut)
    (0, "nom, prenom", "Dernier mot = prénom (initiale), le reste = nom", "Repris"),
    (1, "origine", "Code 1..14 = ordre de l'énumération Region (1 SFAX, 3 GAFSA, 6 MEDNINE, 7 TATAOUINE, 8 SBZ…)", "Repris"),
    (2, "adresse", "Ville (tronquée à 25 caractères)", "Repris"),
    (3, "dossierNumber", "« ??? » ignoré", "Repris"),
    (4, "dateNaissance", "", "Repris"),
    (5, "ageActuel", "Recalculé par l'application à partir de la date de naissance", "Recalculé"),
    (6, "—", "Tranche d'âge : se déduit de l'âge", "Non repris"),
    (7, "sexe", "1 MASCULIN, 2 FEMININ", "Repris"),
    (8, "couvertureSociale", "1 CNAM, 2 INDIGENT, vide/« pas de carnet » NP (champ obligatoire)", "Repris"),
    (9, "parentsConsanguins", "1 OUI, 2 NON, 3 NP", "Repris"),
    (10, "degreParenteConsanguins", "Texte", "Repris"),
    (11, "casSimilaires", "1 OUI, 2 NON, 3 NP", "Repris"),
    (12, "decesSyndromeHemorragique", "1 OUI, 2 NON, 3 NP", "Repris"),
    (13, "—", "Cause du décès familial : pas de champ dans la fiche", "Non repris"),
    (14, "degreParenteCasSimilaires", "Texte -> FRERE (frère), COUSIN (cousin), LOINTAIN (oncle et au-delà) ; le plus proche si plusieurs", "Dérivé"),
    (15, "—", "Fratrie oui/non : redondant avec le nombre de frères et sœurs", "Non repris"),
    (16, "fratrieNbFreres", "Nombre", "Repris"),
    (17, "fratrieNbSoeurs", "Nombre", "Repris"),
    (18, "—", "Bilan chez la mère : pas de champ", "Non repris"),
    (19, "—", "Résultat du bilan maternel : pas de champ", "Non repris"),
    (20, "—", "Mère conductrice : pas de champ", "Non repris"),
    (21, "formeHemophilie", "1 SPORADIQUE, 2 HEREDITAIRE", "Repris"),
    (22, "datePremierSigneClinique", "Âge texte (« 2 mois ») ajouté à la date de naissance -> date approximative", "Dérivé"),
    (23, "cases « Circonstances de découverte »", "Mots-clés (circoncision, hémarthrose, ecchymoses…) -> cases à cocher", "Dérivé"),
    (24, "ageDiagnosticJours", "Uniquement si exprimé en jours (« 1 jour »)", "Dérivé"),
    (25, "ageDiagnosticAnnees, ageDiagnosticMois, anneeDiagnostic", "Âge au diagnostic en mois -> années + mois ; année = naissance + âge", "Dérivé"),
    (26, "circonstancesDecouverte", "1 FORTUITE (bilan préop), 2 FAMILIALE (frère hémophile), 3 SYNDROME_HEMORRAGIQUE (champ obligatoire)", "Repris"),
    (27, "cases « Circonstances de découverte »", "Idem colonne « symptomes »", "Dérivé"),
    (28, "tcaMT", "« 64/30 » -> 64 s (temps du patient)", "Repris"),
    (29, "—", "Ratio TCA : pas de champ (se recalcule)", "Non repris"),
    (30, "tcaMTTotal", "« 31/30 » -> 31 s", "Repris"),
    (31, "—", "Globules blancs : pas de champ", "Non repris"),
    (32, "hb", "g/dl", "Repris"),
    (33, "plaquettes", "/mm3", "Repris"),
    (34, "dosageFacteurVIII", f"%, « <1 » -> {VALEUR_INFERIEURE_A_1}", "Repris"),
    (35, "dosageFacteurIX", f"%, « <1 » -> {VALEUR_INFERIEURE_A_1}", "Repris"),
    (36, "formeClinique", "1 A, 2 B (confirmé par les taux de FIX)", "Repris"),
    (37, "severite", "1 SEVERE, 2 MODEREE, 3 MINEURE (confirmé par les taux de facteur)", "Repris"),
    (38, "—", "Vaccination HBV : pas de champ", "Non repris"),
    (39, "—", "Calendrier vaccinal : pas de champ", "Non repris"),
    (40, "hemarthroseMC", "1 OUI, 2 NON", "Repris"),
    (41, "hemarthroseSiege", "Colonnes « dt / gche » des 6 articulations (1 = atteinte) -> liste des articulations", "Repris"),
    (43, "—", "Colonnes de synthèse « coude », « genou »… : redondantes avec dt/gche", "Non repris"),
    (59, "hematomeSuperficiel", "1 OUI, 2 NON", "Repris"),
    (60, "hematomeSuperficielType", "1 SPONTANE, 2 PROVOQUE", "Repris"),
    (62, "—", "Hématome profond : pas de champ (le psoas a sa propre colonne)", "Non repris"),
    (63, "hematomeSuperficielSiege", "Texte tronqué à 25 caractères", "Repris"),
    (64, "hematomePsoas", "1 OUI, 2 NON", "Repris"),
    (65, "—", "Spontané/provoqué du psoas : le champ de l'application attend un type de saignement muqueux (incohérent)", "Non repris"),
    (66, "hematomePsoasRecidive", "1/oui OUI, 2/non NON", "Repris"),
    (67, "saignementSNC", "1 OUI, 2 NON", "Repris"),
    (68, "saignementSNCEvolution", "« bonne »/1 -> REGRESSION ; autre texte non repris", "Dérivé"),
    (69, "hemorragiesCutaneoMuqueuses", "1 OUI, 2 NON", "Repris"),
    (70, "hemorragiesCutaneoMuqueusesTypes", "Codes 1 chute dentaire, 2 plaie langue, 3 gingivorragies, 4 épistaxis ; un seul type possible -> le premier", "Repris"),
    (71, "—", "Ecchymoses (et colonnes 72-73) : pas de champ dédié", "Non repris"),
    (74, "hemorragieVisceres", "1 OUI, 2 NON", "Repris"),
    (75, "hemorragieVisceresType", "1 HEMATEMESE, 2 RECTORRAGIE, 3 HEMATURIE ; texte « hématurie »… reconnu ; un seul type -> le premier", "Repris"),
    (76, "hemorragieVisceresExploration", "1/oui/texte -> OUI", "Repris"),
    (77, "hemorragieVisceresExamen", "Texte tronqué à 25 caractères", "Repris"),
    (78, "circoncision", "1 OUI, 2 NON, 3 NP, « pas encore » NON", "Repris"),
    (79, "circoncisionPreparation", "1 OUI, 2 NON", "Repris"),
    (80, "circoncisionSuiteOperatoire", "1 SIMPLE, 2 HEMORRAGIQUE (« hématome de verge » -> HEMORRAGIQUE)", "Repris"),
    (81, "circoncisionAge", "En années (« 18 mois » -> 1)", "Repris"),
    (82, "avulsionDentaire", "1 OUI, 2 NON", "Repris"),
    (83, "avulsionDentairePreparation", "1 OUI, 2 NON", "Repris"),
    (84, "interventionChirurgicale", "1 OUI, 2 NON", "Repris"),
    (85, "—", "Préparation de l'intervention : pas de champ", "Non repris"),
    (86, "interventionChirurgicaleType", "Texte tronqué à 25 caractères", "Repris"),
    (87, "accidentsHemorragiquesGraves", "1 OUI, 2 NON", "Repris"),
    (88, "accidentsHemorragiquesGravesTypes", "1 hématome cérébral, 2 hémothorax, 3 EDC hémorragique, 4 hématome compressif (« sd des loges » -> compressif) ; un seul type", "Repris"),
    (90, "antecedentsTraitementPSL", "1 OUI, 2 NON (sinon colonne « ATCD de ttt par PSL »)", "Repris"),
    (91, "cryoprecipite", "OUI si « cryo seul », « cryo puis facteur » ou « cryo+facteur » = 1", "Dérivé"),
    (94, "plasmaFraisCongele", "OUI si l'une des 3 colonnes PFC = 1", "Dérivé"),
    (97, "typeTraitementSubstitutif", "« FVIII » / « FIX » / « PPSB » + nom du produit (novoeight, refacto…)", "Dérivé"),
    (101, "agePremiereSubstitution", "Année de début - année de naissance", "Dérivé"),
    (102, "—", "Année de début par facteur : pas de champ", "Non repris"),
    (103, "priseEnChargeTherapeutique", "1 PROPHYLAXIE, 2 A_LA_DEMANDE (et non plasmatique/recombinant)", "Repris"),
    (104, "modaliteTraitement", "1 PLASMATIQUE, 2 RECOMBINANT (confirmé par les noms : novoeight, refacto = 2)", "Repris"),
    (105, "typeTraitementSubstitutif", "Nom du produit", "Repris"),
    (106, "causePriseEnCharge", "Texte tronqué à 25 caractères", "Repris"),
    (107, "prophylaxieDoseFrequence", "Dose + fréquence/semaine (« 20 ui/kg x2/sem »)", "Repris"),
    (109, "dateDebutProphylaxie", "Année seule -> 1er janvier", "Repris"),
    (110, "—", "Observance de la prophylaxie : pas de champ", "Non repris"),
    (111, "hemarthroseFrequenceAn", "Non repris : nombre d'accidents sur 6 mois, unité différente", "Non repris"),
    (112, "augmenterDoseFacteur", "« oui… » -> vrai", "Repris"),
    (113, "orthopediques", "1 OUI, 2 NON", "Repris"),
    (114, "synovite, arthropathieHemophiliqueChronique, pseudotumeurs, fractures", "Codes 1 synovite, 2 arthropathie, 3 pseudotumeurs, 4 fracture (« 2 4 », 24, 2.4 = 2 et 4)", "Repris"),
    (115, "coudeDroit … hancheGauche", "Texte des articulations atteintes -> cases droite/gauche", "Dérivé"),
    (116, "—", "Siège de la fracture : pas de champ", "Non repris"),
    (117, "pecTherapeutique", "Texte : « synoviorthèse » -> SYNOVIORTHOSE, « PTG » -> PTG (aussi colonne « PEC de l'arthropathie »)", "Dérivé"),
    (118, "—", "Articulation cible (codes non documentés)", "Non repris"),
    (119, "—", "Bilan radio, année, scores de Gilbert et Pettersson : pas de champ", "Non repris"),
    (124, "inhibiteurs", "1 OUI, 2 NON, 3 NP", "Repris"),
    (125, "tauxInhibiteur", "1 FAIBLE_REPONDEUR (≤ 2,2 UB), 2 FORT_REPONDEUR (≥ 8 UB)", "Repris"),
    (126, "—", "Titre de l'inhibiteur en UB : le champ de l'application n'est pas numérique", "Non repris"),
    (127, "novoseven, feiba, immurel, augmenterDoseFacteur", "Texte reconnu ; codes chiffrés 3, 4, 5 non documentés -> non repris", "Dérivé"),
    (128, "iti", "oui -> vrai, non -> faux", "Repris"),
    (129, "posologieITI", "Texte tronqué à 25 caractères", "Repris"),
    (130, "observanceITI", "oui/non", "Repris"),
    (131, "causeNonObservance", "Texte", "Repris"),
    (132, "evolutionInhibiteur", "persistance/disparition ; 1 DISPARITION, 2 PERSISTANCE", "Repris"),
    (133, "prophylaxieHemlibra", "1 -> vrai", "Repris"),
    (134, "dateDebutHemlibra", "", "Repris"),
    (135, "schemaTherapeutique", "« mensuel » MENSUEL, « toutes les 2 semaines » BIMENSUEL", "Repris"),
    (136, "efficaciteHemlibra", "Nombre d'accidents / 6 mois", "Repris"),
    (137, "—", "Complication virale : redondant avec les sérologies", "Non repris"),
    (141, "serologieHBC", "1 POSITIVE, 2 NEGATIVE, 3 NP (le champ s'appelle HBC dans l'application)", "Repris"),
    (146, "serologieHVC", "1 POSITIVE, 2 NEGATIVE, 3 NP", "Repris"),
    (147, "dateDecouverteVHC", "Année seule -> 1er janvier", "Repris"),
    (148, "chargeViraleVHC", "Texte", "Repris"),
    (149, "traitementVHC", "oui… -> OUI, non -> NON", "Repris"),
    (150, "evolutionVHC", "disparition/persistance", "Repris"),
    (151, "serologieHIV", "1 POSITIVE, 2 NEGATIVE, 3 NP", "Repris"),
    (152, "vieSociale", "« enfant préscolaire » PRESCOLAIRE, 1 CHOMEUR, 2 SCOLARISE, 3 TRAVAILLE (ordre différent de l'application)", "Repris"),
    (153, "—", "Niveau scolaire : pas de champ", "Non repris"),
    (154, "profession", "Texte (codes chiffrés ignorés), tronqué à 25 caractères", "Repris"),
    (155, "marie, etatCivil", "1 OUI, 2 NON, 3 NP ; état civil (obligatoire) : MARIE / CELIBATAIRE / NP", "Repris"),
    (156, "enfants", "1 OUI, 2 NON (« 1 (père de 2 filles) » -> OUI)", "Repris"),
    (157, "handicap, typeHandicap", "Description du handicap (boiterie, arthropathie…) -> handicap OUI + type ; 2 -> NON", "Dérivé"),
    (158, "activiteSportive", "1 vrai, 2 faux", "Repris"),
    (159, "decede", "1 vrai, 2 faux", "Repris"),
    (160, "causeDeces", "Texte", "Repris"),
    (161, "—", "DDN = date des dernières nouvelles (pas la date de naissance) : pas de champ", "Non repris"),
    (162, "—", "Commentaires libres (colonnes sans en-tête) : pas de champ", "Non repris"),
]


# ----------------------------------------------------------------------------------------
def convert(r: tuple, line: int, alerts: list) -> dict:
    """Une ligne Excel -> dictionnaire {champ: valeur} de la fiche hémophilie."""
    g = lambda i: r[i] if i < len(r) else None  # noqa: E731
    name = str(g(0)).strip()
    warn = lambda champ, val, msg, retenu=None: alerts.append(  # noqa: E731
        {"ligne": line, "patient": name, "champ": champ, "valeur Excel": val, "problème": msg, "valeur retenue": retenu}
    )
    d: dict = {"diagnostic": DIAGNOSTIC, "serviceClinique": SERVICE_CLINIQUE_DEFAUT}

    def enum(field, idx, table):
        v = g(idx)
        if v is None:
            return
        c = as_int(v)
        if c in table:
            d[field] = table[c]
        else:
            o = oui_non_text(v) if table is OUI_NON_NP else None
            if o:
                d[field] = o
            else:
                warn(field, v, "code inconnu, non repris")

    # Identification ------------------------------------------------------------------
    parts = name.split()
    if len(parts) >= 2:
        d["nom"], d["prenom"] = " ".join(parts[:-1]), parts[-1]
    else:
        d["nom"] = name
    enum("origine", 1, REGION)
    if g(2):
        d["adresse"] = str(g(2)).strip()
    if not is_empty(g(3)):
        d["dossierNumber"] = str(g(3)).strip()
    dn, approx = as_date(g(4))
    if dn:
        d["dateNaissance"] = dn
        if approx:
            warn("dateNaissance", g(4), "année seule : date fixée au 1er janvier", dn.isoformat())
    enum("sexe", 7, SEXE)
    if as_int(g(8)) in REGIME:
        d["couvertureSociale"] = REGIME[as_int(g(8))]
    else:
        d["couvertureSociale"] = "NP"
        if g(8) is not None:
            warn("couvertureSociale", g(8), "texte non codé", "NP")
    m = as_int(g(155))
    d["etatCivil"] = {1: "MARIE", 2: "CELIBATAIRE"}.get(m, "NP")

    # Antécédents ---------------------------------------------------------------------
    enum("parentsConsanguins", 9, OUI_NON_NP)
    if g(10) and not is_empty(g(10)):
        d["degreParenteConsanguins"] = str(g(10)).strip()
    enum("casSimilaires", 11, OUI_NON_NP)
    lien = g(14)
    if lien:
        n = norm(lien)
        if re.search(r"\bfr[e]re", n):
            d["degreParenteCasSimilaires"] = "FRERE"
        elif "cousin" in n:
            d["degreParenteCasSimilaires"] = "COUSIN"
        elif "oncle" in n or "tante" in n:
            d["degreParenteCasSimilaires"] = "LOINTAIN"
        else:
            warn("degreParenteCasSimilaires", lien, "lien non reconnu")
    enum("decesSyndromeHemorragique", 12, OUI_NON_NP)
    for f, i in (("fratrieNbFreres", 16), ("fratrieNbSoeurs", 17)):
        if as_int(g(i)) is not None:
            d[f] = as_int(g(i))
    enum("formeHemophilie", 21, FORME_HEMO)

    # Circonstances de découverte -----------------------------------------------------
    c = as_int(g(26))
    if c in CDD:
        d["circonstancesDecouverte"] = CDD[c]
    else:
        guess = "SYNDROME_HEMORRAGIQUE" if (g(23) or g(27)) else "FORTUITE"
        d["circonstancesDecouverte"] = guess
        warn("circonstancesDecouverte", g(26), "absent (champ obligatoire)", guess)
    if d["circonstancesDecouverte"] == "SYNDROME_HEMORRAGIQUE":
        d.update(symptom_flags(g(23), g(27)))
    days = duration_days(g(22))
    if days is not None and dn:
        d["datePremierSigneClinique"] = dn + timedelta(days=days)
    elif g(22) and not is_empty(g(22)):
        warn("datePremierSigneClinique", g(22), "âge non lisible")
    months = as_int(g(25))
    if months is not None:
        d["ageDiagnosticAnnees"], d["ageDiagnosticMois"] = divmod(months, 12)
        if dn:
            d["anneeDiagnostic"] = (dn + timedelta(days=round(months * 30.44))).year
    if g(24) and re.search(r"jour", norm(g(24))):
        d["ageDiagnosticJours"] = duration_days(g(24))

    # Biologie ------------------------------------------------------------------------
    if tca(g(28)) is not None:
        d["tcaMT"] = tca(g(28))
    if tca(g(30)) is not None:
        d["tcaMTTotal"] = tca(g(30))
    if isinstance(g(32), (int, float)):
        d["hb"] = float(g(32))
    if as_int(g(33)) is not None:
        d["plaquettes"] = as_int(g(33))
    for f, i in (("dosageFacteurVIII", 34), ("dosageFacteurIX", 35)):
        val, msg = factor_level(g(i))
        if val is not None:
            d[f] = val
        if msg:
            warn(f, g(i), msg, val)
    enum("formeClinique", 36, FORME_CLINIQUE)
    enum("severite", 37, SEVERITE)

    # Manifestations cliniques ----------------------------------------------------------
    enum("hemarthroseMC", 40, OUI_NON_NP)
    sites = [("Coude droit", 41), ("Coude gauche", 42), ("Genou droit", 44), ("Genou gauche", 45),
             ("Cheville droite", 47), ("Cheville gauche", 48), ("Épaule droite", 50), ("Épaule gauche", 51),
             ("Hanche droite", 53), ("Hanche gauche", 54), ("Poignet droit", 56), ("Poignet gauche", 57)]
    chosen = [lbl for lbl, i in sites if as_int(g(i)) == 1]
    if chosen:
        order = FIELDS["hemarthroseSiege"]["choices"]
        d["hemarthroseSiege"] = ", ".join(sorted(chosen, key=order.index))
    enum("hematomeSuperficiel", 59, OUI_NON_NP)
    enum("hematomeSuperficielType", 60, SPONT_PROV)
    if g(63) and not is_empty(g(63)):
        d["hematomeSuperficielSiege"] = str(g(63)).strip()
    enum("hematomePsoas", 64, OUI_NON_NP)
    if g(66) is not None:
        d["hematomePsoasRecidive"] = oui_non_text(g(66))
    enum("saignementSNC", 67, OUI_NON_NP)
    ev = g(68)
    if ev is not None:
        if as_int(ev) == 1 or norm(ev) == "bonne":
            d["saignementSNCEvolution"] = "REGRESSION"
        else:
            warn("saignementSNCEvolution", ev, "ni régression ni décès, non repris")
    enum("hemorragiesCutaneoMuqueuses", 69, OUI_NON_NP)
    t = g(70)
    if t is not None and d.get("hemorragiesCutaneoMuqueuses") == "OUI":
        cs = [x for x in codes(t) if x in MUQUEUSES]
        if not cs:
            n = norm(t)
            cs_txt = [v for k, v in (("dentaire", "CHUTE_DENTAIRE"), ("langue", "PLAIE_LANGUE"), ("gingivo", "GINGIVORRAGIES"),
                                     ("epistax", "EPISTAXIS"), ("chymose", "ECHYMOSES")) if k in n]
            if cs_txt:
                d["hemorragiesCutaneoMuqueusesTypes"] = cs_txt[0]
                if len(cs_txt) > 1:
                    warn("hemorragiesCutaneoMuqueusesTypes", t, "plusieurs types, un seul possible", cs_txt[0])
            else:
                warn("hemorragiesCutaneoMuqueusesTypes", t, "type non reconnu")
        else:
            d["hemorragiesCutaneoMuqueusesTypes"] = MUQUEUSES[cs[0]]
            if len(cs) > 1:
                warn("hemorragiesCutaneoMuqueusesTypes", t, "plusieurs types, un seul possible", MUQUEUSES[cs[0]])
    enum("hemorragieVisceres", 74, OUI_NON_NP)
    t = g(75)
    if t is not None and d.get("hemorragieVisceres") == "OUI":
        n = norm(t)
        txt = next((v for k, v in (("hematemese", "HEMATEMESE"), ("rectorr", "RECTORRAGIE"), ("hematurie", "HEMATURIE")) if k in n), None)
        cs = [x for x in codes(t) if x in VISCERES] or [int(x) for x in re.findall(r"^\s*(\d)", n) if int(x) in VISCERES]
        if txt:
            d["hemorragieVisceresType"] = txt
        elif cs:
            d["hemorragieVisceresType"] = VISCERES[cs[0]]
            if len(cs) > 1:
                warn("hemorragieVisceresType", t, "plusieurs types, un seul possible", VISCERES[cs[0]])
        else:
            warn("hemorragieVisceresType", t, "type non reconnu")
    if g(76) is not None:
        d["hemorragieVisceresExploration"] = "OUI" if (as_int(g(76)) == 1 or isinstance(g(76), str)) else oui_non_text(g(76))
    if g(77):
        d["hemorragieVisceresExamen"] = str(g(77)).strip()
    if g(78) is not None:
        d["circoncision"] = oui_non_text(g(78))
    enum("circoncisionPreparation", 79, OUI_NON_NP)
    so = g(80)
    if so is not None:
        d["circoncisionSuiteOperatoire"] = SUITE_OP.get(as_int(so)) or ("HEMORRAGIQUE" if re.search(r"hemato|hemorr|saign", norm(so)) else None)
    days = duration_days(g(81))
    if days is not None:
        d["circoncisionAge"] = int(days // 365.25)
        if days < 365:
            warn("circoncisionAge", g(81), "âge inférieur à 1 an : le champ est en années", 0)
    enum("avulsionDentaire", 82, OUI_NON_NP)
    enum("avulsionDentairePreparation", 83, OUI_NON_NP)
    enum("interventionChirurgicale", 84, OUI_NON_NP)
    if g(86):
        d["interventionChirurgicaleType"] = str(g(86)).strip()
    enum("accidentsHemorragiquesGraves", 87, OUI_NON_NP)
    t = g(88)
    if d.get("accidentsHemorragiquesGraves") == "OUI" and t is not None:
        cs = [x for x in codes(t) if x in ACC_GRAVES]
        if cs:
            d["accidentsHemorragiquesGravesTypes"] = ACC_GRAVES[cs[0]]
            if len(cs) > 1:
                warn("accidentsHemorragiquesGravesTypes", t, "plusieurs types, un seul possible", ACC_GRAVES[cs[0]])
        elif re.search(r"loges|compress", norm(t)):
            d["accidentsHemorragiquesGravesTypes"] = "HEMATOME_COMPRESSIF"
        else:
            warn("accidentsHemorragiquesGravesTypes", t, "type non reconnu")
    elif d.get("accidentsHemorragiquesGraves") == "NON" and t is not None:
        warn("accidentsHemorragiquesGravesTypes", t, "type renseigné alors qu'il n'y a pas d'accident grave : ignoré")

    # Prise en charge ---------------------------------------------------------------------
    psl = as_int(g(90))
    d["antecedentsTraitementPSL"] = OUI_NON_NP.get(psl) or oui_non_text(g(100))
    if any(as_int(g(i)) == 1 for i in (91, 92, 93)):
        d["cryoprecipite"] = "OUI"
    elif any(as_int(g(i)) == 2 for i in (91, 92, 93)):
        d["cryoprecipite"] = "NON"
    if any(as_int(g(i)) == 1 for i in (94, 95, 96)):
        d["plasmaFraisCongele"] = "OUI"
    elif any(as_int(g(i)) == 2 for i in (94, 95, 96)):
        d["plasmaFraisCongele"] = "NON"
    prod = [lbl for lbl, i in (("FVIII", 97), ("FIX", 98), ("PPSB", 99)) if as_int(g(i)) == 1]
    nom_produit = str(g(105)).replace("?", "").strip() if g(105) else ""
    tts = " - ".join(x for x in ["/".join(prod), nom_produit] if x)
    if tts:
        d["typeTraitementSubstitutif"] = tts
    y = as_int(g(101))
    if y and dn and 1900 < y < 2100:
        d["agePremiereSubstitution"] = max(0, y - dn.year)
    enum("priseEnChargeTherapeutique", 103, PEC)
    enum("modaliteTraitement", 104, PLASMA_RECOMB)
    if g(106):
        d["causePriseEnCharge"] = str(g(106)).strip()
    dose, freq = g(107), g(108)
    if dose and norm(dose) not in ("non", "oui") and not norm(dose).startswith("non"):
        f = f" x{as_int(freq)}/sem" if as_int(freq) else (f" {freq}" if freq else "")
        d["prophylaxieDoseFrequence"] = (str(dose).strip() + f).strip()
    dd, _ = as_date(g(109))
    if dd:
        d["dateDebutProphylaxie"] = dd
    if g(112) is not None and norm(g(112)).startswith("oui"):
        d["augmenterDoseFacteur"] = True

    # Complications ----------------------------------------------------------------------
    enum("orthopediques", 113, OUI_NON_NP)
    if d.get("orthopediques") == "OUI":
        t = g(114)
        cs = codes(t)
        for x in cs:
            if x in ORTHO_TYPE:
                d[ORTHO_TYPE[x]] = True
        if t is not None and not cs:
            n = norm(t)
            for k, f in (("synovite", "synovite"), ("arthropathie", "arthropathieHemophiliqueChronique"),
                         ("pseudo", "pseudotumeurs"), ("fracture", "fractures")):
                if k in n:
                    d[f] = True
        if g(116) and not d.get("fractures"):
            warn("fractures", g(114), f"siège de fracture renseigné (« {g(116)} ») mais type sans code 4", True)
            d["fractures"] = True
        joints, doubts = parse_joints(g(115))
        d.update(joints)
        for x in doubts:
            warn("articulations", g(115), x)
    pec_txt = " ".join(norm(x) for x in (g(117), g(123)) if x)
    if "synovio" in pec_txt:
        d["pecTherapeutique"] = "SYNOVIORTHOSE"
    elif "ptg" in pec_txt:
        d["pecTherapeutique"] = "PTG"
    enum("inhibiteurs", 124, OUI_NON_NP)
    if d.get("inhibiteurs") == "OUI":
        enum("tauxInhibiteur", 125, REPONDEUR)
        cat = g(127)
        if cat is not None:
            n = norm(cat)
            if isinstance(cat, str) and re.search(r"[a-z]", n):
                if "novoseven" in n:
                    d["novoseven"] = True
                if "feiba" in n:
                    d["feiba"] = True
                if "imurel" in n or "immurel" in n:
                    d["immurel"] = True
                if "augmenter" in n:
                    d["augmenterDoseFacteur"] = True
            else:
                warn("CAT inhibiteur", cat, "code chiffré non documenté, non repris")
        if g(128) is not None:
            d["iti"] = norm(g(128)).startswith("oui")
            if "?" in str(g(128)):
                warn("iti", g(128), "réponse incertaine", d["iti"])
        if g(129):
            d["posologieITI"] = str(g(129)).strip()
        if g(130):
            d["observanceITI"] = oui_non_text(g(130))
        if g(131) and not is_empty(g(131)):
            d["causeNonObservance"] = str(g(131)).strip()
        ev = g(132)
        if ev is not None:
            n = norm(ev)
            if as_int(ev) in EVOL_INHIB:
                d["evolutionInhibiteur"] = EVOL_INHIB[as_int(ev)]
            elif "disparition" in n:
                d["evolutionInhibiteur"] = "DISPARITION"
            elif "persist" in n:
                d["evolutionInhibiteur"] = "PERSISTANCE"
            else:
                warn("evolutionInhibiteur", ev, "texte non reconnu")
    if as_int(g(133)) == 1:
        d["prophylaxieHemlibra"] = True
        dd, _ = as_date(g(134))
        if dd:
            d["dateDebutHemlibra"] = dd
        s = norm(g(135) or "")
        if "mensuel" in s:
            d["schemaTherapeutique"] = "MENSUEL"
        elif "2 semaines" in s:
            d["schemaTherapeutique"] = "BIMENSUEL"
        elif "semaine" in s:
            d["schemaTherapeutique"] = "HEBDOMADAIRE"
        if as_int(g(136)) is not None:
            d["efficaciteHemlibra"] = as_int(g(136))
    enum("serologieHBC", 141, SEROLOGIE)
    enum("serologieHVC", 146, SEROLOGIE)
    dd, approx = as_date(g(147))
    if dd:
        d["dateDecouverteVHC"] = dd
        if approx:
            warn("dateDecouverteVHC", g(147), "année seule", dd.isoformat())
    if g(148) and not is_empty(g(148)):
        d["chargeViraleVHC"] = str(g(148)).strip()
    if g(149) is not None:
        o = oui_non_text(g(149))
        if o:
            d["traitementVHC"] = o
    if g(150):
        n = norm(g(150))
        d["evolutionVHC"] = "DISPARITION" if "disparition" in n else "PERSISTANCE" if "persist" in n else None
    enum("serologieHIV", 151, SEROLOGIE)

    # Évolution ----------------------------------------------------------------------------
    vs = g(152)
    if isinstance(vs, str) and "prescolaire" in norm(vs):
        d["vieSociale"] = "PRESCOLAIRE"
    elif as_int(vs) in VIE_SOCIALE:
        d["vieSociale"] = VIE_SOCIALE[as_int(vs)]
    tr = g(154)
    if isinstance(tr, str) and re.search(r"[a-z]", norm(tr)) and not is_empty(tr):
        d["profession"] = tr.strip().rstrip("?")
    enum("marie", 155, OUI_NON_NP)
    en = g(156)
    if en is not None:
        c = as_int(en) or (int(norm(en)[0]) if norm(en)[:1].isdigit() else None)
        d["enfants"] = OUI_NON_NP.get(c, "NP")
    h = g(157)
    if h is not None:
        if as_int(h) == 2:
            d["handicap"] = "NON"
        elif isinstance(h, str):
            d["handicap"] = "OUI"
            d["typeHandicap"] = h.strip()
    if as_int(g(158)) in BOOL_12:
        d["activiteSportive"] = BOOL_12[as_int(g(158))]
    elif g(158) is not None:
        warn("activiteSportive", g(158), "réponse inconnue")
    if as_int(g(159)) in BOOL_12:
        d["decede"] = BOOL_12[as_int(g(159))]
    if g(160):
        d["causeDeces"] = str(g(160)).strip()

    # Longueurs maximales de l'application ---------------------------------------------------
    for k, v in list(d.items()):
        f = FIELDS.get(k)
        if v is None:
            del d[k]
            continue
        if f and f["type"] == "string" and isinstance(v, str) and len(v) > f.get("maxLength", 255):
            short = v[: f["maxLength"]]
            warn(k, v, f"texte de {len(v)} caractères tronqué à {f['maxLength']}", short)
            d[k] = short
    # âge actuel : même calcul que l'application
    if "dateNaissance" in d:
        t = date.today()
        b = d["dateNaissance"]
        d["ageActuel"] = t.year - b.year - ((t.month, t.day) < (b.month, b.day))
    return d


def validate(d: dict) -> list[str]:
    errs = []
    for name, f in FIELDS.items():
        v = d.get(name)
        if v is None:
            if f.get("required"):
                errs.append(f"{name} obligatoire manquant")
            continue
        if f["type"] == "enum" and v not in {o["value"] for o in f["options"]}:
            errs.append(f"{name} : valeur « {v} » hors énumération")
        if f["type"] == "boolean" and not isinstance(v, bool):
            errs.append(f"{name} : booléen attendu")
        if f["type"] == "integer" and not isinstance(v, int):
            errs.append(f"{name} : entier attendu")
    unknown = set(d) - set(FIELDS)
    if unknown:
        errs.append(f"champs inconnus : {unknown}")
    return errs


def sql_literal(v) -> str:
    if v is None:
        return "NULL"
    if isinstance(v, bool):
        return "1" if v else "0"
    if isinstance(v, (int, float)):
        return repr(v)
    if isinstance(v, date):
        return f"'{v.isoformat()}'"
    return "'" + str(v).replace("\\", "\\\\").replace("'", "''") + "'"


# ----------------------------------------------------------------------------------------
def main(xlsx: str, out_sql: str, out_report: str) -> None:
    ws = openpyxl.load_workbook(xlsx, data_only=True).worksheets[0]
    rows = list(ws.iter_rows(values_only=True))
    header = rows[0]
    for i, h in EXPECTED.items():
        if norm(header[i] or "") != norm(h):
            sys.exit(f"Colonne {i + 1} : « {header[i]} » trouvé, « {h} » attendu. Le fichier n'a pas le format prévu.")

    alerts: list[dict] = []
    fiches: list[tuple[int, dict]] = []
    for line, r in enumerate(rows[1:], start=2):
        if not any(v is not None for v in r) or not r[0]:
            continue
        d = convert(r, line, alerts)
        errs = validate(d)
        if errs:
            sys.exit(f"Ligne {line} ({r[0]}) invalide : {errs}")
        fiches.append((line, d))

    # doublons probables : même nom et même date de naissance
    seen = {}
    for line, d in fiches:
        key = (norm(d.get("nom", "")), norm(d.get("prenom", "")), d.get("dateNaissance"))
        if key in seen:
            alerts.append({"ligne": line, "patient": f"{d.get('nom')} {d.get('prenom')}", "champ": "—",
                           "valeur Excel": f"identique à la ligne {seen[key]}", "problème": "doublon probable (même nom et même date de naissance)",
                           "valeur retenue": "importé quand même"})
        seen[key] = line

    # ---------- SQL ----------
    names = [f["name"] for f in ENT["fields"]]
    used = [n for n in names if any(n in d for _, d in fiches)]
    cols = ", ".join(f"`{FIELDS[n]['column']}`" for n in used)
    with open(out_sql, "w", encoding="utf-8") as fh:
        fh.write(f"-- Import du registre Excel dans fiche_hemophilie : {len(fiches)} fiches\n")
        fh.write(f"-- Généré le {datetime.now():%Y-%m-%d %H:%M} par tools/import_registre_hemophilie.py\n")
        fh.write(f"-- service_clinique non présent dans l'Excel : valeur par défaut '{SERVICE_CLINIQUE_DEFAUT}'\n")
        fh.write("SET NAMES utf8mb4;\n\n")
        fh.write("-- 1) Champs de texte libre élargis à 255 caractères (VARCHAR(64) dans une base créée avant cette version)\n")
        for f in ENT["fields"]:
            if f["type"] == "string" and f.get("maxLength", 0) >= 255:
                fh.write(f"ALTER TABLE fiche_hemophilie MODIFY `{f['column']}` TEXT NULL;\n")
        fh.write("\n-- 2) Fiches (tout ou rien)\nSTART TRANSACTION;\n\n")
        for line, d in fiches:
            vals = ", ".join(sql_literal(d.get(n)) for n in used)
            fh.write(f"-- ligne Excel {line} : {d.get('nom')} {d.get('prenom', '')}\n")
            fh.write(f"INSERT INTO fiche_hemophilie ({cols}) VALUES ({vals});\n")
        fh.write("\nCOMMIT;\n")

    # ---------- Classeur de contrôle ----------
    wb = openpyxl.Workbook()
    head_font = Font(bold=True, color="FFFFFF")
    head_fill = PatternFill("solid", fgColor="1C2433")
    status_fill = {"Repris": "E3F0F0", "Dérivé": "FFF4D6", "Recalculé": "E3F0F0", "Non repris": "F3F5F8"}

    def sheet(title, headers, data, widths):
        s = wb.create_sheet(title)
        s.append(headers)
        for c in s[1]:
            c.font, c.fill = head_font, head_fill
        for row in data:
            s.append(row)
        for i, w in enumerate(widths, start=1):
            s.column_dimensions[openpyxl.utils.get_column_letter(i)].width = w
        for row in s.iter_rows(min_row=2):
            for c in row:
                c.alignment = Alignment(wrap_text=True, vertical="top")
        s.freeze_panes = "A2"
        return s

    wb.remove(wb.active)
    filled = {n: sum(1 for _, d in fiches if n in d) for n in names}
    mapping_rows = []
    for idx, target, rule, status in MAPPING:
        targets = [t.strip() for t in target.split(",")]
        label = "; ".join(FIELDS[t]["label"] for t in targets if t in FIELDS)
        count = max((filled.get(t, 0) for t in targets if t in FIELDS), default="")
        mapping_rows.append([openpyxl.utils.get_column_letter(idx + 1), header[idx], target, label, rule, status, count])
    s = sheet("Correspondance", ["Col. Excel", "En-tête Excel", "Champ de la fiche", "Libellé dans l'application", "Règle de conversion", "Statut", "Fiches renseignées"],
              mapping_rows, [10, 34, 34, 34, 70, 12, 12])
    for row in s.iter_rows(min_row=2):
        row[5].fill = PatternFill("solid", fgColor=status_fill.get(row[5].value, "FFFFFF"))

    code_rows = [
        ["origine", "1 SFAX, 2 GABES, 3 GAFSA, 4 KEBILI, 5 TOZEUR, 6 MEDNINE, 7 TATAOUINE, 8 SBZ, 10 MAHDIA, 11 KAIRAOUANE", "Villes : Hencha, Mahrès, Jbeniana = 1 ; Gafsa, Redeyef = 3 ; Ben Guerdane, Zarzis = 6 ; Tataouine = 7 ; Regueb, Sidi Bouzid = 8", "Sûr"],
        ["oui / non", "1 OUI, 2 NON, 3 NP", "Degré de parenté renseigné uniquement si consanguinité = 1 ; hémarthrose = 1 quand une articulation est atteinte", "Sûr"],
        ["sexe", "1 MASCULIN, 2 FEMININ", "82 hommes sur 83 (hémophilie)", "Sûr"],
        ["régime social", "1 CNAM, 2 INDIGENT", "« indigent » cité comme cause de traitement à la demande", "Probable"],
        ["forme d'hémophilie", "1 SPORADIQUE, 2 HEREDITAIRE", "Code 2 : cas similaire dans la famille dans 35/35 cas", "Sûr"],
        ["CDD", "1 FORTUITE, 2 FAMILIALE, 3 SYNDROME_HEMORRAGIQUE", "1 = bilans préopératoires ; 2 = « frère hémophile »", "Sûr"],
        ["forme clinique", "1 A, 2 B", "FIX effondré pour le code 2, normal pour le code 1", "Sûr"],
        ["sévérité", "1 SEVERE, 2 MODEREE, 3 MINEURE", "FVIII/FIX < 1 % pour 1, 1,5–4 % pour 2, 7–22 % pour 3", "Sûr"],
        ["spontané / provoqué", "1 SPONTANE, 2 PROVOQUE", "", "Probable"],
        ["suite opératoire", "1 SIMPLE, 2 HEMORRAGIQUE", "Circoncision préparée -> 1 (33/39) ; non préparée -> 2 (25/27)", "Sûr"],
        ["type muqueuses", "1 CHUTE_DENTAIRE, 2 PLAIE_LANGUE, 3 GINGIVORRAGIES, 4 EPISTAXIS", "Ordre de l'énumération ; 3 (gingivorragies) le plus fréquent, cohérent avec le texte libre", "Probable"],
        ["type viscères", "1 HEMATEMESE, 2 RECTORRAGIE, 3 HEMATURIE", "Ordre de l'énumération", "Probable"],
        ["accidents graves", "1 hématome cérébral, 2 hémothorax, 3 EDC, 4 hématome compressif", "Code 4 accompagné de « sd des loges »", "Probable"],
        ["type complication ortho.", "1 synovite, 2 arthropathie, 3 pseudotumeur, 4 fracture", "Code 4 présent pour les patients ayant un siège de fracture", "Sûr"],
        ["modalité ttt", "1 PROPHYLAXIE, 2 A_LA_DEMANDE", "Dose de prophylaxie renseignée pour 1 ; cause « à la demande » pour 2", "Sûr"],
        ["plasmatique / recombinant", "1 PLASMATIQUE, 2 RECOMBINANT", "Novoeight, Refacto (recombinants) = 2 ; factVIIIp, fact 9 = 1", "Sûr"],
        ["répondeur", "1 FAIBLE, 2 FORT", "Titres ≤ 2,2 UB pour 1, ≥ 8 UB pour 2 (seuil 5 UB)", "Sûr"],
        ["sérologies", "1 POSITIVE, 2 NEGATIVE, 3 NP", "VHC = 1 pour 9 des 9 « complication virale »", "Sûr"],
        ["vie sociale", "1 CHOMEUR, 2 SCOLARISE, 3 TRAVAILLE", "1 : « chômeur » ; 2 : « élève » ; 3 : métiers. Ordre différent de l'application", "Sûr"],
        ["CAT inhibiteur (3, 4, 5)", "non documenté", "Codes chiffrés non repris : à préciser", "Inconnu"],
        ["articulation cible", "non documenté", "Non repris", "Inconnu"],
    ]
    sheet("Codes", ["Colonne", "Décodage retenu", "Éléments qui le confirment", "Fiabilité"], code_rows, [26, 60, 80, 12])
    sheet("Alertes", ["Ligne Excel", "Patient", "Champ", "Valeur Excel", "Problème", "Valeur retenue"],
          [[a["ligne"], a["patient"], a["champ"], str(a["valeur Excel"]), a["problème"], str(a["valeur retenue"] if a["valeur retenue"] is not None else "")] for a in alerts],
          [10, 18, 30, 50, 50, 30])
    preview = [["Ligne Excel"] + [FIELDS[n]["label"] for n in used]]
    for line, d in fiches:
        preview.append([line] + [(v.isoformat() if isinstance(v, date) else ("Oui" if v is True else "Non" if v is False else v)) for v in (d.get(n) for n in used)])
    sheet("Aperçu des fiches", preview[0], preview[1:], [10] + [16] * len(used))
    wb.save(out_report)

    print(f"{len(fiches)} fiches -> {out_sql}")
    print(f"{len(alerts)} alertes -> {out_report}")


if __name__ == "__main__":
    if len(sys.argv) != 4:
        print(__doc__)
        sys.exit(1)
    main(*sys.argv[1:])
