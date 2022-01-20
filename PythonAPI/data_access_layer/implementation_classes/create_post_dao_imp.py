from data_access_layer.abstract_classes.create_post_dao import CreatePostDAO
from util.database_connection import connection
from entities.post import Post


class CreatePostDAOImp(CreatePostDAO):

    def create_post(self, post: Post) -> Post:
        """a method to create a post in the database"""
        pass

    def create_post_image(self, post_id: int, image: str) -> str:
        """a method to place a post image into the database"""
        # insert the image into the database
        sql = f"INSERT INTO post_picture_table VALUES (default, {post_id}, '{image}')"
        cursor = connection.cursor()
        cursor.execute(sql)
        connection.commit()

        # get the new image from the database and send it back
        sql = f"select picture from post_picture_table where post_id = {post_id};"
        cursor.execute(sql)
        image = cursor.fetchone()[0]
        image_decoded = image.decode('utf-8')
        return image_decoded


