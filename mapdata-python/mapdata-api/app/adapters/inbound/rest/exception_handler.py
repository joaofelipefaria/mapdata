from fastapi import Request 
from fastapi.responses import JSONResponse 
from app.domain.exceptions import EntityNotFoundException 

async def entity_not_found_handler( 
    request: Request, 
    exc: EntityNotFoundException, 
): 
    return JSONResponse( 
        status_code=404, 
        content={ 
            "message": str(exc) 
        }, 
    )