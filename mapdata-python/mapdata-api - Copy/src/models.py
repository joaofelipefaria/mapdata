from sqlalchemy import ForeignKey, Integer, String
from sqlalchemy.orm import DeclarativeBase, Mapped, mapped_column, relationship


class Base(DeclarativeBase):
    pass


class Data(Base):

    __tablename__ = "data"

    id: Mapped[int] = mapped_column(Integer, primary_key=True)
    value: Mapped[str] = mapped_column(String)

    metadata_items = relationship(
        "Metadata",
        back_populates="data",
        cascade="all, delete-orphan"
    )


class Metadata(Base):

    __tablename__ = "metadata"

    id: Mapped[int] = mapped_column(Integer, primary_key=True)

    data_id: Mapped[int] = mapped_column(
        ForeignKey("data.id")
    )

    value1: Mapped[str] = mapped_column(String)

    value2: Mapped[str] = mapped_column(String)

    data = relationship(
        "Data",
        back_populates="metadata_items"
    )