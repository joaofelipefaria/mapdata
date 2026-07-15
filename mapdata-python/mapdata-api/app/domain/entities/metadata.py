from dataclasses import dataclass 

@dataclass(slots=True) 
class Metadata: 
    id: int | None 
    data_id: int 
    value1: str 
    value2: str