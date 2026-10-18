"""Chargement du schéma des fiches (généré par tools/extract_schema.py)."""
import json
from functools import lru_cache
from pathlib import Path

SCHEMA_PATH = Path(__file__).with_name("entities.json")


@lru_cache
def load_schema() -> dict:
    return json.loads(SCHEMA_PATH.read_text(encoding="utf-8"))


def entities() -> list[dict]:
    return load_schema()["entities"]


@lru_cache
def entity_by_slug() -> dict[str, dict]:
    return {e["slug"]: e for e in entities()}
