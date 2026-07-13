from abc import ABC, abstractmethod
from app.domain.entities.data import Data

class DataRepository(ABC):
    @abstractmethod
    def save(self, data: Data) -> Data:
        pass

    @abstractmethod
    def find_by_id(self, id: int) -> Data:
        pass

    @abstractmethod
    def find_all(self) -> list[Data]:
        pass

    @abstractmethod
    def delete(self, id: int) -> None:
        pass   