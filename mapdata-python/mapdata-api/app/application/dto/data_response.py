from pydantic import BaseModel, ConfigDict 

class DataResponse(BaseModel): 
    id: int 
    value: str 
    model_config = ConfigDict( 
        from_attributes=True 
    )