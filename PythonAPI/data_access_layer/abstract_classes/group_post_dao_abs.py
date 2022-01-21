from abc import ABC, abstractmethod

from PythonAPI.entities.group_post import GroupPost


class GroupPostDAOAbs(ABC):
    @abstractmethod
    def create_post(self, post: GroupPost) -> GroupPost:
        pass

    @abstractmethod
    def create_post_image(self, image: str) -> bool:
        pass

    @abstractmethod
    def get_post_by_id(self, post_id: int) -> GroupPost:
        pass

    @abstractmethod
    def get_all_posts(self) -> list[GroupPost]:
        pass

    @abstractmethod
    def get_all_posts_by_group_id(self, group_id: int) -> list[GroupPost]:
        pass

    @abstractmethod
    def delete_post_by_post_id(self, post_id: int) -> bool:
        pass
