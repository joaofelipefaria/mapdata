from abc import ABC 
from abc import abstractmethod 
from app.domain.entities.metadata import Metadata 

class MetadataRepository(ABC): 
    @abstractmethod 
    def find_all(self) -> list[Metadata]: 
        ... 
    @abstractmethod 
    def find_by_id(self, id: int) -> Metadata | None: 
        ... 
    @abstractmethod 
    def find_by_data_id( self, data_id: int, ) -> list[Metadata]: 
        ... 
    @abstractmethod 
    def save( self, entity: Metadata, ) -> Metadata: 
        ... 
    @abstractmethod 
    def delete( self, id: int, ) -> None: 
        ...