from app.domain.entities.data import Data 
from app.application.dto.data_response import DataResponse 

class DataMapper: 
    @staticmethod 
    def to_response(entity: Data) -> DataResponse: 
        return DataResponse( 
            id=entity.id, 
            value=entity.value, 
        ) 
    
    @staticmethod 
    def to_response_list( 
        entities: list[Data], 
    ) -> list[DataResponse]: 
        return [ 
            DataMapper.to_response(entity) 
            for entity in entities 
        ]