from sqlalchemy import ForeignKey 
from sqlalchemy import String 
from sqlalchemy.orm import Mapped 
from sqlalchemy.orm import mapped_column 
from sqlalchemy.orm import relationship 
from app.config.database import Base 

class MetadataModel(Base): 
    __tablename__ = "metadata" 
    id: Mapped[int] = mapped_column( 
        primary_key=True, 
        autoincrement=True, 
    ) 
    data_id: Mapped[int] = mapped_column( 
        ForeignKey("mapdata.id"), 
        nullable=False, 
    ) 
    value1: Mapped[str] = mapped_column( 
        String(255), 
        nullable=False, 
    ) 
    value2: Mapped[str] = mapped_column( 
        String(255), 
        nullable=False, 
    ) 
    data = relationship( 
        "DataModel", 
        back_populates="metadata_list", 
    )