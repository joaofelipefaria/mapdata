from app.domain.entities.data import Data 
from app.domain.exceptions import EntityNotFoundException 
from app.domain.repositories.data_repository import DataRepository 

class DataService: 
    def __init__( 
            self, 
            repository: DataRepository, 
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
            raise EntityNotFoundException( f"Data {id} not found." ) 
        return entity 
    
    def create( 
            self, 
            value: str, 
        ): 
        entity = Data( 
            id=None, 
            value=value, 
        ) 
        return self.repository.save(entity) 
    
    def update( 
            self, 
            id: int, 
            value: str, 
        ): 
        entity = self.find_by_id(id) 
        entity.value = value 
        return self.repository.save(entity) 
    
    def delete( 
            self, 
            id: int, 
        ): 
        self.find_by_id(id) 
        self.repository.delete(id)