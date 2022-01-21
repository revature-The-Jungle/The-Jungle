from abc import ABC, abstractmethod


class GroupDAO(ABC):
    @abstractmethod
    def create_group(self, user_id: int, group_name: str, group_about: str, image_format: str):
        pass

    @abstractmethod
    def join_group_by_group_id(self, group_id: int):
        pass
