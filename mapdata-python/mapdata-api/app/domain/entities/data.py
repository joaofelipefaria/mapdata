from dataclasses import dataclass 

@dataclass(slots=True) 
class Data: 
    id: int | None 
    value: str