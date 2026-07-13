from dataclasses import dataclass
from typing import Optional

@dataclass
class Metadata:
    id: Optional[int]
    data_id: int
    value1: str
    value2: str
