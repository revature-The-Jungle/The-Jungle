import base64

from data_access_layer.abstract_classes.create_post_dao import CreatePostDAO
from entities.post import Post


class CreatePostDAOImp(CreatePostDAO):

    def create_post(self, post: Post) -> Post:
        """a method to create a post in the database"""
        pass

    def create_post_image(self, user_id: int, image: str) -> bool:
        """a method to place an image into the database"""
        blob = base64.standard_b64encode(image)
        sql = "INSERT INTO employee_picture_table VALUES (default, ?, ?)"

