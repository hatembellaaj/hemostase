import os
import tempfile

os.environ["DATABASE_URL"] = "sqlite:///" + os.path.join(tempfile.mkdtemp(), "test.db")
os.environ["SEED_DEMO_DATA"] = "true"

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


def test_import_hemophilie_from_interface(client):
    h = token(client)
    xlsx = _fake_registry([("Fictifimport Alpha", (2011, 3, 2)), ("Fictifimport Beta", (2012, 4, 3))])
    url = "/api/admin/import/hemophilie"
    hdr = {**h, "Content-Type": "application/octet-stream"}

    # réservé aux administrateurs
    client.post("/api/admin/users", json={"login": "lecteur", "activated": True, "authorities": ["ROLE_USER"], "password": "lecteur"}, headers=h)
    hu = {**token(client, "lecteur", "lecteur"), "Content-Type": "application/octet-stream"}
    assert client.post(f"{url}/preview", content=xlsx, headers=hu).status_code == 403

    def count():
        r = client.get("/api/fiches/hemophilie", params={"q": "Fictifimport"}, headers=h)
        return int(r.headers["X-Total-Count"])

    # aperçu : rien n'est écrit
    p = client.post(f"{url}/preview", content=xlsx, headers=hdr)
    assert p.status_code == 200, p.text
    assert p.json()["valide"] and p.json()["nbFiches"] == 2 and p.json()["nbNouvelles"] == 2
    assert count() == 0

    # import, puis second import : aucun doublon
    r = client.post(url, content=xlsx, headers=hdr)
    assert r.status_code == 200, r.text
    assert r.json()["importees"] == 2 and count() == 2
    r = client.post(url, content=xlsx, headers=hdr)
    assert r.json() == {"importees": 0, "ignoreesDejaPresentes": 2, "alertes": r.json()["alertes"]} and count() == 2
    assert client.post(f"{url}/preview", content=xlsx, headers=hdr).json()["nbDejaPresentes"] == 2

    # fichier au mauvais format ou illisible : refusé, rien n'est écrit
    bad = _fake_registry([("Fictifimport Gamma", (2013, 1, 1))])
    import io

    import openpyxl

    wb = openpyxl.load_workbook(io.BytesIO(bad))
    wb.active["A1"] = "Autre colonne"
    buf = io.BytesIO()
    wb.save(buf)
    r = client.post(url, content=buf.getvalue(), headers=hdr)
    assert r.status_code == 400 and count() == 2
    assert client.post(url, content=b"pas un classeur", headers=hdr).status_code == 400

    # classeur de contrôle
    rep = client.post(f"{url}/report", content=xlsx, headers=hdr)
    assert rep.status_code == 200 and rep.content[:2] == b"PK"
