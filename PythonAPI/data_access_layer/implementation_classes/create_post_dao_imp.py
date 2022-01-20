from data_access_layer.abstract_classes.create_post_dao import CreatePostDAO
from util.database_connection import connection
from entities.post import Post


class CreatePostDAOImp(CreatePostDAO):

    def create_post(self, post: Post) -> Post:
        """a method to create a post in the database"""
        pass

    def create_post_image(self, post_id: int, image: str) -> bool:
        """a method to place an image into the database"""
        # will need to add extra tests
        sql = f"INSERT INTO post_picture_table VALUES (default, {post_id}, '{image}')"
        cursor = connection.cursor()
        cursor.execute(sql)
        connection.commit()
        return True


