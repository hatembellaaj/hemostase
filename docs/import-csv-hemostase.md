# Import d'un CSV dans la table `fiche_hemophilie`

Ce guide propose un **script SQL type** et une **table de correspondance** entre les colonnes d'un CSV (fourni) et les colonnes de la table `fiche_hemophilie`.

> Remarque : dans le code, l'entité « hémostase » correspond à la table **`fiche_hemophilie`**. Adaptez si votre base contient un autre nom de table.

## 1) Méthode recommandée (staging + insertion)

La méthode la plus sûre est :

1. **Créer une table de staging** qui reflète exactement les colonnes du CSV (toutes en `text`).
2. **Charger le CSV** dans la table de staging avec `COPY` (PostgreSQL) ou `LOAD DATA` (MySQL).
3. **Insérer** dans `fiche_hemophilie` en transformant/convertissant les champs (dates, nombres, enums) dans un `INSERT ... SELECT`.

### Exemple PostgreSQL (à adapter)

```sql
-- 1) Table de staging (types en text pour absorber les données brutes)
CREATE TEMP TABLE staging_hemostase (
  "Nom et prénom" text,
  "origine" text,
  "ville" text,
  "numéro du dossier" text,
  "date de naissance" text,
  "âge actuel" text,
  "tranche" text,
  "sexe" text,
  "régime social" text,
  "consanguinité" text,
  "degré de parenté" text,
  "cas similaire dans la famille" text,
  "décés par Sd hémorragique dans la famille" text,
  "si oui cause" text,
  "lien de parenté" text,
  "fraterie" text,
  "nbre de fréres" text,
  "nbre de sœurs" text,
  "bilan chez la mére" text,
  "si fait préciser" text,
  "conductrice" text,
  "forme d'hémophilie" text,
  "age de début des 1ers signes" text,
  "symptomes" text,
  "âge de diagnostic" text,
  "mois" text,
  "CDD" text,
  "si sd hémorragique, type" text,
  "TCA" text,
  "ratio" text,
  "TCA(M+T)" text,
  "GB" text,
  "Hb" text,
  "plq" text,
  "facteur VIII" text,
  "facteur IX" text,
  "forme clinique" text,
  "sévérité" text,
  "vaccination HBV" text,
  "calendrier vaccinal" text,
  "hémarthrose" text,
  "coude dt" text,
  "coude gche" text,
  "coude" text,
  "genou dt" text,
  "genou gche" text,
  "genou" text,
  "cheville dt" text,
  "cheville gche" text,
  "cheville" text,
  "ép dte" text,
  "ép gche" text,
  "epaule" text,
  "hche dte" text,
  "hche gche" text,
  "hche" text,
  "poignet dt" text,
  "poignet gche" text,
  "poignet" text,
  "hématome" text,
  "spontanée ou provoqué" text,
  "hematome profond" text,
  "siége" text,
  "hématome du psoas" text,
  "spontané ou provoqué" text,
  "récidive" text,
  "hémorragie du SNC" text,
  "evolution" text,
  "hémorragie des muqueuses" text,
  "type" text,
  "écchymoses" text,
  "spontanée ou provoquée" text,
  "siége_2" text,
  "hémorragie des viscéres" text,
  "type_2" text,
  "exploration" text,
  "type de lésion sous jacente" text,
  "circoncision" text,
  "préparée ou non" text,
  "suite opératoire" text,
  "âge de circoncision" text,
  "avulsion dentaire" text,
  "si oui préparée ou non?" text,
  "intervention chirurgicale" text,
  "préparée ou non?" text,
  "type_3" text,
  "accidents hémorragiques graves" text,
  "si oui type" text,
  "PSL:oui/non" text,
  "cryo seul" text,
  "cryo puis facteur" text,
  "cryo+facteur" text,
  "PFC seul" text,
  "PFC puis facteur" text,
  "PFC + facteur" text,
  "Facteur VIII seul" text,
  "Facteur IX seul" text,
  "PPSB" text,
  "ATCD de ttt par PSL" text,
  "date de début de substitution" text,
  "date de début substituiton par facteur" text,
  "modalité du ttt substitutif prophylactique ou a la demande" text,
  "type du ttt substitutif: plasmatique ou recombinant" text,
  "si recombinant preciser nom" text,
  "si a la demande: cause" text,
  "si prophylaxie, dose en UI/kg" text,
  "frequence/semaine" text,
  "date du début" text,
  "obsevance" text,
  "nbre d'accidents hémorragique/6 mois" text,
  "necessite d'augmentation des doses?" text,
  "complication orthopédique" text,
  "type_4" text,
  "articulation" text,
  "si fracture siége" text,
  "CAT thérapeutique" text,
  "articulation cible" text,
  "bilan radio" text,
  "année" text,
  "score orthopédique de Gilbert" text,
  "score radiologique de Petterson" text,
  "PEC de l'arthropathie" text,
  "inhibiteur" text,
  "faible/fort repondeur" text,
  "taux d'inhibiteur" text,
  "CAT thérapeutique_2" text,
  "ITI" text,
  "posologie en ui/kg et frequence" text,
  "observance" text,
  "si non, cause" text,
  "évolution" text,
  "prophylaxie par Hemlibra" text,
  "date de debut" text,
  "schéma utilise" text,
  "efficacité (nbre d'acc/6mois)" text,
  "complication virale" text,
  "date de début" text,
  "schéma thérapeutique" text,
  "efficacitê(nbre d'accidents hemorragiques/6 mois)" text,
  "sérologie HBV" text,
  "date de découverte" text,
  "charge virale" text,
  "traitement" text,
  "evolution_2" text,
  "sérologie VHC" text,
  "date de découverte_2" text,
  "charge virale_2" text,
  "traitement_2" text,
  "évolution_2" text,
  "sérologie HIV" text,
  "vie social" text,
  "niveau scolaire" text,
  "travail" text,
  "marié" text,
  "enfants" text,
  "si oui type" text,
  "activité sportive" text,
  "décédé" text,
  "si Dc, cause" text,
  "DDN" text,
  "niveau scolaire_2" text
);

-- 2) Import (adapter le séparateur selon votre CSV : ;, , ou tabulation)
COPY staging_hemostase
FROM '/chemin/vers/hemostase.csv'
WITH (FORMAT csv, HEADER true, DELIMITER E'\t', NULL '', QUOTE '"');

-- 3) Insertion (extrait, à adapter selon la table de correspondance)
INSERT INTO fiche_hemophilie (
  dossier_number,
  nom,
  prenom,
  origine,
  adresse,
  date_naissance,
  age_actuel,
  sexe,
  couverture_sociale,
  parents_consanguins,
  degre_parente_consanguins,
  cas_similaires,
  deces_syndrome_hemorragique,
  degre_parente_cas_similaires,
  fratrie_nb_freres,
  fratrie_nb_soeurs,
  forme_hemophilie,
  date_premier_signe_clinique,
  age_diagnostic_annees,
  age_diagnostic_mois,
  hb,
  plaquettes,
  dosage_facteur_viii,
  dosage_facteur_ix,
  forme_clinique,
  severite
)
SELECT
  NULLIF("numéro du dossier", ''),
  split_part("Nom et prénom", ' ', 1),
  NULLIF(regexp_replace("Nom et prénom", '^\\S+\\s+', ''), ''),
  NULLIF("origine", ''),
  NULLIF("ville", ''),
  NULLIF("date de naissance", '')::date,
  NULLIF("âge actuel", '')::int,
  NULLIF("sexe", ''),
  NULLIF("régime social", ''),
  NULLIF("consanguinité", ''),
  NULLIF("degré de parenté", ''),
  NULLIF("cas similaire dans la famille", ''),
  NULLIF("décés par Sd hémorragique dans la famille", ''),
  NULLIF("lien de parenté", ''),
  NULLIF("nbre de fréres", '')::int,
  NULLIF("nbre de sœurs", '')::int,
  NULLIF("forme d'hémophilie", ''),
  NULLIF("age de début des 1ers signes", '')::date,
  NULLIF("âge de diagnostic", '')::int,
  NULLIF("mois", '')::int,
  NULLIF("Hb", '')::float,
  NULLIF("plq", '')::int,
  NULLIF("facteur VIII", '')::float,
  NULLIF("facteur IX", '')::float,
  NULLIF("forme clinique", ''),
  NULLIF("sévérité", '')
FROM staging_hemostase;
```

