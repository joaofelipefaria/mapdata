from pydantic import BaseModel, ConfigDict 

class CreateMetadataRequest(BaseModel): 
    data_id: int 
    value1: str 
    value2: str 
    model_config = ConfigDict( 
        from_attributes=True 
    ) 

class UpdateMetadataRequest(BaseModel): 
    data_id: int 
    value1: str 
    value2: str 
    model_config = ConfigDict( 
        from_attributes=True 
    ) 