from abc import ABC, abstractmethod

from PythonAPI.entities.group import Group


class GroupDAO(ABC):
    @abstractmethod
    def create_group(self, group: Group):
        pass

    @abstractmethod
    def join_group(self, group_id: int, user_id: int):
        pass
