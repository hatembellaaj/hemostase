# Registre hémostase : version Python + React

Portage du projet JHipster **adhes2025** (Spring Boot + Angular + MariaDB) vers :

| Couche      | Technologie                                     | Dossier     |
|-------------|-------------------------------------------------|-------------|
| API         | Python 3.12, FastAPI, SQLAlchemy 2, JWT          | `backend/`  |
| Interface   | React 18, TypeScript, Vite, servie par nginx     | `frontend/` |
| Base        | MariaDB 11.4                                     | (Docker)    |

Les 8 fiches d'origine sont reprises à l'identique (tables, colonnes, énumérations, champs obligatoires) :
Hémophilie, Thrombopathie, Maladie de Willebrand, déficits en facteurs VII, VIII, XI, XIII et XVIII,
soit 1 416 champs au total.

## Démarrage rapide

```bash
cp .env.example .env          # changez les mots de passe et JWT_SECRET
docker compose up -d --build
```

Ouvrez <http://localhost:8080> et connectez-vous avec `admin` / `admin` (ou `user` / `user`).
**Changez ces mots de passe dès la première connexion** (menu « Mon compte »).

- Documentation interactive de l'API : <http://localhost:8080/api/docs>
- Pour charger les 80 fiches fictives de JHipster dans une base vide : `SEED_DEMO_DATA=true` dans `.env`.

### Mode développement (rechargement à chaud)

```bash
docker compose -f docker-compose.yml -f docker-compose.dev.yml up --build
```

Interface sur <http://localhost:5173>, API sur <http://localhost:8000/api/docs>, MariaDB sur le port 3306.

Sans Docker :

```bash
cd backend && pip install -r requirements.txt && uvicorn app.main:app --reload   # SQLite par défaut
cd frontend && npm install && npm run dev
```

## Reprendre la base JHipster existante

Le backend utilise **les mêmes noms de tables et de colonnes** que JHipster (`fiche_hemophilie`,
`jhi_user`, `jhi_user_authority`…). Les mots de passe bcrypt existants restent valables.

1. Exportez l'ancienne base : `mysqldump adhes2025 > docker/initdb/01-adhes2025.sql`
2. Lancez `docker compose up -d --build` sur un volume vide : MariaDB importe le dump automatiquement.
   (Ou pointez `DATABASE_URL` vers votre serveur existant.)
3. Au démarrage, le backend passe les colonnes `id` en `AUTO_INCREMENT` (JHipster utilisait une
   séquence Hibernate). Les données existantes sont conservées et la numérotation reprend à `MAX(id)+1`.

Faites une sauvegarde avant de brancher le backend sur la base de production.

## Ce qui a été repris des formulaires Angular

Le fichier `backend/app/entities.json` décrit chaque fiche. Il est généré automatiquement à partir
du projet JHipster par `tools/extract_schema.py`, qui lit :

- `.jhipster/*.json` : types, énumérations, champs obligatoires, longueurs maximales ;
- les changelogs Liquibase : noms réels des tables et colonnes ;
- les templates `*-update.component.html` : sections (accordéons), ordre des champs, libellés
  français, libellés des options, **champs conditionnels** (`@if`, `*ngIf`, `@let`), cas familiaux
  répétés (cas 1 à 3, décès 1 à 3) et sélection multiple des articulations (hémarthrose).

Calculs automatiques conservés : âge actuel à partir de la date de naissance (toutes les fiches) et
ratios FVW:Act/FVW:Ag et FVIII:C/FVW:Ag (Willebrand). Ils sont faits dans l'interface et revérifiés
par l'API.

Si vous modifiez le projet JHipster, régénérez le schéma :

```bash
python tools/extract_schema.py /chemin/vers/adhes2025 backend/app/entities.json
```

L'interface est entièrement pilotée par ce schéma : aucun code React à modifier pour un nouveau champ.

### Écarts connus avec l'original

- Quelques contrôles présents dans les templates Angular n'existent pas dans le modèle Java (ex.
  `pfaColEpi`, `tpMPlusT` pour le facteur VII ; une vingtaine pour le facteur XVIII, dont le
  formulaire semble copié de celui du facteur XI). Ils n'étaient pas enregistrés en base dans
  l'original non plus et sont ignorés.
