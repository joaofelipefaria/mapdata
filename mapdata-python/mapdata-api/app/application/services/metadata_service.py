from app.domain.entities.metadata import Metadata 
from app.domain.exceptions import EntityNotFoundException 
from app.domain.repositories.metadata_repository import MetadataRepository 

class MetadataService: 
    def __init__( 
            self, 
            repository: MetadataRepository, 
        ): 
        self.repository = repository 
    
    def find_all(self): 
        return self.repository.find_all() 
    
    def find_by_id( 
            self, 
            id: int, 
        ): 
        entity = self.repository.find_by_id(id) 
        if entity is None: 
            raise EntityNotFoundException( f"Metadata {id} not found." ) 
        return entity 
    
    def find_by_data_id( 
            self, 
            data_id: int, 
        ): 
        return self.repository.find_by_data_id(data_id) 
    
    def create( 
            self, 
            data_id: int, 
            value1: str, 
            value2: str, 
        ): 
        entity = Metadata( 
            id=None, 
            data_id=data_id, 
            value1=value1, 
            value2=value2, 
        ) 
        return self.repository.save(entity) 
    
    def update( 
            self, 
            id: int, 
            data_id: int, 
            value1: str, 
            value2: str, 
        ): 
        entity = self.find_by_id(id) 
        entity.data_id = data_id 
        entity.value1 = value1 
        entity.value2 = value2 
        return self.repository.save(entity) 
    
    def delete( 
            self, 
            id: int, 
        ): 
        self.find_by_id(id) 
        self.repository.delete(id) 