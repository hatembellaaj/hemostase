#!/usr/bin/env bash
# Vérifie, AVANT le démarrage, que les ports publiés par la solution hemostase sont libres sur ce serveur.
# Lit les .env de python-react et d'agentic-qa (sinon les valeurs par défaut) et interroge `ss`.
#   ./deploiement/check_ports.sh            # depuis la racine du dépôt
# Code de sortie : 0 si tout est libre (ou déjà occupé par nos propres conteneurs), 1 sinon.
set -u
ROOT="$(cd "$(dirname "$0")/.." && pwd)"

val() {  # val <fichier .env> <variable> <défaut>
  local v=""
  [ -f "$1" ] && v="$(grep -E "^$2=" "$1" | tail -1 | cut -d= -f2- | tr -d '"'"'"' ')"
  echo "${v:-$3}"
}

RR="$ROOT/python-react/.env"
QA="$ROOT/agentic-qa/.env"
PORTS=(
  "$(val "$RR" HTTP_PORT 18500)|Registre (python-react, interface + API)"
  "$(val "$QA" HQA_REVIEW_PORT 18501)|Revue Streamlit (agentic-qa)"
  "$(val "$QA" HQA_GATEWAY_PORT 18502)|Passerelle QA RAISE (agentic-qa)"
  "$(val "$QA" HQA_SITE_PORT 18503)|Page de bilan (agentic-qa)"
)

# conteneurs Docker qui publient chaque port ; ceux de la solution s'appellent hemostase-… ou hemostase-qa-… (pour distinguer « déjà lancé par nous » d'un vrai conflit)
docker_owner() {
  sudo -n docker ps --format '{{.Names}}\t{{.Ports}}' 2>/dev/null || docker ps --format '{{.Names}}\t{{.Ports}}' 2>/dev/null
}
OWNERS="$(docker_owner)"

status=0
printf '%-7s %-44s %s\n' "PORT" "SERVICE" "ÉTAT"
for entry in "${PORTS[@]}"; do
  IFS='|' read -r port name <<<"$entry"
  owner="$(printf '%s\n' "$OWNERS" | awk -F'\t' -v p=":$port->" 'index($2, p) {print $1}' | head -1)"
  if [ -n "$owner" ]; then
    case "$owner" in
      hemostase-*) state="déjà lancé par la solution ($owner)";;
      *) state="CONFLIT : utilisé par le conteneur $owner"; status=1;;
    esac
  elif ss -ltnH "sport = :$port" 2>/dev/null | grep -q .; then
    state="CONFLIT : un programme hors Docker écoute déjà"; status=1
  else
    state="libre"
  fi
  printf '%-7s %-44s %s\n' "$port" "$name" "$state"
done

[ $status -eq 0 ] && echo "OK : aucun conflit." || echo "Choisissez un autre port dans le .env concerné, puis relancez ce script."
exit $status
