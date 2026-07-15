from app.domain.entities.metadata import Metadata 
from app.application.dto.metadata_response import MetadataResponse 

class MetadataMapper: 
    
    @staticmethod 
    def to_response( 
        entity: Metadata, 
    ) -> MetadataResponse: 
        return MetadataResponse( 
            id=entity.id, 
            data_id=entity.data_id, 
            value1=entity.value1, 
            value2=entity.value2, 
        ) @staticmethod 
    
    def to_response_list( 
            entities: list[Metadata], 
        ) -> list[MetadataResponse]: 
        return [ 
            MetadataMapper.to_response(entity) 
            for entity in entities 
        ]