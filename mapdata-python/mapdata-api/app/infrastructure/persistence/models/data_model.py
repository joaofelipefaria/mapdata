from sqlalchemy import String 
from sqlalchemy.orm import Mapped 
from sqlalchemy.orm import mapped_column 
from sqlalchemy.orm import relationship 
from app.config.database import Base 

class DataModel(Base): 
    __tablename__ = "mapdata" 
    id: Mapped[int] = mapped_column( 
        primary_key=True, 
        autoincrement=True, 
    ) 
    value: Mapped[str] = mapped_column( 
        String(255), 
        nullable=False, 
    ) 
    metadata_list = relationship( 
        "MetadataModel", 
        back_populates="data", 
        cascade="all, delete-orphan", 
    )