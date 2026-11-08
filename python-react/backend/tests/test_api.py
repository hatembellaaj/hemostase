import os
import tempfile

os.environ["DATABASE_URL"] = "sqlite:///" + os.path.join(tempfile.mkdtemp(), "test.db")
os.environ["SEED_DEMO_DATA"] = "true"
os.environ["PSEUDO_KEY"] = "cle-de-test-uniquement-0123456789abcdef"

import pytest  # noqa: E402
from fastapi.testclient import TestClient  # noqa: E402

from app.main import app  # noqa: E402
from app.schema import entities  # noqa: E402


@pytest.fixture(scope="module")
def client():
    with TestClient(app) as c:
        yield c


def token(client, login="admin", pwd="admin"):
    r = client.post("/api/authenticate", json={"username": login, "password": pwd})
    assert r.status_code == 200, r.text
    return {"Authorization": f"Bearer {r.json()['id_token']}"}


def minimal(ent):
    data = {}
    for f in ent["fields"]:
        if f.get("required"):
            data[f["name"]] = f["options"][0]["value"] if f["type"] == "enum" else "x"
    return data


def test_login_and_account(client):
    assert client.post("/api/authenticate", json={"username": "admin", "password": "bad"}).status_code == 401
    h = token(client)
    acc = client.get("/api/account", headers=h).json()
    assert acc["login"] == "admin" and "ROLE_ADMIN" in acc["authorities"]
    assert client.get("/api/fiches/hemophilie").status_code == 401


def test_jhipster_password_hash_compatible():
    from app.security import verify_password

    # hash de 'admin' livré dans liquibase/data/user.csv de JHipster
    assert verify_password("admin", "$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC")


def test_demo_data_and_stats(client):
    h = token(client)
    stats = client.get("/api/stats", headers=h).json()
    assert len(stats) == 8 and all(s["count"] > 0 for s in stats)


@pytest.mark.parametrize("ent", entities(), ids=lambda e: e["slug"])
def test_crud_each_entity(client, ent):
    h = token(client)
    url = f"/api/fiches/{ent['slug']}"
    payload = minimal(ent)
    payload["nom"] = "TEST"
    r = client.post(url, json=payload, headers=h)
    assert r.status_code == 201, r.text
    fid = r.json()["id"]
    payload["prenom"] = "Ali"
    payload["dateNaissance"] = "2000-01-15"
    r = client.put(f"{url}/{fid}", json=payload, headers=h)
    assert r.status_code == 200, r.text
    got = r.json()
    assert got["prenom"] == "Ali" and got["ageActuel"] >= 25
    lst = client.get(url, params={"q": "TEST", "size": 5}, headers=h)
    assert lst.status_code == 200 and int(lst.headers["X-Total-Count"]) >= 1
    assert client.get(f"{url}/export.csv", headers=h).status_code == 200
    assert client.delete(f"{url}/{fid}", headers=h).status_code == 204
    assert client.get(f"{url}/{fid}", headers=h).status_code == 404


def test_validation_errors(client):
    h = token(client)
    r = client.post("/api/fiches/hemophilie", json={"nom": "x" * 40, "sexe": "AUTRE_CHOSE"}, headers=h)
    assert r.status_code == 400
    errs = r.json()["detail"]["fieldErrors"]
    assert "nom" in errs and "sexe" in errs and "etatCivil" in errs


def test_vwf_ratios(client):
    h = token(client)
    ent = next(e for e in entities() if e["slug"] == "von-willebrand")
    p = minimal(ent) | {"fvwag": 50, "fvwact": 20, "f8c": 40}
    r = client.post("/api/fiches/von-willebrand", json=p, headers=h).json()
    assert r["ratioFvwactFvwag"] == 0.4 and r["ratioF8cFvwag"] == 0.8


def test_users_admin(client):
    h = token(client)
    r = client.post("/api/admin/users", json={"login": "medecin", "password": "secret", "authorities": ["ROLE_USER"]}, headers=h)
    assert r.status_code == 201, r.text
    hu = token(client, "medecin", "secret")
    assert client.get("/api/admin/users", headers=hu).status_code == 403
    assert client.get("/api/fiches/thrombopathie", headers=hu).status_code == 200
    assert client.delete("/api/admin/users/medecin", headers=h).status_code == 204


