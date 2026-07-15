from fastapi import FastAPI

from routers.data import router as data_router
from routers.metadata import router as metadata_router

app = FastAPI()

app.include_router(data_router, prefix="/api/mapdata", tags=["Data"])
app.include_router(metadata_router, prefix="/api/mapdata", tags=["Metadata"])