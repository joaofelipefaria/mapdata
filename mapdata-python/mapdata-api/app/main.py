from fastapi import FastAPI

from app.config.settings import settings

from app.infrastructure.container import Container

from app.adapters.inbound.rest.data_controller import (
    router as data_router,
)

from app.adapters.inbound.rest.metadata_controller import (
    router as metadata_router,
)

from app.adapters.inbound.rest.exception_handler import (
    entity_not_found_handler,
)

from app.domain.exceptions import EntityNotFoundException


container = Container()

container.wire(
    packages=[
        "app.adapters.inbound.rest",
    ]
)


app = FastAPI(
    title=settings.app_name,
    version="1.0.0",
    docs_url="/docs",
    redoc_url="/redoc",
)

app.container = container

app.include_router(data_router)
app.include_router(metadata_router)

app.add_exception_handler(
    EntityNotFoundException,
    entity_not_found_handler,
)


@app.get(
    "/",
    tags=["Root"],
)
def root():
    return {
        "application": settings.app_name,
        "version": "1.0.0",
        "status": "running",
    }


@app.get(
    "/health",
    tags=["Health"],
)
def health():
    return {
        "status": "UP",
    }


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(
        "app.main:app",
        host=settings.host,
        port=settings.port,
        reload=True,
    )