## 2) Table de correspondance (CSV → `fiche_hemophilie`)

> **Légende** :
>
> - **N/A** = aucune colonne correspondante dans `fiche_hemophilie`.
> - Certaines colonnes CSV nécessitent un **mapping vers un enum** (valeurs attendues par l'application).

| Colonne CSV                                                | Colonne `fiche_hemophilie`             | Remarques                                              |
| ---------------------------------------------------------- | -------------------------------------- | ------------------------------------------------------ |
| Nom et prénom                                              | `nom`, `prenom`                        | Découper en deux champs (ex: split sur le 1er espace). |
| origine                                                    | `origine`                              | Enum `Region` (prévoir mapping).                       |
| ville                                                      | `adresse`                              | Pas de colonne `ville` dédiée.                         |
| numéro du dossier                                          | `dossier_number`                       |                                                        |
| date de naissance                                          | `date_naissance`                       | Date (YYYY-MM-DD).                                     |
| âge actuel                                                 | `age_actuel`                           |                                                        |
| tranche                                                    | N/A                                    |                                                        |
| sexe                                                       | `sexe`                                 | Enum `Gender`.                                         |
| régime social                                              | `couverture_sociale`                   | Enum `CouvertureSociale`.                              |
| consanguinité                                              | `parents_consanguins`                  | Enum `OuiNonNP`.                                       |
| degré de parenté                                           | `degre_parente_consanguins`            | Texte.                                                 |
| cas similaire dans la famille                              | `cas_similaires`                       | Enum `OuiNonNP`.                                       |
| décés par Sd hémorragique dans la famille                  | `deces_syndrome_hemorragique`          | Enum `OuiNonNP`.                                       |
| si oui cause                                               | N/A                                    |                                                        |
| lien de parenté                                            | `degre_parente_cas_similaires`         | Enum `DegreeParente`.                                  |
| fraterie                                                   | N/A                                    |                                                        |
| nbre de fréres                                             | `fratrie_nb_freres`                    |                                                        |
| nbre de sœurs                                              | `fratrie_nb_soeurs`                    |                                                        |
| bilan chez la mére                                         | N/A                                    |                                                        |
| si fait préciser                                           | N/A                                    |                                                        |
| conductrice                                                | N/A                                    |                                                        |
| forme d'hémophilie                                         | `forme_hemophilie`                     | Enum `FormeHemophilie`.                                |
| age de début des 1ers signes                               | `date_premier_signe_clinique`          | La colonne DB attend une date, pas un âge.             |
| symptomes                                                  | `circonstances_decouverte`             | Enum `CircumstanceDecouverte` (si pertinent).          |
| âge de diagnostic                                          | `age_diagnostic_annees`                | Âge en années.                                         |
| mois                                                       | `age_diagnostic_mois`                  |                                                        |
| CDD                                                        | N/A                                    |                                                        |
| si sd hémorragique, type                                   | `accidents_hemorragiques_graves_types` | Enum `TypeAccidentHemorragiqueGrave`.                  |
| TCA                                                        | `tca_mt`                               |                                                        |
| ratio                                                      | `tca_mt_total`                         | À vérifier selon votre définition.                     |
| TCA(M+T)                                                   | `tca_mt`                               |                                                        |
| GB                                                         | N/A                                    |                                                        |
| Hb                                                         | `hb`                                   |                                                        |
| plq                                                        | `plaquettes`                           |                                                        |
| facteur VIII                                               | `dosage_facteur_viii`                  |                                                        |
| facteur IX                                                 | `dosage_facteur_ix`                    |                                                        |
| forme clinique                                             | `forme_clinique`                       | Enum `FormeClinique`.                                  |
| sévérité                                                   | `severite`                             | Enum `Severite`.                                       |
| vaccination HBV                                            | N/A                                    | (Sérologie ≠ vaccination).                             |
| calendrier vaccinal                                        | N/A                                    |                                                        |
| hémarthrose                                                | `hemarthrose`                          | Booléen.                                               |
| coude dt                                                   | `coude_droit`                          | Booléen.                                               |
| coude gche                                                 | `coude_gauche`                         | Booléen.                                               |
| coude                                                      | N/A                                    |                                                        |
| genou dt                                                   | `genou_droit`                          | Booléen.                                               |
| genou gche                                                 | `genou_gauche`                         | Booléen.                                               |
| genou                                                      | N/A                                    |                                                        |
| cheville dt                                                | `cheville_droite`                      | Booléen.                                               |
| cheville gche                                              | `cheville_gauche`                      | Booléen.                                               |
| cheville                                                   | N/A                                    |                                                        |
| ép dte                                                     | `epaule_droite`                        | Booléen.                                               |
| ép gche                                                    | `epaule_gauche`                        | Booléen.                                               |
| epaule                                                     | N/A                                    |                                                        |
| hche dte                                                   | `hanche_droite`                        | Booléen.                                               |
| hche gche                                                  | `hanche_gauche`                        | Booléen.                                               |
| hche                                                       | N/A                                    |                                                        |
| poignet dt                                                 | `poignet_droit`                        | Booléen.                                               |
| poignet gche                                               | `poignet_gauche`                       | Booléen.                                               |
| poignet                                                    | N/A                                    |                                                        |
| hématome                                                   | `hematome_superficiel`                 | Enum `OuiNonNP` (si superficiel).                      |
| spontanée ou provoqué                                      | `hematome_superficiel_type`            | Enum `TypeManifestation`.                              |
| hematome profond                                           | `hematome_psoas`                       | Enum `OuiNonNP` (si profond = psoas).                  |
| siége                                                      | `hematome_superficiel_siege`           | Texte (si superficiel).                                |
| hématome du psoas                                          | `hematome_psoas`                       | Enum `OuiNonNP`.                                       |
| spontané ou provoqué                                       | `hematome_psoas_type`                  | Enum `TypeHemorragie`.                                 |
| récidive                                                   | `hematome_psoas_recidive`              | Enum `OuiNonNP`.                                       |
| hémorragie du SNC                                          | `saignement_snc`                       | Enum `OuiNonNP`.                                       |
| evolution                                                  | `saignement_snc_evolution`             | Enum `EvolutionSNC`.                                   |
| hémorragie des muqueuses                                   | `hemorragies_cutaneo_muqueuses`        | Enum `OuiNonNP`.                                       |
| type                                                       | `hemorragies_cutaneo_muqueuses_types`  | Enum `TypeHemorragieCutaneoMuqueuse`.                  |
| écchymoses                                                 | N/A                                    |                                                        |
| spontanée ou provoquée                                     | N/A                                    |                                                        |
| siége (muqueuses)                                          | N/A                                    |                                                        |
| hémorragie des viscéres                                    | `hemorragie_visceres`                  | Enum `OuiNonNP`.                                       |
| type (viscères)                                            | `hemorragie_visceres_type`             | Enum `TypeHemorragieViscere`.                          |
| exploration                                                | `hemorragie_visceres_exploration`      | Enum `OuiNonNP`.                                       |
| type de lésion sous jacente                                | `hemorragie_visceres_examen`           | Texte libre.                                           |
| circoncision                                               | `circoncision`                         | Enum `OuiNonNP`.                                       |
| préparée ou non                                            | `circoncision_preparation`             | Enum `OuiNonNP`.                                       |
| suite opératoire                                           | `circoncision_suite_operatoire`        | Enum `SuiteOperatoire`.                                |
| âge de circoncision                                        | `circoncision_age`                     |                                                        |
| avulsion dentaire                                          | `avulsion_dentaire`                    | Enum `OuiNonNP`.                                       |
| si oui préparée ou non?                                    | `avulsion_dentaire_preparation`        | Enum `OuiNonNP`.                                       |
| intervention chirurgicale                                  | `intervention_chirurgicale`            | Enum `OuiNonNP`.                                       |
| préparée ou non?                                           | N/A                                    | (pas de colonne dédiée).                               |
| type (intervention)                                        | `intervention_chirurgicale_type`       |                                                        |
| accidents hémorragiques graves                             | `accidents_hemorragiques_graves`       | Enum `OuiNonNP`.                                       |
| si oui type                                                | `accidents_hemorragiques_graves_types` | Enum `TypeAccidentHemorragiqueGrave`.                  |
| PSL:oui/non                                                | `antecedents_traitement_psl`           | Enum `OuiNonNP`.                                       |
| cryo seul                                                  | `cryoprecipite`                        | Enum `OuiNonNP`.                                       |
| cryo puis facteur                                          | N/A                                    |                                                        |
| cryo+facteur                                               | N/A                                    |                                                        |
| PFC seul                                                   | `plasma_frais_congele`                 | Enum `OuiNonNP`.                                       |
| PFC puis facteur                                           | N/A                                    |                                                        |
| PFC + facteur                                              | N/A                                    |                                                        |
| Facteur VIII seul                                          | `type_traitement_substitutif`          | Texte libre (ex: "Facteur VIII").                      |
| Facteur IX seul                                            | `type_traitement_substitutif`          | Texte libre (ex: "Facteur IX").                        |
| PPSB                                                       | `type_traitement_substitutif`          | Texte libre (ex: "PPSB").                              |
| ATCD de ttt par PSL                                        | `antecedents_traitement_psl`           | Enum `OuiNonNP`.                                       |
| date de début de substitution                              | N/A                                    | (pas de date dédiée).                                  |
| date de début substituiton par facteur                     | N/A                                    |                                                        |
| modalité du ttt substitutif prophylactique ou a la demande | `modalite_traitement`                  | Enum `ModaliteTraitement`.                             |
| type du ttt substitutif: plasmatique ou recombinant        | `type_traitement_substitutif`          | Texte libre.                                           |
| si recombinant preciser nom                                | N/A                                    |                                                        |
| si a la demande: cause                                     | `cause_prise_en_charge`                |                                                        |
| si prophylaxie, dose en UI/kg                              | `prophylaxie_dose_frequence`           | Regrouper dose + fréquence.                            |
| frequence/semaine                                          | `prophylaxie_dose_frequence`           | Idem.                                                  |
| date du début                                              | `date_debut_prophylaxie`               |                                                        |
| obsevance                                                  | N/A                                    |                                                        |
| nbre d'accidents hémorragique/6 mois                       | N/A                                    |                                                        |
| necessite d'augmentation des doses?                        | `augmenter_dose_facteur`               | Booléen.                                               |
| complication orthopédique                                  | `orthopediques`                        | Enum `OuiNonNP`.                                       |
| type (ortho)                                               | N/A                                    | (répartir sur synovite/arthropathie/fractures).        |
| articulation                                               | N/A                                    |                                                        |
| si fracture siége                                          | N/A                                    |                                                        |
| CAT thérapeutique                                          | `pec_therapeutique`                    | Enum `PECTherapeutique`.                               |
| articulation cible                                         | N/A                                    |                                                        |
| bilan radio                                                | N/A                                    |                                                        |
| année                                                      | N/A                                    |                                                        |
| score orthopédique de Gilbert                              | N/A                                    |                                                        |
| score radiologique de Petterson                            | N/A                                    |                                                        |
| PEC de l'arthropathie                                      | `pec_therapeutique`                    | Si vous stockez via cette colonne.                     |
| inhibiteur                                                 | `inhibiteurs`                          | Enum `OuiNonNP`.                                       |
| faible/fort repondeur                                      | `taux_inhibiteur`                      | Enum `TauxInhibiteur` (faible/fort).                   |
| taux d'inhibiteur                                          | `taux_inhibiteur`                      | Enum `TauxInhibiteur`.                                 |
| CAT thérapeutique (inhibiteur)                             | `pec_therapeutique`                    | Enum `PECTherapeutique`.                               |
| ITI                                                        | `iti`                                  | Booléen.                                               |
| posologie en ui/kg et frequence                            | `posologie_iti`                        | Texte libre.                                           |
| observance                                                 | `observance_iti`                       | Enum `OuiNonNP`.                                       |
| si non, cause                                              | `cause_non_observance`                 |                                                        |
| évolution                                                  | `evolution_inhibiteur`                 | Enum `EvolutionInhibiteur`.                            |
| prophylaxie par Hemlibra                                   | `prophylaxie_hemlibra`                 | Booléen.                                               |
| date de debut                                              | `date_debut_hemlibra`                  |                                                        |
| schéma utilise                                             | `schema_therapeutique`                 | Enum `SchemaTherapeutique`.                            |
| efficacité (nbre d'acc/6mois)                              | `efficacite_hemlibra`                  |                                                        |
| complication virale                                        | N/A                                    |                                                        |
| date de début (virale)                                     | N/A                                    |                                                        |
| schéma thérapeutique                                       | `schema_therapeutique`                 | Si lié à Hemlibra, sinon N/A.                          |
| efficacitê(nbre d'accidents hemorragiques/6 mois)          | `efficacite_hemlibra`                  |                                                        |
| sérologie HBV                                              | `serologie_hbc`                        | Enum `Serologie`.                                      |
| date de découverte (HBV)                                   | N/A                                    |                                                        |
| charge virale (HBV)                                        | N/A                                    |                                                        |
| traitement (HBV)                                           | N/A                                    |                                                        |
| evolution (HBV)                                            | N/A                                    |                                                        |
| sérologie VHC                                              | `serologie_hvc`                        | Enum `Serologie`.                                      |
| date de découverte (VHC)                                   | `date_decouverte_vhc`                  |                                                        |
| charge virale (VHC)                                        | `charge_virale_vhc`                    |                                                        |
| traitement (VHC)                                           | `traitement_vhc`                       | Enum `OuiNonNP`.                                       |
| évolution (VHC)                                            | `evolution_vhc`                        | Enum `EvolutionVHC`.                                   |
| sérologie HIV                                              | `serologie_hiv`                        | Enum `Serologie`.                                      |
| vie social                                                 | `vie_sociale`                          | Enum `VieSociale`.                                     |
| niveau scolaire                                            | N/A                                    |                                                        |
| travail                                                    | `profession`                           |                                                        |
| marié                                                      | `marie`                                | Enum `OuiNonNP`.                                       |
| enfants                                                    | `enfants`                              | Enum `OuiNonNP`.                                       |
| si oui type                                                | `type_handicap`                        | Si ce champ décrit un handicap, sinon N/A.             |
| activité sportive                                          | `activite_sportive`                    | Booléen.                                               |
| décédé                                                     | `decede`                               | Booléen.                                               |
| si Dc, cause                                               | `cause_deces`                          |                                                        |
| DDN                                                        | `date_naissance`                       | Doublon de "date de naissance".                        |
| niveau scolaire (fin)                                      | N/A                                    |                                                        |

## 3) Points d'attention

- Les **valeurs des enums** doivent correspondre à celles de l'application (ex: `MALE`/`FEMALE`, `OUI`/`NON`/`NP`, etc.).
- Pensez à **nettoyer les virgules**, les espaces, et les valeurs vides avant de convertir en `date`/`int`/`float`.
- Si votre CSV contient des colonnes non présentes dans `fiche_hemophilie`, il faut soit :
  - ajouter des colonnes en base, soit
  - les ignorer lors de l'`INSERT`.
