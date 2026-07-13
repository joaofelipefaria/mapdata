from app.domain.entities.data import Data
from app.domain.ports.data_repository import DataRepository

class InMemoryDataRepository(DataRepository):
    def __init__(self):
        self._database = []
        self._next_id = 1

    def save(self, data: Data) -> Data:
        data.id = self._next_id
        self._next_id += 1
        self._database.append(data)
        return data
    
    def find_by_id(self, id: int) -> Data:
        for data in self._database:
            if data.id == id:
                return data
        return None
    
    def find_all(self) -> list[Data]:
        return self._database
    
    def delete(self, id: int) -> None:
        self._database = [
            data
            for data in self._database
            if data.id != id
        ]