"""Chargement de la configuration YAML avec substitution des variables d'environnement (${VAR})."""
from __future__ import annotations

import os
import re
from pathlib import Path

import yaml

ROOT = Path(__file__).resolve().parent.parent


def _load_dotenv(path: Path) -> None:
    if not path.exists():
        return
    for line in path.read_text(encoding="utf-8").splitlines():
        line = line.strip()
        if line and not line.startswith("#") and "=" in line:
            k, v = line.split("=", 1)
            os.environ.setdefault(k.strip(), v.strip())


def _subst(obj):
    if isinstance(obj, dict):
        return {k: _subst(v) for k, v in obj.items()}
    if isinstance(obj, list):
        return [_subst(v) for v in obj]
    if isinstance(obj, str):
        m = re.fullmatch(r"\$\{(\w+)\}", obj.strip())
        if m:
            return os.environ.get(m.group(1)) or None
    return obj


def load_config(path: str | Path | None = None, overrides: dict | None = None) -> dict:
    _load_dotenv(ROOT / ".env")
    cfg = yaml.safe_load(Path(path or ROOT / "config" / "default.yaml").read_text(encoding="utf-8"))
    cfg = _subst(cfg)
    for dotted, value in (overrides or {}).items():
        node = cfg
        *head, last = dotted.split(".")
        for k in head:
            node = node.setdefault(k, {})
        node[last] = value
    # chemins relatifs à la racine du dépôt
    for k, v in cfg["paths"].items():
        if k != "sheet" and v and not Path(v).is_absolute():
            cfg["paths"][k] = str(ROOT / v)
    return cfg


def redacted_config(cfg: dict) -> dict:
    """Copie sans secret, pour le manifeste."""
    import copy

    c = copy.deepcopy(cfg)
    if c.get("llm", {}).get("api_key"):
        c["llm"]["api_key"] = "***"
    return c
