from pydantic import BaseModel, ConfigDict 

class MetadataResponse(BaseModel): 
    id: int 
    data_id: int 
    value1: str 
    value2: str 
    model_config = ConfigDict( 
        from_attributes=True 
    ) 