from fastapi import FastAPI
from app.adapters.inbound.api.data_controller import router as data_router

app = FastAPI(
    title="MapData API", 
    description="API for managing map data", 
    version="1.0.0"
)

app.include_router(data_router)