def test_stats_overview_all_entities(client):
    h = token(client)
    for ent in entities():
        r = client.get(f"/api/stats/{ent['slug']}/overview", headers=h)
        assert r.status_code == 200, (ent["slug"], r.text)
        body = r.json()
        assert body["kpis"][0]["label"] == "Fiches" and body["charts"], ent["slug"]


def test_stats_aggregate(client):
    h = token(client)
    url = "/api/stats/hemophilie/aggregate"
    r = client.post(url, json={"x": "severite", "series": "sexe"}, headers=h).json()
    assert [c["key"] for c in r["categories"]][:1] in (["SEVERE"], ["MODEREE"], ["MINEURE"])
    total = sum(v for d in r["cells"].values() for v in d.values())
    assert total == r["total"]
    r = client.post(url, json={"x": "ageActuel", "xBin": 10, "measure": {"op": "avg", "field": "hb"}}, headers=h)
    assert r.status_code == 200
    r = client.post(url, json={"x": "dateNaissance", "filters": [{"field": "sexe", "op": "in", "value": ["MASCULIN"]}]}, headers=h).json()
    assert all(c["key"] == "__missing__" or isinstance(c["key"], int) for c in r["categories"])
    r = client.post(url, json={"fields": ["hemarthroseMC", "inhibiteurs"], "series": "formeClinique"}, headers=h)
    assert r.status_code == 200 and len(r.json()["categories"]) == 2
    assert client.post(url, json={"x": "nom"}, headers=h).status_code == 400


def _fake_registry(rows):
    """Classeur ENTIÈREMENT FICTIF au format du registre (seuls les intitulés contrôlés sont renseignés)."""
    import datetime
    import io

    import openpyxl

    from app.import_hemophilie import EXPECTED

    wb = openpyxl.Workbook()
    ws = wb.active
    ws.title = "Feuil1"
    hdr = [None] * 165
    for i, h in EXPECTED.items():
        hdr[i] = h
    ws.append(hdr)
    for name, born in rows:
        r = [None] * 165
        r[0], r[1], r[4], r[7], r[36], r[37] = name, 1, datetime.datetime(*born), 1, 1, 1
        ws.append(r)
    buf = io.BytesIO()
    wb.save(buf)
    return buf.getvalue()


def _xlsx_rows(content):
    import io

    import openpyxl

    ws = openpyxl.load_workbook(io.BytesIO(content)).active
    return [r for r in ws.iter_rows(min_row=3, values_only=True)]


def test_pseudonym_code_is_stable_and_keyed(monkeypatch):
    from datetime import date

    from app import pseudonym

    c = pseudonym.code("hemophilie", "Fictif", "Alpha", date(2011, 3, 2))
    assert pseudonym.is_code(c) and c.startswith("HEM-") and len(c) <= 25
    # casse, accents et espaces ignorés ; même partie hexadécimale dans tous les registres
    assert pseudonym.code("hemophilie", " FICTÎF ", "alpha", date(2011, 3, 2)) == c
    assert pseudonym.code("thrombopathie", "Fictif", "Alpha", date(2011, 3, 2)) == "THR-" + c[4:]
    assert pseudonym.code("hemophilie", "Fictif", "Alpha", date(2011, 3, 3)) != c   # autre date de naissance
    from app.config import get_settings

    monkeypatch.setattr(get_settings(), "pseudo_key", "trop-courte")
    import pytest as _pytest

    with _pytest.raises(pseudonym.PseudoKeyMissing):
        pseudonym.code("hemophilie", "Fictif", "Alpha", None)


