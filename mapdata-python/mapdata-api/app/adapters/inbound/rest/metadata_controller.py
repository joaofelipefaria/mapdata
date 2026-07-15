from dependency_injector.wiring import Provide, inject 
from fastapi import APIRouter, Depends, status 

from app.application.dto.metadata_request import CreateMetadataRequest, UpdateMetadataRequest 
from app.application.mappers.metadata_mapper import MetadataMapper 
from app.application.services.metadata_service import MetadataService 
from app.infrastructure.container import Container 

router = APIRouter( 
    prefix="/metadata", 
    tags=["Metadata"], 
) 

@router.get("/") 
@inject 
def find_all( 
    service: MetadataService = Depends( 
        Provide[Container.metadata_service] 
    ), 
): 
    return MetadataMapper.to_response_list( service.find_all() ) 

@router.get("/{id}") 
@inject 
def find_by_id( 
    id: int, 
    service: MetadataService = Depends( 
        Provide[Container.metadata_service] 
    ), 
): 
    return MetadataMapper.to_response( 
        service.find_by_id(id) 
    ) 

@router.get("/data/{data_id}") 
@inject
def find_by_data( 
    data_id: int, 
    service: MetadataService = Depends( 
        Provide[Container.metadata_service] 
    ), 
): 
    return MetadataMapper.to_response_list( 
        service.find_by_data_id(data_id) 
    ) 

@router.post( 
    "/", 
    status_code=status.HTTP_201_CREATED, 
) 

@inject 
def create( 
    request: CreateMetadataRequest, 
    service: MetadataService = Depends( 
        Provide[Container.metadata_service] 
    ), 
): 
    return MetadataMapper.to_response( 
        service.create( 
            request.data_id, 
            request.value1, 
            request.value2, 
        ) 
    ) 

@router.put("/{id}") 
@inject 
def update( 
    id: int, 
    request: UpdateMetadataRequest, 
    service: MetadataService = Depends( 
        Provide[Container.metadata_service] 
    ), 
): 
    return MetadataMapper.to_response( 
        service.update( 
            id, 
            request.data_id, 
            request.value1, 
            request.value2, 
        ) 
    ) 

@router.delete( 
    "/{id}", 
    status_code=status.HTTP_204_NO_CONTENT, 
) 
@inject 
def delete( 
    id: int, 
    service: MetadataService = Depends( 
        Provide[Container.metadata_service] 
    ), 
): 
    service.delete(id)