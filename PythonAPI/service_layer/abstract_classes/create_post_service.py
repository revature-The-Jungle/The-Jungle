from abc import ABC, abstractmethod

from entities.post import Post


class CreatePostService(ABC):

    @abstractmethod
    def create_post_service(self, post: Post) -> Post:
        pass

    @abstractmethod
    def create_post_image_service(self, post_id: int, image: str) -> bool:
        pass
