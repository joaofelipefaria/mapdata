from dataclasses import dataclass
from typing import Optional

@dataclass
class Data:
    id: Optional[int]
    value: str