from app.domain.entities.data import Data 
from app.infrastructure.persistence.models.data_model import DataModel 

class DataEntityMapper: 
    @staticmethod 
    def to_domain( 
        model: DataModel, 
    ) -> Data: 
        return Data( 
            id=model.id, 
            value=model.value, 
        )
    
    @staticmethod
    def to_model( 
            entity: Data, 
        ) -> DataModel: 
        model = DataModel() 
        model.id = entity.id 
        model.value = entity.value 
        return model