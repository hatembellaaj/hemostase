# Import de données "fiche hémophilie" avec anonymisation

Le script `scripts/import_fiche_hemostase.py` permet de charger un export CSV (par exemple depuis Excel) tout en codant les noms/prénoms avant l'envoi vers l'API Hemostase.

## Champs attendus

Le CSV doit contenir les colonnes suivantes :

| Colonne CSV                         | Champ DTO ciblé             | Notes                                                |
| ----------------------------------- | --------------------------- | ---------------------------------------------------- |
| `patient_prenom`                    | `prenom`                    | obligatoire, anonymisé par hash                      |
| `patient_nom`                       | `nom`                       | obligatoire, anonymisé par hash                      |
| `patient_sexe`                      | `sexe`                      | obligatoire, valeur enum (ex: `MASCULIN`, `FEMININ`) |
| `patient_etat_civil`                | `etatCivil`                 | obligatoire, enum                                    |
| `patient_couverture_sociale`        | `couvertureSociale`         | obligatoire, enum                                    |
| `patient_diagnostic`                | `diagnostic`                | obligatoire, enum                                    |
| `patient_nom_jeune_fille`           | `nomJeuneFille`             | anonymisé                                            |
| `patient_profession`                | `profession`                |                                                      |
| `patient_origine`                   | `origine`                   | enum région                                          |
| `patient_autre_origine`             | `autreOrigine`              |                                                      |
| `patient_adresse`                   | `adresse`                   |                                                      |
| `patient_telephone`                 | `telephone`                 |                                                      |
| `patient_prenom_pere`               | `prenomPere`                | anonymisé                                            |
| `patient_profession_pere`           | `professionPere`            |                                                      |
| `patient_nom_prenom_mere`           | `nomPrenomMere`             | anonymisé                                            |
| `patient_profession_mere`           | `professionMere`            |                                                      |
| `patient_annee_diagnostic`          | `anneeDiagnostic`           |                                                      |
| `patient_consentement_registre`     | `consentementRegistre`      | enum (`OUI`, `NON`, `NP`)                            |
| `fiche_dossier_number`              | `dossierNumber`             | obligatoire                                          |
| `fiche_prise_en_charge`             | `priseEnCharge`             | obligatoire (alias `fiche_prene_en_charge`)          |
| `fiche_ordre_number`                | `ordreNumber`               |                                                      |
| `fiche_index_number`                | `indexNumber`               |                                                      |
| `fiche_forme_hemophilie`            | `formeHemophilie`           | enum                                                 |
| `fiche_date_test_confirmation`      | `dateTestConfirmation`      | format `YYYY-MM-DD`                                  |
| `fiche_nbre_freres`                 | `nbreFreres`                |                                                      |
| `fiche_nbre_soeurs`                 | `nbreSoeurs`                |                                                      |
| `fiche_debut_prophylaxie`           | `debutProphylaxie`          | format `YYYY-MM-DD`                                  |
| `fiche_type_traitement_substitutif` | `typeTraitementSubstitutif` | enum                                                 |
| `fiche_serologie_hbc`               | `serologieHBC`              | enum                                                 |
| `fiche_serologie_hvc`               | `serologieHVC`              | enum                                                 |
| `fiche_serologie_hiv`               | `serologieHIV`              | enum                                                 |

Les énumérations attendues utilisent les valeurs majuscules définies dans `src/main/java/org/adhes/hemostase/domain/enumeration/` (espaces remplacés par `_`).

## Anonymisation des noms

Les colonnes de type nom/prénom sont hachées avec SHA-256 et un « salt » configurable avant insertion. Les valeurs brutes ne transitent pas vers l'API.

## Exécution

```bash
python scripts/import_fiche_hemostase.py export.csv \
  --api-base http://localhost:8080 \
  --token "<JWT_admin>" \
  --salt "votre_salt"
```

Le script crée d'abord le patient via `/api/patients`, puis la fiche via `/api/fiche-hemophilies` en lui associant `patientId`.

## Import en Java (profil `fiche-import`)

Une alternative purement Java est disponible pour les environnements où seul le JDK/Docker est accessible. Le `CommandLineRunner`
`FicheHemostaseCsvImporter` se déclenche quand le profil Spring `fiche-import` est actif et attend les propriétés suivantes :

- `hemostase.import.csv-path` : chemin vers le CSV (identique au tableau ci-dessus).
- `hemostase.import.api-base` : URL de base de l'API (ex : `http://localhost:8080`).
- `hemostase.import.token` : JWT avec droits d'écriture.
- `hemostase.import.salt` : salt utilisé pour le hachage SHA-256 des identités.

### Exemple en local

```bash
./mvnw -DskipTests package
SPRING_PROFILES_ACTIVE=prod,fiche-import \
SPRING_MAIN_WEB-APPLICATION-TYPE=none \
java -jar target/*.jar \
  --hemostase.import.csv-path=/chemin/export.csv \
  --hemostase.import.api-base=http://localhost:8080 \
  --hemostase.import.token="<JWT_admin>" \
  --hemostase.import.salt="votre_salt"
```

### Exemple Docker Compose

1. Construire l'image applicative si besoin : `./mvnw -DskipTests -Pprod package jib:dockerBuild`
2. Lancer la base + l'API : `docker compose -f src/main/docker/app.yml up -d`
3. Exécuter l'importeur dans un conteneur éphémère, en montant le CSV :

```bash
docker compose -f src/main/docker/app.yml run --rm \
  -v $(pwd)/export.csv:/data/export.csv:ro \
  -e SPRING_PROFILES_ACTIVE=prod,fiche-import \
  -e SPRING_MAIN_WEB-APPLICATION-TYPE=none \
  -e HEMOSTASE_IMPORT_CSV_PATH=/data/export.csv \
  -e HEMOSTASE_IMPORT_API_BASE=http://app:8080 \
  -e HEMOSTASE_IMPORT_TOKEN="<JWT_admin>" \
  -e HEMOSTASE_IMPORT_SALT="votre_salt" \
  app
```

Le conteneur se connecte à l'API déjà démarrée (service `app`) et se termine une fois l'import réalisé.

## Conseils

- Vérifiez que le JWT possède les droits d'écriture.
- Contrôlez les valeurs des énumérations dans le code si un statut HTTP 400 est retourné.
- Travaillez sur une base de test avant d'injecter en production.
