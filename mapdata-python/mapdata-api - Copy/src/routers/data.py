from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

try:
    from .. import crud
    from ..database import get_db
    from ..schemas.data import DataCreate, DataUpdate, DataResponse
except ImportError:  # pragma: no cover - fallback for script execution
    import crud
    from database import get_db
    from schemas.data import DataCreate, DataUpdate, DataResponse

router = APIRouter()


@router.post("/", response_model=DataResponse)
def create(
    request: DataCreate,
    db: Session = Depends(get_db),
):
    return crud.create_data(db, request)


@router.get("/all", response_model=list[DataResponse])
def list_all(
    db: Session = Depends(get_db),
):
    return crud.get_all_data(db)


@router.get("/{data_id}", response_model=DataResponse)
def find(
    data_id: int,
    db: Session = Depends(get_db),
):
    data = crud.get_data(db, data_id)

    if data is None:
        raise HTTPException(
            status_code=404,
            detail="Data not found",
        )

    return data


@router.put("/{data_id}", response_model=DataResponse)
def update(
    data_id: int,
    request: DataUpdate,
    db: Session = Depends(get_db),
):
    data = crud.update_data(db, data_id, request)

    if data is None:
        raise HTTPException(
            status_code=404,
            detail="Data not found",
        )

    return data


@router.delete("/{data_id}")
def delete(
    data_id: int,
    db: Session = Depends(get_db),
):
    deleted = crud.delete_data(db, data_id)

    if not deleted:
        raise HTTPException(
            status_code=404,
            detail="Data not found",
        )

    return {
        "message": "Deleted successfully"
    }