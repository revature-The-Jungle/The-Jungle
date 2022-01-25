from abc import ABC, abstractmethod

from entities.post import Post


class LikePostDAO(ABC):

    @abstractmethod
    def like_post(self, post_id: int) -> Post:
        pass
