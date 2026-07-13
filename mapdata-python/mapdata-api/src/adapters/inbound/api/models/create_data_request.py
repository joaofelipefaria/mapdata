from pydantic import BaseModel

class CreateDataRequest(BaseModel):
    value: str