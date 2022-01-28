from abc import ABC, abstractmethod
from typing import List

from entities.post import Post


class PostFeedDao(ABC):

    @abstractmethod
    def get_all_posts(self)-> List[Post]:
        pass

    @abstractmethod
    def get_all_posts_with_group_id(self, groupid : int) -> List[Post]:
        pass

    @abstractmethod
    def delete_a_post(self, postid : int) -> bool:
        pass