def test_import_hemophilie_from_interface(client):
    from datetime import date

    from app.pseudonym import code

    h = token(client)
    xlsx = _fake_registry([("Fictifimport Alpha", (2011, 3, 2)), ("Fictifimport Beta", (2012, 4, 3))])
    url = "/api/admin/import/hemophilie"
    hdr = {**h, "Content-Type": "application/octet-stream"}
    alpha = code("hemophilie", "Fictifimport", "Alpha", date(2011, 3, 2))

    # registres : seul l'hémophilie a un convertisseur
    regs = {r["slug"]: r for r in client.get("/api/admin/import", headers=h).json()}
    assert regs["hemophilie"]["disponible"] and not regs["thrombopathie"]["disponible"]
    assert client.post("/api/admin/import/thrombopathie/preview", content=xlsx, headers=hdr).status_code == 404
    assert client.post("/api/admin/import/inconnu/preview", content=xlsx, headers=hdr).status_code == 404

    # réservé aux administrateurs
    client.post("/api/admin/users", json={"login": "lecteur", "activated": True, "authorities": ["ROLE_USER"], "password": "lecteur"}, headers=h)
    hu = {**token(client, "lecteur", "lecteur"), "Content-Type": "application/octet-stream"}
    assert client.post(f"{url}/preview", content=xlsx, headers=hu).status_code == 403
    assert client.get("/api/admin/import", headers=hu).status_code == 403

    def count(q):
        r = client.get("/api/fiches/hemophilie", params={"q": q}, headers=h)
        return int(r.headers["X-Total-Count"])

    # aperçu : rien n'est écrit, le code de chaque fiche est annoncé
    p = client.post(f"{url}/preview", content=xlsx, headers=hdr)
    assert p.status_code == 200, p.text
    assert p.json()["valide"] and p.json()["nbFiches"] == 2 and p.json()["nbNouvelles"] == 2
    assert p.json()["apercu"][0]["code"] == alpha
    assert count(alpha) == 0

    # import : noms codés, aucun nom en clair en base ; second import : aucun doublon
    r = client.post(url, content=xlsx, headers=hdr)
    assert r.status_code == 200, r.text
    assert r.json()["importees"] == 2 and count(alpha) == 1 and count("Fictifimport") == 0
    fiche = client.get("/api/fiches/hemophilie", params={"q": alpha}, headers=h).json()[0]
    assert fiche["nom"] == alpha and not fiche["prenom"]
    r = client.post(url, content=xlsx, headers=hdr)
    assert r.json()["importees"] == 0 and r.json()["ignoreesDejaPresentes"] == 2
    assert client.post(f"{url}/preview", content=xlsx, headers=hdr).json()["nbDejaPresentes"] == 2

    # correspondance : remise à l'administrateur, recalculée depuis le fichier
    corr = client.post(f"{url}/correspondence", content=xlsx, headers=hdr)
    assert corr.status_code == 200
    assert (alpha, "Fictifimport", "Alpha", "2011-03-02", "ligne Excel 2") in _xlsx_rows(corr.content)

    # fichier au mauvais format ou illisible : refusé, rien n'est écrit
    import io

    import openpyxl

    wb = openpyxl.load_workbook(io.BytesIO(_fake_registry([("Fictifimport Gamma", (2013, 1, 1))])))
    wb.active["A1"] = "Autre colonne"
    buf = io.BytesIO()
    wb.save(buf)
    assert client.post(url, content=buf.getvalue(), headers=hdr).status_code == 400
    assert client.post(url, content=b"pas un classeur", headers=hdr).status_code == 400

    # classeur de contrôle
    rep = client.post(f"{url}/report", content=xlsx, headers=hdr)
    assert rep.status_code == 200 and rep.content[:2] == b"PK"


def test_code_existing_clear_names(client):
    from datetime import date

    from app.pseudonym import code

    h = token(client)
    ent = next(e for e in entities() if e["slug"] == "thrombopathie")
    payload = {**minimal(ent), "nom": "Fictifexist", "prenom": "Delta", "dateNaissance": "2009-06-07"}
    assert client.post("/api/fiches/thrombopathie", json=payload, headers=h).status_code == 201
    before = {r["slug"]: r["nonCodees"] for r in client.get("/api/admin/import", headers=h).json()}["thrombopathie"]
    assert before >= 1

    r = client.post("/api/admin/import/thrombopathie/code-existing", headers=h)
    assert r.status_code == 200, r.text
    c = code("thrombopathie", "Fictifexist", "Delta", date(2009, 6, 7))
    assert any(row[:4] == (c, "Fictifexist", "Delta", "2009-06-07") for row in _xlsx_rows(r.content))
    lst = client.get("/api/fiches/thrombopathie", params={"q": c}, headers=h)
    assert int(lst.headers["X-Total-Count"]) == 1 and not lst.json()[0]["prenom"]
    assert {r["slug"]: r["nonCodees"] for r in client.get("/api/admin/import", headers=h).json()}["thrombopathie"] == 0
    # second passage : rien à coder
    assert client.post("/api/admin/import/thrombopathie/code-existing", headers=h).status_code == 409
