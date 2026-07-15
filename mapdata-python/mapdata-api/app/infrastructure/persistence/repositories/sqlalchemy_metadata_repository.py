from sqlalchemy import select 
from app.domain.entities.metadata import Metadata 
from app.domain.repositories.metadata_repository import MetadataRepository 
from app.infrastructure.persistence.models.metadata_model import MetadataModel 
from app.infrastructure.persistence.mappers.metadata_entity_mapper import ( 
    MetadataEntityMapper, 
) 

class SqlAlchemyMetadataRepository(MetadataRepository): 
    def __init__( self, session_factory, ): 
        self.session_factory = session_factory 
    
    def find_all(self) -> list[Metadata]: 
        with self.session_factory() as session: 
            models = session.scalars( select(MetadataModel) ).all() 
            return [ 
                MetadataEntityMapper.to_domain(model) 
                for model in models 
            ] 
    
    def find_by_id( 
            self, 
            id: int, 
        ) -> Metadata | None: 
        with self.session_factory() as session: 
            model = session.get( MetadataModel, id, ) 
            if model is None: 
                return None 
            return MetadataEntityMapper.to_domain(model) 
    
    def find_by_data_id( 
            self, 
            data_id: int, 
        ) -> list[Metadata]: 
        with self.session_factory() as session: 
            models = session.scalars( select(MetadataModel).where( MetadataModel.data_id == data_id ) ).all() 
            return [ 
                MetadataEntityMapper.to_domain(model) 
                for model in models 
            ] 
    
    def save( 
            self, 
            entity: Metadata, 
        ) -> Metadata: 
        with self.session_factory() as session: 
            if entity.id is None: 
                model = MetadataEntityMapper.to_model(entity) 
                session.add(model) 
            else: 
                model = session.get( MetadataModel, entity.id, ) 
                model.data_id = entity.data_id 
                model.value1 = entity.value1 
                model.value2 = entity.value2 
            session.commit() 
            session.refresh(model) 
            return MetadataEntityMapper.to_domain(model) 
    
    def delete( 
            self, 
            id: int, 
        ): 
        with self.session_factory() as session: 
            model = session.get( MetadataModel, id, ) 
            if model: 
                session.delete(model) 
            session.commit() 