- À l'inverse, les champs du modèle absents des templates sont regroupés dans une section
  « Autres informations » pour rester saisissables.
- Sur une **base neuve**, les colonnes texte font au moins 64 caractères (et `TEXT` au-delà de 255)
  pour respecter les limites de taille de ligne de MariaDB en utf8mb4 avec plus de 200 colonnes.
  Les longueurs maximales d'origine restent contrôlées par l'API.
- Non repris : inscription publique, réinitialisation de mot de passe par e-mail, pages
  d'administration techniques de JHipster (métriques, logs, configuration).

## Statistiques

Menu **Analyse → Statistiques** (ou bouton « Statistiques » sur chaque liste), un registre à la fois :

- **Vue d'ensemble** : chiffres clés (nombre de fiches, âge médian, part des moins de 18 ans, des femmes,
  et indicateurs propres au registre : formes sévères, hémophilie B, inhibiteurs…) et graphiques
  prédéfinis (âge, origine, couverture sociale, consanguinité, année du diagnostic, puis graphiques
  propres à chaque registre). Ils sont définis dans `backend/app/stats.py` (`COMMON_CHARTS`, `SPECIFIC`,
  `KPI_SPECIFIC`) : ajouter un graphique = ajouter une ligne.
- **Explorateur** : l'utilisateur choisit
  - le type : histogramme, courbe ou camembert ;
  - l'axe des catégories : un champ (liste, oui/non, nombre regroupé en classes, date regroupée par année)
    ou plusieurs champs oui/non à comparer (ex. hémarthrose, psoas, inhibiteurs) ;
  - une répartition par un second champ (barres groupées ou empilées) ;
  - la mesure : nombre de fiches, ou moyenne / minimum / maximum d'une valeur (Hb, taux de facteur…) ;
  - des filtres (ex. forme clinique = A, âge entre 0 et 18) ;
  - l'affichage en pourcentage et l'inclusion des « Non renseigné ».

  Les réglages sont conservés dans l'adresse de la page : copier le lien suffit pour partager un graphique.

Chaque graphique a une infobulle, une vue tableau et un export CSV.

## API

| Méthode | Route                                   | Rôle                                       |
|---------|-----------------------------------------|--------------------------------------------|
| POST    | `/api/authenticate`                     | Connexion → `{ id_token }`                 |
| GET     | `/api/account`                          | Compte connecté                            |
| POST    | `/api/account/change-password`          | Changer son mot de passe                   |
| GET     | `/api/schema`                           | Description des fiches                     |
| GET     | `/api/stats`                            | Nombre de fiches par registre              |
| GET     | `/api/stats/{type}/overview`            | Chiffres clés et graphiques prédéfinis     |
| POST    | `/api/stats/{type}/aggregate`           | Agrégation à la demande (voir `backend/app/stats.py`) |
| GET     | `/api/fiches/{type}?page&size&sort&q`   | Liste paginée (`X-Total-Count`), recherche |
| GET     | `/api/fiches/{type}/export.csv`         | Export CSV (séparateur `;`, compatible Excel) |
| GET/POST/PUT/DELETE | `/api/fiches/{type}[/{id}]`  | Lecture, création, modification, suppression |
| *       | `/api/admin/users…`                     | Gestion des utilisateurs (ROLE_ADMIN)      |

Types : `hemophilie`, `thrombopathie`, `von-willebrand`, `facteur-vii`, `facteur-viii`,
`facteur-xi`, `facteur-xiii`, `facteur-xviii`.

Les filtres par champ sont acceptés en paramètre : `/api/fiches/hemophilie?sexe=FEMININ&severite=SEVERE`.

## Tests

```bash
cd backend && pip install -r requirements.txt pytest httpx && pytest
```

Les tests couvrent la connexion, la compatibilité des mots de passe JHipster, le CRUD des 8 fiches,
la validation, les calculs automatiques et les droits administrateur.
