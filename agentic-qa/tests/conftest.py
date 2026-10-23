"""Fixtures : uniquement des données ARTIFICIELLES (aucune donnée du registre réel)."""
import json
import sys
from pathlib import Path

import pytest

ROOT = Path(__file__).resolve().parent.parent
sys.path.insert(0, str(ROOT))

from hqa.cases import build_cases, same_name_groups  # noqa: E402
from hqa.config import load_config  # noqa: E402
from hqa.dictionary import load_dictionary  # noqa: E402
from hqa.privacy import Pseudonymizer  # noqa: E402
from hqa.rules import RuleEngine  # noqa: E402
from hqa.synthetic import generate  # noqa: E402
from hqa.workbook import read_workbook  # noqa: E402

DEV_STATUSES = ["structural", "approved", "hypothesis", "proposed_reference"]


@pytest.fixture(scope="session")
def d():
    return load_dictionary(ROOT / "dictionary" / "fields.yaml", ROOT / "dictionary" / "rules.yaml")


@pytest.fixture()
def cfg(tmp_path):
    c = load_config(overrides={"paths.private_dir": str(tmp_path / "private"), "paths.derived_dir": str(tmp_path / "derived"),
                               "paths.runs_dir": str(tmp_path / "runs")})
    c["llm"].update({"base_url": None, "model": None, "allow_registry_data": "no"})
    return c


@pytest.fixture()
def synth(tmp_path, d):
    g = generate(d, 30, 42, tmp_path / "syn")
    wb = read_workbook(g["workbook"], "Feuil1", {c: f.field_id for c, f in d.by_col().items()})
    cases = build_cases(wb, d, Pseudonymizer(tmp_path / "private"))
    same = {s: grp for grp in same_name_groups(cases) for s in grp}
    return {"path": g["workbook"], "truth": g["truth"], "wb": wb, "cases": cases, "same": same}


@pytest.fixture()
def engine_dev(d):
    return RuleEngine(d, DEV_STATUSES)


def write_rows(tmp_path, d, rows: list[dict], name="mini.xlsx") -> Path:
    """Classeur artificiel minimal : rows = [{field_id: valeur}]"""
    import openpyxl

    wb = openpyxl.Workbook()
    ws = wb.active
    ws.title = "Feuil1"
    by_col = {f.source_col: f for f in d.fields.values()}
    ws.append([by_col[c].original_header if c in by_col else None for c in range(1, 166)])
    for r in rows:
        ws.append([r.get(by_col[c].field_id) if c in by_col else None for c in range(1, 166)])
    ws.append([None] * 165)  # ligne vide dans l'étendue
    p = tmp_path / name
    wb.save(p)
    return p


@pytest.fixture()
def mini(tmp_path, d):
    def _make(rows, statuses=None):
        p = write_rows(tmp_path, d, rows)
        wb = read_workbook(p, "Feuil1", {c: f.field_id for c, f in d.by_col().items()})
        cases = build_cases(wb, d, Pseudonymizer(tmp_path / "private_mini"))
        cases.sort(key=lambda c: c.excel_row)
        same = {s: grp for grp in same_name_groups(cases) for s in grp}
        return {"path": p, "wb": wb, "cases": cases, "engine": RuleEngine(d, statuses or DEV_STATUSES, {"same_name": same})}
    return _make


def base_row(**kw):
    r = {"patient_name": "Fictif A", "file_number": "X-1", "sex": 1, "consanguinity": 2, "family_similar_cases": 2,
         "clinical_form": 1, "severity": 1, "fviii_level": 0.5}
    r.update(kw)
    return r


json  # noqa: B018
