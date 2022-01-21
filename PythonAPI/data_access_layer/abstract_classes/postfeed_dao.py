from abc import ABC, abstractmethod

from entities.post import Post


class PostfeedDao(ABC):

    @abstractmethod
    def get_all_posts(self)->list[Post]:
        pass

    @abstractmethod
    def delete_a_post(self, postid : int) -> bool:
        pass

