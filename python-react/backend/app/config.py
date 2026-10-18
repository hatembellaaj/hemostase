from functools import lru_cache

from pydantic_settings import BaseSettings, SettingsConfigDict


class Settings(BaseSettings):
    model_config = SettingsConfigDict(env_file=".env", extra="ignore")

    app_name: str = "Registre Hémostase"
    # ex : mysql+pymysql://user:pass@db:3306/hemostase?charset=utf8mb4
    database_url: str = "sqlite:///./hemostase.db"

    jwt_secret: str = "change-me-please-change-me-please-change-me-please"
    jwt_algorithm: str = "HS512"
    jwt_expiration_minutes: int = 60 * 24  # 1 jour
    jwt_remember_me_minutes: int = 60 * 24 * 30  # 30 jours

    # Comptes créés au premier démarrage si la table des utilisateurs est vide
    admin_login: str = "admin"
    admin_password: str = "admin"
    create_default_user: bool = True

    # Charge les fiches de démonstration (fake-data JHipster) si les tables sont vides
    seed_demo_data: bool = False

    cors_origins: str = "http://localhost:5173,http://localhost:8080"


@lru_cache
def get_settings() -> Settings:
    return Settings()
