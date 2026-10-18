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
