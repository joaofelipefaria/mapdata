from fastapi import APIRouter, Depends, HTTPException, Response, status
from sqlalchemy.orm import Session

try:
    from .. import crud
    from ..database import get_db
    from ..schemas.metadata import (
        MetadataCreate,
        MetadataUpdate,
        MetadataResponse,
    )
except ImportError:  # pragma: no cover - fallback for script execution
    import crud
    from database import get_db
    from schemas.metadata import (
        MetadataCreate,
        MetadataUpdate,
        MetadataResponse,
    )

router = APIRouter(tags=["Metadata"])


@router.get(
    "/{mapdata_id}/metadata",
    response_model=list[MetadataResponse],
)
def get_metadata(
    mapdata_id: int,
    db: Session = Depends(get_db),
):
    metadata = crud.get_metadata_by_mapdata_id(
        db,
        mapdata_id,
    )

    if not metadata:
        return Response(status_code=status.HTTP_204_NO_CONTENT)

    return metadata


@router.get(
    "/{mapdata_id}/metadata/{metadata_id}",
    response_model=MetadataResponse,
)
def get_metadata_by_id(
    mapdata_id: int,
    metadata_id: int,
    db: Session = Depends(get_db),
):
    metadata = crud.get_metadata_by_id(
        db,
        metadata_id,
    )

    if metadata is None:
        raise HTTPException(
            status_code=404,
            detail="Metadata not found",
        )

    return metadata


@router.post(
    "/{mapdata_id}/metadata",
    response_model=MetadataResponse,
    status_code=status.HTTP_201_CREATED,
)
def create_metadata(
    mapdata_id: int,
    request: MetadataCreate,
    db: Session = Depends(get_db),
):
    return crud.create_metadata(
        db,
        mapdata_id,
        request,
    )


@router.put(
    "/{mapdata_id}/metadata/{metadata_id}",
    response_model=MetadataResponse,
)
def update_metadata(
    mapdata_id: int,
    metadata_id: int,
    request: MetadataUpdate,
    db: Session = Depends(get_db),
):
    metadata = crud.update_metadata(
        db,
        mapdata_id,
        metadata_id,
        request,
    )

    if metadata is None:
        raise HTTPException(
            status_code=404,
            detail="Metadata not found",
        )

    return metadata


@router.delete(
    "/{mapdata_id}/metadata/{metadata_id}",
    status_code=status.HTTP_204_NO_CONTENT,
)
def delete_metadata(
    mapdata_id: int,
    metadata_id: int,
    db: Session = Depends(get_db),
):
    deleted = crud.delete_metadata(
        db,
        metadata_id,
    )

    if not deleted:
        raise HTTPException(
            status_code=404,
            detail="Metadata not found",
        )


@router.delete(
    "/{mapdata_id}/metadata/all",
    status_code=status.HTTP_204_NO_CONTENT,
)
def delete_all_metadata(
    mapdata_id: int,
    db: Session = Depends(get_db),
):
    crud.delete_metadata_by_mapdata_id(
        db,
        mapdata_id,
    )