from datetime import datetime, timezone

from fastapi import APIRouter, Depends, HTTPException, Query, Response
from pydantic import BaseModel
from sqlalchemy import delete, func, insert, select, update
from sqlalchemy.exc import IntegrityError

from ..db import authorities, get_engine, user_authorities, users
from ..security import ROLE_ADMIN, hash_password, require_admin

router = APIRouter(prefix="/api/admin", tags=["administration"])


class UserIn(BaseModel):
    login: str
    firstName: str | None = None
    lastName: str | None = None
    email: str | None = None
    activated: bool = True
    authorities: list[str] = ["ROLE_USER"]
    password: str | None = None


def _dto(row, roles: list[str]) -> dict:
    return {
        "id": row["id"],
        "login": row["login"],
        "firstName": row["first_name"],
        "lastName": row["last_name"],
        "email": row["email"],
        "activated": row["activated"],
        "authorities": sorted(roles),
        "createdDate": row["created_date"].isoformat() if row["created_date"] else None,
        "lastModifiedDate": row["last_modified_date"].isoformat() if row["last_modified_date"] else None,
    }


def _roles(conn, user_id: int) -> list[str]:
    return conn.execute(select(user_authorities.c.authority_name).where(user_authorities.c.user_id == user_id)).scalars().all()


@router.get("/authorities")
def list_authorities(_: dict = Depends(require_admin)):
    with get_engine().connect() as conn:
        return conn.execute(select(authorities.c.name).order_by(authorities.c.name)).scalars().all()


@router.get("/users")
def list_users(response: Response, page: int = Query(0, ge=0), size: int = Query(20, ge=1, le=200), _: dict = Depends(require_admin)):
    with get_engine().connect() as conn:
        total = conn.execute(select(func.count()).select_from(users)).scalar_one()
        rows = conn.execute(select(users).order_by(users.c.id).offset(page * size).limit(size)).mappings().all()
        response.headers["X-Total-Count"] = str(total)
        return [_dto(r, _roles(conn, r["id"])) for r in rows]


@router.get("/users/{login}")
def get_user(login: str, _: dict = Depends(require_admin)):
    with get_engine().connect() as conn:
        r = conn.execute(select(users).where(users.c.login == login)).mappings().first()
        if not r:
            raise HTTPException(404, "Utilisateur introuvable")
        return _dto(r, _roles(conn, r["id"]))


def _set_roles(conn, user_id: int, roles: list[str]) -> None:
    known = set(conn.execute(select(authorities.c.name)).scalars().all())
    conn.execute(delete(user_authorities).where(user_authorities.c.user_id == user_id))
    for r in roles:
        if r in known:
            conn.execute(insert(user_authorities).values(user_id=user_id, authority_name=r))


@router.post("/users", status_code=201)
def create_user(body: UserIn, admin: dict = Depends(require_admin)):
    if not body.password or len(body.password) < 4:
        raise HTTPException(400, "Mot de passe requis (4 caractères minimum)")
    now = datetime.now(timezone.utc).replace(tzinfo=None)
    try:
        with get_engine().begin() as conn:
            res = conn.execute(
                insert(users).values(
                    login=body.login.lower(),
                    password_hash=hash_password(body.password),
                    first_name=body.firstName,
                    last_name=body.lastName,
                    email=(body.email or None),
                    activated=body.activated,
                    lang_key="fr",
                    created_by=admin["login"],
                    created_date=now,
                    last_modified_by=admin["login"],
                    last_modified_date=now,
                )
            )
            uid = res.inserted_primary_key[0]
            _set_roles(conn, uid, body.authorities)
    except IntegrityError:
        raise HTTPException(400, "Identifiant ou e-mail déjà utilisé")
    return get_user(body.login.lower(), admin)


@router.put("/users/{user_id}")
def update_user(user_id: int, body: UserIn, admin: dict = Depends(require_admin)):
    values = dict(
        login=body.login.lower(),
        first_name=body.firstName,
        last_name=body.lastName,
        email=(body.email or None),
        activated=body.activated,
        last_modified_by=admin["login"],
        last_modified_date=datetime.now(timezone.utc).replace(tzinfo=None),
    )
    if body.password:
        values["password_hash"] = hash_password(body.password)
    if user_id == admin["id"] and (not body.activated or ROLE_ADMIN not in body.authorities):
        raise HTTPException(400, "Vous ne pouvez pas retirer vos propres droits administrateur")
    try:
        with get_engine().begin() as conn:
            if conn.execute(update(users).where(users.c.id == user_id).values(**values)).rowcount == 0:
                raise HTTPException(404, "Utilisateur introuvable")
            _set_roles(conn, user_id, body.authorities)
    except IntegrityError:
        raise HTTPException(400, "Identifiant ou e-mail déjà utilisé")
    return get_user(body.login.lower(), admin)


@router.delete("/users/{login}", status_code=204)
def delete_user(login: str, admin: dict = Depends(require_admin)):
    if login == admin["login"]:
        raise HTTPException(400, "Vous ne pouvez pas supprimer votre propre compte")
    with get_engine().begin() as conn:
        r = conn.execute(select(users.c.id).where(users.c.login == login)).first()
        if not r:
            raise HTTPException(404, "Utilisateur introuvable")
        conn.execute(delete(user_authorities).where(user_authorities.c.user_id == r[0]))
        conn.execute(delete(users).where(users.c.id == r[0]))
    return Response(status_code=204)
