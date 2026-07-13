from fastapi import FastAPI

from app.adapters.inbound.api.models.create_data_request import CreateDataRequest
from app.adapters.outbound.persistence.in_memory_data_repository import InMemoryDataRepository

from app.application.use_cases.create_data import CreateDataUseCase

router = APIRouter()

repository = InMemoryDataRepository()
create_data_use_case = CreateDataUseCase(repository)

@router.post("/data")
def create_data(request: CreateDataRequest):
    return create_data_use_case.execute(request.value)