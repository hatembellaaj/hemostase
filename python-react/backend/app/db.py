"""
Tables SQLAlchemy.

Les noms de tables et de colonnes sont identiques à ceux de l'application JHipster
(fiche_hemophilie, jhi_user, ...), ce qui permet de brancher le backend Python
directement sur une base MariaDB existante.
"""
from sqlalchemy import (
    BigInteger,
    Boolean,
    Column,
    Date,
    DateTime,
    Float,
    ForeignKey,
    Integer,
    MetaData,
    String,
    Table,
    Text,
    create_engine,
    inspect,
    text,
)
from sqlalchemy.engine import Engine
from sqlalchemy.types import TypeEngine

from .config import get_settings
from .schema import entities

metadata = MetaData()

users = Table(
    "jhi_user",
    metadata,
    Column("id", BigInteger().with_variant(Integer, "sqlite"), primary_key=True, autoincrement=True),
    Column("login", String(50), unique=True, nullable=False),
    Column("password_hash", String(60), nullable=False),
    Column("first_name", String(50)),
    Column("last_name", String(50)),
    Column("email", String(191), unique=True),
    Column("image_url", String(256)),
    Column("activated", Boolean, nullable=False, default=True),
    Column("lang_key", String(10)),
    Column("activation_key", String(20)),
    Column("reset_key", String(20)),
    Column("created_by", String(50), nullable=False, default="system"),
    Column("created_date", DateTime),
    Column("reset_date", DateTime),
    Column("last_modified_by", String(50)),
    Column("last_modified_date", DateTime),
)

authorities = Table(
    "jhi_authority",
    metadata,
    Column("name", String(50), primary_key=True),
)

user_authorities = Table(
    "jhi_user_authority",
    metadata,
    Column("user_id", BigInteger().with_variant(Integer, "sqlite"), ForeignKey("jhi_user.id", ondelete="CASCADE"), primary_key=True),
    Column("authority_name", String(50), ForeignKey("jhi_authority.name"), primary_key=True),
)


def _sql_type(field: dict) -> TypeEngine:
    """
    Les fiches comptent jusqu'à 225 colonnes. Deux limites MariaDB/InnoDB s'appliquent :
      - 65 535 octets au total par ligne (VARCHAR comptés à leur taille maximale) ;
      - ~8 Ko stockés dans la ligne elle-même : en utf8mb4, seules les colonnes de plus
        de 255 octets (VARCHAR >= 64 caractères) peuvent être déportées hors de la ligne.
    Les colonnes texte/énumération font donc au moins 64 caractères en base ; la longueur
    maximale d'origine (25, 255, ...) reste contrôlée par l'API.
    """
    t = field["type"]
    if t == "string":
        n = field.get("maxLength") or 255
        return Text() if n >= 255 else String(max(64, n))
    if t == "enum":
        longest = max((len(o["value"]) for o in field.get("options", [])), default=64)
        return String(max(64, longest))
    if t == "integer":
        return Integer()
    if t == "float":
        return Float(precision=53)  # DOUBLE en MariaDB
    if t == "boolean":
        return Boolean()
    if t == "date":
        return Date()
    if t == "datetime":
        return DateTime()
    return String(255)


fiche_tables: dict[str, Table] = {}
for ent in entities():
    cols = [Column("id", BigInteger().with_variant(Integer, "sqlite"), primary_key=True, autoincrement=True)]
    for f in ent["fields"]:
        cols.append(Column(f["column"], _sql_type(f), nullable=not f.get("required"), key=f["name"]))
    fiche_tables[ent["slug"]] = Table(ent["table"], metadata, *cols)


_engine: Engine | None = None


def get_engine() -> Engine:
    global _engine
    if _engine is None:
        url = get_settings().database_url
        kwargs = {"pool_pre_ping": True}
        if url.startswith("sqlite"):
            kwargs["connect_args"] = {"check_same_thread": False}
        _engine = create_engine(url, **kwargs)
    return _engine


def init_db() -> None:
    """Crée les tables manquantes et adapte une base JHipster existante."""
    engine = get_engine()
    metadata.create_all(engine, checkfirst=True)
    if engine.dialect.name in ("mysql", "mariadb"):
        _ensure_auto_increment(engine)


def _ensure_auto_increment(engine: Engine) -> None:
    """
    Dans JHipster, les identifiants viennent d'une séquence Hibernate et la colonne `id`
    n'est pas AUTO_INCREMENT. On l'active pour que le backend Python puisse insérer.
    MariaDB reprend automatiquement à MAX(id)+1 : les données existantes sont conservées.
    """
    insp = inspect(engine)
    tables = ["jhi_user"] + [t.name for t in fiche_tables.values()]
    with engine.begin() as conn:
        # les tables JHipster dépassent souvent la limite stricte de taille de ligne InnoDB :
        # sans cela, la reconstruction déclenchée par ALTER TABLE serait refusée
        conn.execute(text("SET SESSION innodb_strict_mode = 0"))
        for name in tables:
            row = conn.execute(
                text(
                    "SELECT EXTRA FROM information_schema.COLUMNS "
                    "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = :t AND COLUMN_NAME = 'id'"
                ),
                {"t": name},
            ).first()
            if row is not None and "auto_increment" not in (row[0] or "").lower():
                # les clés étrangères vers jhi_user.id empêchent le MODIFY : on les retire le temps de l'opération
                fks = []
                if name == "jhi_user":
                    for fk in insp.get_foreign_keys("jhi_user_authority"):
                        if fk["referred_table"] == "jhi_user":
                            fks.append(fk)
                            conn.execute(text(f"ALTER TABLE jhi_user_authority DROP FOREIGN KEY `{fk['name']}`"))
                conn.execute(text(f"ALTER TABLE `{name}` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT"))
                for fk in fks:
                    conn.execute(
                        text(
                            f"ALTER TABLE jhi_user_authority ADD CONSTRAINT `{fk['name']}` "
                            "FOREIGN KEY (user_id) REFERENCES jhi_user(id)"
                        )
                    )
