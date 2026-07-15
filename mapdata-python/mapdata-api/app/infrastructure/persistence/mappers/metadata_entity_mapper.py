from app.domain.entities.metadata import Metadata 
from app.infrastructure.persistence.models.metadata_model import MetadataModel

class MetadataEntityMapper: 
    @staticmethod 
    def to_domain( 
        model: MetadataModel, 
    ) -> Metadata: 
        return Metadata( 
            id=model.id, 
            data_id=model.data_id, 
            value1=model.value1, 
            value2=model.value2, 
        ) 
    
    @staticmethod 
    def to_model( 
        entity: Metadata, 
    ) -> MetadataModel: 
        model = MetadataModel() 
        model.id = entity.id 
        model.data_id = entity.data_id 
        model.value1 = entity.value1 
        model.value2 = entity.value2 
        return model