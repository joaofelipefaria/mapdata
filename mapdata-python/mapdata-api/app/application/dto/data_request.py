from pydantic import BaseModel, ConfigDict 

class CreateDataRequest(BaseModel): 
    value: str 
    model_config = ConfigDict( 
        from_attributes=True 
    ) 

class UpdateDataRequest(BaseModel): 
    value: str 
    model_config = ConfigDict( 
        from_attributes=True 
    ) 