from sqlalchemy.orm import Session

try:
    from ..models import Metadata
    from ..schemas.metadata import MetadataCreate, MetadataUpdate
except ImportError:  # pragma: no cover - fallback for script execution
    from models import Metadata
    from schemas.metadata import MetadataCreate, MetadataUpdate

def create_metadata(db, request):

    metadata = Metadata(
        data_id=request.data_id,
        value1=request.value1,
        value2=request.value2,
    )

    db.add(metadata)
    db.commit()
    db.refresh(metadata)

    return metadata


def get_all_metadata(db):
    return db.query(Metadata).all()


def get_metadata(db, metadata_id):

    return (
        db.query(Metadata)
        .filter(Metadata.id == metadata_id)
        .first()
    )


def update_metadata(db, metadata_id, request):

    metadata = get_metadata(db, metadata_id)

    if metadata is None:
        return None

    metadata.value1 = request.value1
    metadata.value2 = request.value2

    db.commit()
    db.refresh(metadata)

    return metadata


def delete_metadata(db, metadata_id):

    metadata = get_metadata(db, metadata_id)

    if metadata is None:
        return False

    db.delete(metadata)
    db.commit()

    return True