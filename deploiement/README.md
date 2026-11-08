# Déploiement de la solution hemostase sur un serveur partagé

La solution regroupe trois projets Docker Compose indépendants. Tous les ports publiés sur le serveur sont regroupés
dans la plage **18500–18509** et se règlent dans les fichiers `.env` ; les ports internes aux conteneurs ne changent pas.

| Port hôte | Service | Projet / conteneur | Variable (`.env`) | Écoute par défaut |
|---|---|---|---|---|
| 18500 | Registre : interface React + API (`/api`) | `python-react` → `hemostase-frontend-1` | `HTTP_PORT`, `HTTP_BIND` | `0.0.0.0` (réseau) |
| 18501 | Revue et annotation (Streamlit, sans authentification) | `agentic-qa` → `hemostase-qa-review-1` | `HQA_REVIEW_PORT` | `127.0.0.1` |
| 18502 | Passerelle QA pour RAISE (clé `X-API-Key`) | `agentic-qa` → `hemostase-qa-gateway-1` | `HQA_GATEWAY_PORT`, `HQA_GATEWAY_BIND` | `127.0.0.1` |
| 18503 | Page de bilan statique (agrégats) | `agentic-qa` → `hemostase-qa-site-1` | `HQA_SITE_PORT`, `HQA_SITE_BIND` | `127.0.0.1` |
| 18504–18509 | réservés (extensions futures) | — | — | — |
| — | MariaDB, Ollama, `mcp-agentic-rag` | réseaux Docker internes | — | aucun port publié |

Le mode développement de `python-react` (`docker-compose.dev.yml`) publie 5173, 8000 et 3306 sur `127.0.0.1`
seulement ; il est destiné à un poste de développement (`DEV_WEB_PORT`, `DEV_API_PORT`, `DEV_DB_PORT` pour les changer).

## 1. Vérifier que les ports sont libres

```bash
cd ~/IA/medecine/hemostase
./deploiement/check_ports.sh
```

Le script lit les `.env` (ou les valeurs par défaut) et signale tout port déjà pris par un autre conteneur ou
programme. En cas de conflit, changer la variable du `.env` concerné et relancer le script.

## 2. Registre (python-react)

```bash
cd python-react
cp .env.example .env            # changer les mots de passe, JWT_SECRET ; HTTP_PORT=18500
docker compose up -d --build    # -> http://<serveur>:18500
```

Le projet s'appelle `hemostase` (conteneurs `hemostase-*`, volume de base `hemostase_db_data`).

## 3. Contrôle qualité (agentic-qa)

```bash
cd ../agentic-qa
cp .env.example .env            # HQA_UID/HQA_GID = id -u / id -g ; ports 18501-18503
mkdir -p data/raw runs && chmod 700 data
HQA_GIT_COMMIT=$(git rev-parse HEAD) docker compose build
docker compose up -d review site          # gateway seulement si RAISE est utilisé
```

Détails (modèle local, autorisation, essais) : `agentic-qa/docs/DOCKER.md`.

## 4. Accès

- Registre : `http://<serveur>:18500`.
- Revue (Streamlit, sans authentification) : tunnel SSH, jamais publiée directement
  `ssh -L 18501:127.0.0.1:18501 <user>@<serveur>` puis `http://localhost:18501`.
- Page de bilan : `http://127.0.0.1:18503` sur le serveur ; publique avec `HQA_SITE_BIND=0.0.0.0`.

Docker publie ses ports **en contournant `ufw`** : un port lié à `0.0.0.0` est joignable depuis Internet sauf si le
pare-feu de l'hébergeur le bloque. Pour un nom de domaine en HTTPS, laisser les services sur `127.0.0.1` et les
placer derrière le reverse proxy du serveur.
