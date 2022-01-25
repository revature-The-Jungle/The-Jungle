from abc import ABC, abstractmethod

from entities.post import Post


class PostFeedDao(ABC):

    @abstractmethod
    def get_all_posts(self)->list[Post]:
        pass

    
    def delete_a_post(self, post_id : int) -> bool:
        pass

