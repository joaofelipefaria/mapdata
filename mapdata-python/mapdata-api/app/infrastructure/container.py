from dependency_injector import containers 
from dependency_injector import providers 
from app.config.database import SessionLocal 
from app.infrastructure.persistence.repositories.sqlalchemy_data_repository import ( 
    SqlAlchemyDataRepository, 
) 
from app.infrastructure.persistence.repositories.sqlalchemy_metadata_repository import (
    SqlAlchemyMetadataRepository, 
) 
from app.application.services.data_service import DataService 
from app.application.services.metadata_service import MetadataService 

class Container(containers.DeclarativeContainer): 
    wiring_config = containers.WiringConfiguration( 
        packages=[ "app.adapters.inbound.rest" ] 
    ) 
    
    session = providers.Object(SessionLocal) 
    data_repository = providers.Factory( 
        SqlAlchemyDataRepository, 
        session_factory=session, 
    ) 
    metadata_repository = providers.Factory( 
        SqlAlchemyMetadataRepository, 
        session_factory=session, 
    ) 
    data_service = providers.Factory( 
        DataService, 
        repository=data_repository, 
    ) 
    metadata_service = providers.Factory( 
        MetadataService, 
        repository=metadata_repository, 
    ) 