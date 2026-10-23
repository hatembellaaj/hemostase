# Image du prototype de contrôle qualité. AUCUNE donnée patient n'est copiée dans l'image :
# data/ et runs/ sont montés en volumes à l'exécution (voir docker-compose.yml et .dockerignore).
FROM python:3.12-slim

ARG HQA_GIT_COMMIT=unknown
ARG HQA_UID=1000
ARG HQA_GID=1000

ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1 \
    PIP_NO_CACHE_DIR=1 \
    HQA_GIT_COMMIT=${HQA_GIT_COMMIT} \
    STREAMLIT_BROWSER_GATHER_USAGE_STATS=false

WORKDIR /app

COPY requirements.txt .
RUN pip install -r requirements.txt

COPY hqa ./hqa
COPY dictionary ./dictionary
COPY prompts ./prompts
COPY config ./config
COPY tests ./tests
COPY examples ./examples
COPY docs ./docs
COPY review_app.py README.md ./

# utilisateur non root ; ses UID/GID doivent correspondre au propriétaire des dossiers montés
RUN groupadd -g ${HQA_GID} hqa && useradd -u ${HQA_UID} -g ${HQA_GID} -m hqa \
    && mkdir -p /app/data /app/runs && chown -R hqa:hqa /app
USER hqa

ENTRYPOINT ["python", "-m", "hqa.cli"]
CMD ["--help"]
