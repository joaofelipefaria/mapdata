from app.domain.entities.data import Data
from app.domain.ports.data_repository import DataRepository

class CreateDataUseCase:
    def __init__(self,repository: DataRepository):
        self.repository = repository

    def execute(self, value: str) -> Data:
        data = Data(id=None, value=value)
        return self.repository.save(data)