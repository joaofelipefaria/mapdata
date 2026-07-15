from sqlalchemy import select 
from app.domain.entities.data import Data 
from app.domain.repositories.data_repository import DataRepository 
from app.infrastructure.persistence.models.data_model import DataModel 
from app.infrastructure.persistence.mappers.data_entity_mapper import (
    DataEntityMapper,
) 

class SqlAlchemyDataRepository(DataRepository): 
    def __init__( self, session_factory, ): 
        self.session_factory = session_factory 
    
    def find_all(self) -> list[Data]: 
        with self.session_factory() as session: 
            models = session.scalars( 
                select(DataModel) 
            ).all() 
            return [ 
                DataEntityMapper.to_domain(model) 
                for model in models 
            ] 
        
    def find_by_id( 
            self, 
            id: int,
        ) -> Data | None: 
        with self.session_factory() as session: 
            model = session.get( 
                DataModel, 
                id, 
            ) 
            if model is None: 
                return None 
            return DataEntityMapper.to_domain(model) 

    def save( 
            self, 
            entity: Data, 
        ) -> Data: 
        with self.session_factory() as session: 
            if entity.id is None: 
                model = DataEntityMapper.to_model(entity) 
                session.add(model) 
            else: 
                model = session.get( DataModel, entity.id, ) 
                model.value = entity.value 
            session.commit() 
            session.refresh(model) 
            return DataEntityMapper.to_domain(model) 

    def delete( 
            self, 
            id: int, 
        ): 
        with self.session_factory() as session: 
            model = session.get( DataModel, id, ) 
            if model: 
                session.delete(model) 
            session.commit() 