# agentic-qa — contrôle qualité hybride multi-agent du registre d'hémophilie

Prototype de recherche pour l'article *Hybrid Multi-Agent Quality Assurance for a Hemophilia Registry:
Semantic Normalization and Expert-Guided Validation* (ICAART 2027).

> **Statut scientifique.** L'infrastructure est implémentée et testée. **Aucune expérience avec un vrai modèle
> de langage n'a été exécutée** : aucun environnement LLM n'est configuré ni autorisé pour les données du registre.
> Les seuls résultats chiffrés existants proviennent (1) du système à règles B0 sur le registre réel, sans
> annotation experte donc **sans métrique de performance**, et (2) d'une expérience sur **données entièrement
> artificielles** (validation logicielle). Toute sortie d'un faux modèle est étiquetée `[SIMULÉ]` et exclue des résultats.

Outil d'aide à la revue des données : il ne prend aucune décision clinique et ne modifie jamais le registre original.

## Ce qui est implémenté

| Composant | Fichiers | Statut |
|---|---|---|
| Lecture en lecture seule + empreinte SHA-256, traçabilité feuille/ligne/colonne/intitulé | `hqa/workbook.py` | testé |
| Profil agrégé (types, vides, intitulés répétés, colonnes sans titre) sans valeur identifiante | `hqa/profiling.py` | testé, exécuté |
| Dictionnaire YAML (32 champs, statut confirmé / hypothèse / manquant) et règles versionnées | `dictionary/` | toutes les correspondances = **hypothèses** |
| Analyseurs déterministes (nombres, censure `<1 %`, unités, durées, dates partielles, codes) | `hqa/parsers.py` | testé |
| Normalisation déterministe ; états observed / unknown / not_applicable / unresolved / empty | `hqa/normalize.py` | testé |
| Moteur de règles (structurelles actives ; hypothèses et seuils ISTH **inactifs** par défaut) | `hqa/rules.py`, `dictionary/rules.yaml` | testé |
| Pseudonymisation (identifiant d'étude, table privée hors exports), expurgation des narratifs | `hqa/privacy.py`, `hqa/cases.py` | testé |
| Adaptateur LLM compatible OpenAI (Ollama, vLLM, serveur local) + garde-fou d'autorisation | `hqa/llm.py` | testé avec faux modèle uniquement |
| Agents : normalisation, cohérence, vérification, coordinateur ; validation des sorties | `hqa/agents.py`, `prompts/*.v1.md` | testé avec faux modèle |
| Comparateurs B0, B1, B2, B3, ablation B3-noverif ; bornes d'exécution | `hqa/pipelines.py` | testé avec faux modèle |
| Découpage dev/test reproductible et figeable | `hqa/split.py` | exécuté (graine 20270101, dev 25 / test 58) |
| Opportunités définies avant évaluation, gabarits d'annotation en aveugle, kappa | `hqa/opportunities.py` | gabarits générés |
| Métriques avec dénominateurs, NA au lieu de 0, bootstrap apparié par dossier | `hqa/metrics.py` | testé |
| Perturbations contrôlées sur données artificielles (variation / injectée / indécidable) | `hqa/synthetic.py` | exécuté |
| Exports désidentifiés + manifeste (versions, empreintes, prompts, graines, git) | `hqa/exports.py` | testé |
| Interface de revue (annotation A/B, adjudication, revue chronométrée) | `review_app.py` | testé (affichage) |

## Avec Docker (recommandé)

```bash
cp .env.example .env && mkdir -p data/raw runs   # puis copier registre.xlsx dans data/raw/
HQA_GIT_COMMIT=$(git rev-parse HEAD) docker compose build
docker compose run --rm hqa audit
docker compose up -d review                     # http://127.0.0.1:8501 (tunnel SSH depuis un poste distant)
```

Toutes les commandes, le modèle local Ollama et le déploiement serveur : `docs/DOCKER.md`.

## Installation sans Docker

```bash
cd agentic-qa
python3 -m venv .venv && source .venv/bin/activate
pip install -r requirements.txt
cp .env.example .env          # laisser HQA_ALLOW_REGISTRY_DATA_TO_LLM=no tant que l'autorisation n'est pas confirmée
mkdir -p data/raw && cp /chemin/vers/registre.xlsx data/raw/registre.xlsx   # copie ; l'original reste intact
```

`data/` et `runs/` sont exclus de Git (données patients, table de correspondance, sorties).

## Commandes

```bash
python -m pytest -q tests                                   # 36 tests, données artificielles uniquement

python -m hqa.cli audit                                     # profil agrégé -> runs/audit/profile.md
python -m hqa.cli split --freeze                            # découpage dev/test figé -> data/derived/split.json
python -m hqa.cli annotation-template --subset dev
python -m hqa.cli annotation-template --subset test --sample-cells-per-case 6   # échantillon stratifié

python -m hqa.cli run --mode B0 --subset all                # règles structurelles
python -m hqa.cli run --mode B0 --subset dev --enable-hypothesis-rules   # profil exploratoire (dev seulement)

# expérience artificielle (validation logicielle) ; --simulate-llm = infrastructure B1-B3 avec FAUX modèle
python -m hqa.cli synthetic --rows 30 --seed 42 --simulate-llm

# après annotation et adjudication par deux experts
python -m hqa.cli evaluate --runs <run_B0> <run_B3> ... --annotations data/derived/annotation_test_adjudicated.csv

streamlit run review_app.py                                 # annotation, adjudication, revue
python -m hqa.cli review-assignment --experts E1 E2 --conditions B0 B3   # carré latin pour la revue chronométrée
```

### Exécuter les modes agentiques avec un modèle local (après autorisation)

```bash
# .env
HQA_LLM_BASE_URL=http://localhost:11434/v1      # ex. Ollama
HQA_LLM_MODEL=<modèle>
HQA_ALLOW_REGISTRY_DATA_TO_LLM=yes              # seulement après confirmation écrite de l'environnement

python -m hqa.cli run --mode B3 --subset dev
python -m hqa.cli run --mode B1 --subset test   # test : exige un découpage figé
```

Sans `HQA_ALLOW_REGISTRY_DATA_TO_LLM=yes`, le client refuse d'envoyer quoi que ce soit. Les données envoyées
sont la vue `llm_view` : identifiant d'étude, dates ramenées au mois, noms expurgés dans les textes.

## Résultats existants (à ne pas surinterpréter)

- **Registre réel, B0 structurel, 83 lignes :** 43 anomalies *candidates* (S04 même clé de nom : 17,
  S03 unité absente : 16, S01 valeur illisible : 10). Ce sont des signalements, pas des erreurs confirmées :
  aucune annotation experte n'existe encore, donc **aucune précision ni rappel n'est rapportable**.
- **Données artificielles (30 lignes, graine 42) :** B0 rappel global 0,947 (18/19) ; l'unique faux négatif est
  la contradiction commentaire/champ, qu'aucune règle déterministe ne couvre (conçue ainsi). Les lignes B1–B3
  `[SIMULÉ]` sont identiques à B0 parce que le faux modèle ne propose rien et que ses sorties sont exclues :
  elles démontrent le fonctionnement de l'infrastructure (appels, budgets, journaux), **pas** une performance.

Agents Maître/Esclaves sur la plateforme RAISE (Azure OpenAI) : `raise/README.md` (passerelle `hqa/gateway.py`, prompts, outils Toolbox, lanceur).

Voir `docs/PROTOCOL.md`, `docs/LIMITATIONS.md`, `docs/QUESTIONS.md`, `docs/architecture.md`.
