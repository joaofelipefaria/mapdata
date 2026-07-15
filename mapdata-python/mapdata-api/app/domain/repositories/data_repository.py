from abc import ABC 
from abc import abstractmethod 
from app.domain.entities.data import Data 

class DataRepository(ABC): 
    @abstractmethod 
    def find_all(self) -> list[Data]: 
        ... 
    @abstractmethod 
    def find_by_id(self, id: int) -> Data | None: 
        ... 
    @abstractmethod 
    def save(self, entity: Data) -> Data: 
        ... 
    @abstractmethod 
    def delete(self, id: int) -> None: 
        ...