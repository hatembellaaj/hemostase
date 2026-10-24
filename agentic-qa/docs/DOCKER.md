# Exécution avec Docker

Trois services (Docker Compose v2, commande `docker compose` sans tiret) :

| Service | Rôle | Démarrage |
|---|---|---|
| `hqa` | CLI : audit, découpage, exécutions B0–B3, évaluation, données artificielles | `docker compose run --rm hqa <commande>` |
| `review` | interface Streamlit (annotation, adjudication, revue) | `docker compose up -d review` |
| `ollama` | modèle **local** facultatif (profil `llm`) | `docker compose --profile llm up -d ollama` |

L'image ne contient **aucune donnée patient** (`.dockerignore` exclut `data/`, `runs/`, `.env`, `*.xlsx`) :
le registre, la table de correspondance et les sorties restent sur l'hôte, montés en volumes.
Le service `ollama` n'a aucun port publié ; l'interface de revue n'écoute que sur `127.0.0.1` de l'hôte.

## 1. Récupérer le code sur le serveur

```bash
cd ~/NewReg/hemostase                 # dépôt déjà cloné (sinon : git clone <url> && cd hemostase)
git fetch origin
git checkout feature/python-react     # branche qui contient agentic-qa/
git pull
cd agentic-qa
```

## 2. Préparer les données et la configuration

```bash
mkdir -p data/raw runs                # à créer AVANT le premier lancement (sinon Docker les crée en root)
# depuis le Mac : scp ~/Downloads/registre.xlsx <user>@<serveur>:~/NewReg/hemostase/agentic-qa/data/raw/registre.xlsx
chmod 700 data

cp .env.example .env
sed -i "s/^HQA_UID=.*/HQA_UID=$(id -u)/; s/^HQA_GID=.*/HQA_GID=$(id -g)/" .env
# laisser HQA_ALLOW_REGISTRY_DATA_TO_LLM=no tant que l'autorisation n'est pas confirmée
```

## 3. Construire et vérifier

```bash
HQA_GIT_COMMIT=$(git rev-parse HEAD) docker compose build
docker compose run --rm --entrypoint python hqa -m pytest -q tests     # 47 tests attendus
```

## 4. Lancer les étapes

```bash
docker compose run --rm hqa audit                                     # -> runs/audit/profile.md
docker compose run --rm hqa split --freeze                            # -> data/derived/split.json
docker compose run --rm hqa annotation-template --subset dev
docker compose run --rm hqa annotation-template --subset test --sample-cells-per-case 6
docker compose run --rm hqa run --mode B0 --subset all
docker compose run --rm hqa synthetic --rows 30 --seed 42 --simulate-llm
docker compose run --rm hqa evaluate --runs <run_B0> <run_B3> --annotations data/derived/annotation_test_adjudicated.csv
```

## 5. Interface de revue

```bash
docker compose up -d review
docker compose logs -f review
```

Streamlit n'a pas d'authentification : il est publié sur `127.0.0.1:8501` du serveur seulement.
Depuis le poste de l'expert :

```bash
ssh -L 8501:127.0.0.1:8501 <user>@<serveur>
# puis ouvrir http://localhost:8501
```

(Changer le port local avec `HQA_REVIEW_PORT` dans `.env`. Pour un accès sans tunnel, le placer derrière le
reverse proxy existant **avec authentification** — ne pas publier le port sur 0.0.0.0.)

## 6. Modèle local

```bash
docker compose --profile llm up -d ollama
docker compose exec ollama ollama pull qwen2.5:7b-instruct   # serveur actuel : CPU seul, 31 Go de RAM
```

Le service fixe `OLLAMA_CONTEXT_LENGTH=8192` (sinon les prompts longs sont tronqués sans erreur) et
`OLLAMA_NUM_PARALLEL=1`. Mesure sur le serveur actuel (Xeon E-2386G, sans GPU) : ~34 tokens/s en lecture,
~11 tokens/s en génération ; d'où `request_timeout_s: 300` et `max_seconds_per_case: 900` dans `config/default.yaml`.

### 6a. Vrai modèle sur données artificielles (aucune autorisation registre requise)

Dans `.env` : `HQA_LLM_BASE_URL=http://ollama:11434/v1` et `HQA_LLM_MODEL=qwen2.5:7b-instruct`
(laisser `HQA_ALLOW_REGISTRY_DATA_TO_LLM=no`).

```bash
docker compose run --rm hqa synthetic --rows 10 --seed 42 --llm --modes B3     # essai court
docker compose run --rm hqa synthetic --rows 30 --seed 42 --llm                # B1, B2, B3, B3-noverif
```

Les sorties brutes du modèle sont conservées dans `runs/<run>/raw_llm_outputs.jsonl` (données artificielles
uniquement). Une sortie coupée par `max_output_tokens` est comptée comme invalide (« sortie tronquée »), jamais
comme une absence d'anomalie.
Seul le classeur artificiel généré peut être envoyé (contrôle par empreinte). Les résultats servent à valider
l'infrastructure et à mesurer temps, tokens et sorties non conformes ; ils ne mesurent pas la performance sur le registre.

### 6b. Registre (uniquement après autorisation)

Dans `.env` :

```
HQA_LLM_BASE_URL=http://ollama:11434/v1
HQA_LLM_MODEL=qwen2.5:7b-instruct
HQA_ALLOW_REGISTRY_DATA_TO_LLM=yes     # seulement après confirmation écrite de l'environnement
HQA_LLM_AUTHORIZATION_REF=modèle local uniquement, accord du responsable du registre du 2026-10-24
```

```bash
docker compose run --rm hqa run --mode B3 --subset dev
docker compose run --rm hqa run --mode B2 --subset dev
docker compose run --rm hqa run --mode B1 --subset dev
docker compose run --rm hqa run --mode B3-noverif --subset dev
```

GPU NVIDIA : décommenter le bloc `deploy` du service `ollama` (nécessite `nvidia-container-toolkit`).
Sans GPU le modèle tourne sur CPU, beaucoup plus lentement.

## 6c. Page de bilan

`site/index.html` est une page statique : architecture, chiffres obtenus, questions ouvertes. Elle ne contient
que des agrégats, aucune donnée patient. Le service `site` (nginx) la sert en lecture seule :

```bash
docker compose up -d site            # http://127.0.0.1:8097 sur le serveur
ssh -L 8097:127.0.0.1:8097 <user>@<serveur>   # depuis un poste, puis http://localhost:8097
```

Pour la rendre accessible sur le réseau, mettre `HQA_SITE_BIND=0.0.0.0` dans `.env` (ou la placer derrière le
reverse proxy existant). Le schéma d'architecture et les polices se chargent depuis des CDN (jsDelivr, Google
Fonts) : sans accès Internet, la page reste lisible et le schéma s'affiche en texte.

## 7. Arrêt et nettoyage

```bash
docker compose down                    # arrête review (et ollama)
docker compose --profile llm down      # idem, profil llm inclus
docker volume rm hemostase-qa_ollama-models   # supprime les modèles téléchargés (facultatif)
```

`data/` et `runs/` ne sont jamais supprimés par Docker : ce sont des dossiers de l'hôte.

## Cohabitation avec les autres déploiements

Projet Compose distinct (`name: hemostase-qa`), réseau propre, aucune base de données, aucun port publié
hors `127.0.0.1:8501` : pas de conflit avec la version Java ni avec `python-react` (port 8090).
