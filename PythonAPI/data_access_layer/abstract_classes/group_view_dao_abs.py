from abc import ABC, abstractmethod
from typing import List

from entities.group import Group


class GroupViewDao(ABC):

    @abstractmethod
    def get_group_by_id(self, group_id) -> Group:
        pass

    @abstractmethod
    def get_all_groups(self) -> List[Group]:
        pass
