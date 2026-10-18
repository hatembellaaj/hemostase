import logging
from contextlib import asynccontextmanager

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from sqlalchemy import text

from .config import get_settings
from .db import get_engine, init_db
from .routers import auth, fiches, users
from .seed import seed

logging.basicConfig(level=logging.INFO, format="%(asctime)s %(levelname)s %(name)s: %(message)s")


@asynccontextmanager
async def lifespan(_: FastAPI):
    init_db()
    seed()
    yield


settings = get_settings()
app = FastAPI(
    title=settings.app_name,
    version="1.0.0",
    description="API du registre des maladies de l'hémostase (portage Python du projet JHipster adhes2025).",
    lifespan=lifespan,
    docs_url="/api/docs",
    openapi_url="/api/openapi.json",
)
app.add_middleware(
    CORSMiddleware,
    allow_origins=[o.strip() for o in settings.cors_origins.split(",") if o.strip()],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
    expose_headers=["X-Total-Count"],
)

app.include_router(auth.router)
app.include_router(users.router)
app.include_router(fiches.router)


@app.get("/api/health", tags=["système"])
def health():
    with get_engine().connect() as conn:
        conn.execute(text("SELECT 1"))
    return {"status": "UP"}
