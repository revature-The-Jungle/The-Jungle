from data_access_layer.abstract_classes.create_post_dao import CreatePostDAO
from entities.post import Post


class CreatePostDAOImp(CreatePostDAO):

    def create_post(self, post: Post) -> Post:
        """a method to create a post in the database"""

        pass

    def create_post_image(self, image: str) -> bool:
        """a method to place an image into the database"""
        pass
