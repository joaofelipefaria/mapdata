from sqlalchemy.orm import Session

try:
    from ..models import Data
    from ..schemas.data import DataCreate, DataUpdate
except ImportError:  # pragma: no cover - fallback for script execution
    from models import Data
    from schemas.data import DataCreate, DataUpdate

def create_data(db: Session, request: DataCreate):

    data = Data(value=request.value)

    db.add(data)
    db.commit()
    db.refresh(data)

    return data


def get_all_data(db: Session):

    return db.query(Data).all()


def get_data(db: Session, data_id: int):

    return db.query(Data).filter(Data.id == data_id).first()


def update_data(db: Session, data_id: int, request: DataUpdate):

    data = get_data(db, data_id)

    if data is None:
        return None

    data.value = request.value

    db.commit()
    db.refresh(data)

    return data


def delete_data(db: Session, data_id: int):

    data = get_data(db, data_id)

    if data is None:
        return False

    db.delete(data)
    db.commit()

    return True