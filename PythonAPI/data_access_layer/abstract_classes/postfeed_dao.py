from abc import ABC, abstractmethod

from entities.post import Post


class PostfeedDao(ABC):

    @abstractmethod
    def get_all_posts(self)->list[Post]:
        pass

    @abstractmethod
    def create_post_image(self, post_id: int, image: str) -> str:
        pass

    @abstractmethod
    def get_post_image(self, post_id: int) -> str:
        pass
    
    def delete_a_post(self, postid : int) -> bool:
        pass

