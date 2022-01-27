from abc import ABC, abstractmethod

from entities.post import Post


class PostFeedDao(ABC):

    @abstractmethod
    def get_all_posts(self)->list[Post]:
        pass

    @abstractmethod
    def get_all_posts_with_group_id(self, groupid : int) -> list[Post]:
        pass

    @abstractmethod
    def delete_a_post(self, postid : int) -> bool:
        pass

