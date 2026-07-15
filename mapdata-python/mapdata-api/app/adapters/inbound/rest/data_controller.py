from dependency_injector.wiring import Provide, inject 
from fastapi import APIRouter, Depends, status 
from app.application.dto.data_request import CreateDataRequest, UpdateDataRequest 
from app.application.mappers.data_mapper import DataMapper 
from app.application.services.data_service import DataService 
from app.infrastructure.container import Container 

router = APIRouter( 
    prefix="/data", 
    tags=["Data"], 
) 

@router.get("/") 
@inject 
def find_all( 
    service: DataService = Depends( 
        Provide[Container.data_service] 
    ), 
): 
    return DataMapper.to_response_list( 
        service.find_all() 
    ) 

@router.get("/{id}") 
@inject 
def find_by_id( 
    id: int, 
    service: DataService = Depends( 
        Provide[Container.data_service] 
    ), 
): 
    return DataMapper.to_response( 
        service.find_by_id(id) 
    ) 

@router.post( "/", status_code=status.HTTP_201_CREATED, ) 
@inject 
def create( 
    request: CreateDataRequest, 
    service: DataService = Depends( 
        Provide[Container.data_service] 
    ),
): 
    return DataMapper.to_response( 
        service.create(request.value) 
    ) 

@router.put("/{id}") 
@inject 
def update( 
    id: int, 
    request: UpdateDataRequest, 
    service: DataService = Depends( 
        Provide[Container.data_service] 
    ), 
): 
    return DataMapper.to_response( 
        service.update( 
            id, 
            request.value, 
        ) 
    ) 

@router.delete( 
    "/{id}", 
    status_code=status.HTTP_204_NO_CONTENT, 
) 

@inject 
def delete( 
    id: int, 
    service: DataService = Depends( 
        Provide[Container.data_service] 
    ), 
): 
    service.delete(id)