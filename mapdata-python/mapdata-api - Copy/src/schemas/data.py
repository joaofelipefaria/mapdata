from pydantic import BaseModel, ConfigDict


class DataCreate(BaseModel):
    value: str


class DataUpdate(BaseModel):
    value: str


class DataResponse(BaseModel):
    id: int
    value: str

    model_config = ConfigDict(from_attributes=True)