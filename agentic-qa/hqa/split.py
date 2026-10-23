"""
Découpage développement / test reproductible.

Unité d'observation : la ligne (fiche). L'identité des personnes n'étant pas confirmée, aucune ligne n'est
fusionnée. Les lignes partageant une même clé de nom sont placées dans le MÊME sous-ensemble (contrainte de
découpage, pas une fusion) pour éviter une fuite développement -> test si elles concernent la même personne
ou la même famille.
"""
from __future__ import annotations

import hashlib
import json
import random
from pathlib import Path

from .cases import Case


def make_split(cases: list[Case], seed: int, n_dev: int, file_sha256: str) -> dict:
    groups: dict[str, list[str]] = {}
    for c in cases:
        groups.setdefault(c.name_key, []).append(c.study_id)
    units = sorted(sorted(g) for g in groups.values())
    rng = random.Random(seed)
    rng.shuffle(units)
    dev, test, n = [], [], 0
    for g in units:
        # on remplit le développement jusqu'à n_dev lignes ; un groupe n'est jamais coupé
        if n < n_dev and n + len(g) <= n_dev + 1:
            dev.append(g)
            n += len(g)
        else:
            test.append(g)
    dev_ids = sorted(s for g in dev for s in g)
    test_ids = sorted(s for g in test for s in g)
    assert not set(dev_ids) & set(test_ids)
    split = {
        "seed": seed,
        "requested_n_dev": n_dev,
        "n_dev": len(dev_ids),
        "n_test": len(test_ids),
        "unit_of_observation": "ligne (fiche) ; identité non confirmée, aucune fusion",
        "grouping": "lignes de même clé de nom dans le même sous-ensemble",
        "n_groups_multi_row": sum(1 for g in units if len(g) > 1),
        "file_sha256": file_sha256,
        "dev": dev_ids,
        "test": test_ids,
        "frozen": False,
    }
    split["split_sha256"] = hashlib.sha256(json.dumps({"dev": dev_ids, "test": test_ids}, sort_keys=True).encode()).hexdigest()
    return split


def save_split(split: dict, path: str | Path, overwrite: bool = False) -> None:
    p = Path(path)
    if p.exists() and not overwrite:
        old = json.loads(p.read_text(encoding="utf-8"))
        if old.get("frozen"):
            raise RuntimeError("Découpage figé : refus d'écraser (utiliser un nouveau fichier)")
    p.write_text(json.dumps(split, indent=1, ensure_ascii=False), encoding="utf-8")


def load_split(path: str | Path) -> dict:
    return json.loads(Path(path).read_text(encoding="utf-8"))


def subset(cases: list[Case], split: dict, which: str) -> list[Case]:
    ids = set(split[which])
    other = set(split["test" if which == "dev" else "dev"])
    out = [c for c in cases if c.study_id in ids]
    assert not any(c.study_id in other for c in out)
    return out
