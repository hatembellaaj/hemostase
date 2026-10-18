from datetime import datetime, timezone

from fastapi import APIRouter, Depends, HTTPException
from pydantic import BaseModel
from sqlalchemy import update

from ..db import get_engine, users
from ..security import create_token, get_current_user, hash_password, load_user, verify_password

router = APIRouter(prefix="/api", tags=["authentification"])


class LoginIn(BaseModel):
    username: str
    password: str
    rememberMe: bool = False


class PasswordChangeIn(BaseModel):
    currentPassword: str
    newPassword: str


class AccountIn(BaseModel):
    firstName: str | None = None
    lastName: str | None = None
    email: str | None = None


def account_dto(u: dict) -> dict:
    return {
        "id": u["id"],
        "login": u["login"],
        "firstName": u["first_name"],
        "lastName": u["last_name"],
        "email": u["email"],
        "activated": u["activated"],
        "langKey": u["lang_key"],
        "authorities": u["authorities"],
    }


@router.post("/authenticate")
def authenticate(body: LoginIn):
    u = load_user(body.username.lower())
    if not u or not verify_password(body.password, u["password_hash"]):
        raise HTTPException(401, "Identifiant ou mot de passe incorrect")
    if not u["activated"]:
        raise HTTPException(401, "Compte désactivé")
    return {"id_token": create_token(u["login"], u["authorities"], body.rememberMe)}


@router.get("/account")
def account(user: dict = Depends(get_current_user)):
    return account_dto(user)


@router.post("/account")
def save_account(body: AccountIn, user: dict = Depends(get_current_user)):
    with get_engine().begin() as conn:
        conn.execute(
            update(users)
            .where(users.c.id == user["id"])
            .values(
                first_name=body.firstName,
                last_name=body.lastName,
                email=body.email or None,
                last_modified_by=user["login"],
                last_modified_date=datetime.now(timezone.utc).replace(tzinfo=None),
            )
        )
    return account_dto(load_user(user["login"]))


@router.post("/account/change-password")
def change_password(body: PasswordChangeIn, user: dict = Depends(get_current_user)):
    if not verify_password(body.currentPassword, user["password_hash"]):
        raise HTTPException(400, "Mot de passe actuel incorrect")
    if not 4 <= len(body.newPassword) <= 100:
        raise HTTPException(400, "Le mot de passe doit contenir entre 4 et 100 caractères")
    with get_engine().begin() as conn:
        conn.execute(update(users).where(users.c.id == user["id"]).values(password_hash=hash_password(body.newPassword)))
    return {"ok": True}
