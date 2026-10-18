"""Données initiales : rôles, comptes par défaut et (optionnellement) fiches de démonstration."""
import csv
import logging
from datetime import datetime, timezone
from pathlib import Path

from sqlalchemy import func, insert, select

from .config import get_settings
from .db import authorities, fiche_tables, get_engine, user_authorities, users
from .fiches import _coerce, _FieldError, apply_computed
from .schema import entities
from .security import ROLE_ADMIN, ROLE_USER, hash_password

log = logging.getLogger("hemostase.seed")
DEMO_DIR = Path(__file__).with_name("demo_data")


def seed() -> None:
    s = get_settings()
    engine = get_engine()
    with engine.begin() as conn:
        existing = set(conn.execute(select(authorities.c.name)).scalars().all())
        for role in (ROLE_ADMIN, ROLE_USER):
            if role not in existing:
                conn.execute(insert(authorities).values(name=role))

        if conn.execute(select(func.count()).select_from(users)).scalar_one() == 0:
            now = datetime.now(timezone.utc).replace(tzinfo=None)
            accounts = [(s.admin_login, s.admin_password, "Administrateur", [ROLE_ADMIN, ROLE_USER])]
            if s.create_default_user:
                accounts.append(("user", "user", "Utilisateur", [ROLE_USER]))
            for login, pwd, name, roles in accounts:
                uid = conn.execute(
                    insert(users).values(
                        login=login,
                        password_hash=hash_password(pwd),
                        first_name=name,
                        last_name="",
                        email=f"{login}@localhost",
                        activated=True,
                        lang_key="fr",
                        created_by="system",
                        created_date=now,
                    )
                ).inserted_primary_key[0]
                for r in roles:
                    conn.execute(insert(user_authorities).values(user_id=uid, authority_name=r))
            log.warning("Comptes par défaut créés (%s). Changez les mots de passe !", ", ".join(a[0] for a in accounts))

    if s.seed_demo_data:
        load_demo_data()


def load_demo_data() -> None:
    engine = get_engine()
    for ent in entities():
        table = fiche_tables[ent["slug"]]
        path = DEMO_DIR / f"{ent['table']}.csv"
        if not path.exists():
            continue
        with engine.begin() as conn:
            if conn.execute(select(func.count()).select_from(table)).scalar_one() > 0:
                continue
            by_col = {f["column"]: f for f in ent["fields"]}
            rows = []
            with path.open(encoding="utf-8") as fh:
                for raw in csv.DictReader(fh, delimiter=";"):
                    row = {}
                    for col, val in raw.items():
                        f = by_col.get(col)
                        if not f:
                            continue
                        try:
                            v = _coerce(f, val)
                        except _FieldError:
                            v = None
                        if f["type"] == "string" and v and f.get("maxLength"):
                            v = v[: f["maxLength"]]
                        if f["type"] == "enum" and v not in {o["value"] for o in f.get("options", [])}:
                            v = f["options"][0]["value"] if f.get("required") else None
                        row[f["name"]] = v
                    apply_computed(ent, row)
                    rows.append(row)
            if rows:
                conn.execute(insert(table), rows)
                log.info("%s : %d fiches de démonstration chargées", ent["table"], len(rows))
