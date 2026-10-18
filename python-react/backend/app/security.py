from datetime import datetime, timedelta, timezone

import bcrypt
import jwt
from fastapi import Depends, HTTPException, status
from fastapi.security import HTTPAuthorizationCredentials, HTTPBearer
from sqlalchemy import select

from .config import get_settings
from .db import get_engine, user_authorities, users

ROLE_ADMIN = "ROLE_ADMIN"
ROLE_USER = "ROLE_USER"

bearer = HTTPBearer(auto_error=False)


def hash_password(password: str) -> str:
    # même format que Spring Security ($2a$10$...) : les mots de passe existants restent valides
    return bcrypt.hashpw(password.encode(), bcrypt.gensalt(rounds=10, prefix=b"2a")).decode()


def verify_password(password: str, hashed: str) -> bool:
    try:
        return bcrypt.checkpw(password.encode(), hashed.encode())
    except ValueError:
        return False


def create_token(login: str, roles: list[str], remember_me: bool = False) -> str:
    s = get_settings()
    minutes = s.jwt_remember_me_minutes if remember_me else s.jwt_expiration_minutes
    now = datetime.now(timezone.utc)
    payload = {"sub": login, "auth": " ".join(roles), "iat": now, "exp": now + timedelta(minutes=minutes)}
    return jwt.encode(payload, s.jwt_secret, algorithm=s.jwt_algorithm)


def load_user(login: str) -> dict | None:
    with get_engine().connect() as conn:
        u = conn.execute(select(users).where(users.c.login == login)).mappings().first()
        if not u:
            return None
        roles = conn.execute(
            select(user_authorities.c.authority_name).where(user_authorities.c.user_id == u["id"])
        ).scalars().all()
    user = dict(u)
    user["authorities"] = sorted(roles)
    return user


def get_current_user(creds: HTTPAuthorizationCredentials | None = Depends(bearer)) -> dict:
    if creds is None:
        raise HTTPException(status.HTTP_401_UNAUTHORIZED, "Authentification requise")
    s = get_settings()
    try:
        payload = jwt.decode(creds.credentials, s.jwt_secret, algorithms=[s.jwt_algorithm])
    except jwt.PyJWTError:
        raise HTTPException(status.HTTP_401_UNAUTHORIZED, "Jeton invalide ou expiré")
    user = load_user(payload.get("sub", ""))
    if not user or not user["activated"]:
        raise HTTPException(status.HTTP_401_UNAUTHORIZED, "Utilisateur inconnu ou désactivé")
    return user


def require_admin(user: dict = Depends(get_current_user)) -> dict:
    if ROLE_ADMIN not in user["authorities"]:
        raise HTTPException(status.HTTP_403_FORBIDDEN, "Droits administrateur requis")
    return user
