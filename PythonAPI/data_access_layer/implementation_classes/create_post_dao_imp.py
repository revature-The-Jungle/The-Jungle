from custom_exceptions.post_not_found import PostNotFound
from data_access_layer.abstract_classes.create_post_dao import CreatePostDAO
from util.database_connection import connection
from entities.post import Post


class CreatePostDAOImp(CreatePostDAO):

    def create_post(self, post: Post) -> Post:
        """a method to create a post in the database"""
        sql = "insert into post_table values(default, %s, %s, %s, %s, %s, default) returning post_id"
        cursor = connection.cursor()
        cursor.execute(sql, (post.user_id, post.group_id, post.post_text, post.image_format, post.likes))
        connection.commit()
        generated_id = cursor.fetchone()[0]
        post.post_id = generated_id
        return post

    def create_post_image(self, post_id: int, image: str) -> str:
        """a method to place a post image into the database"""
        # Check to see if the post id is in the database, raise an error otherwise.
        sql = f"select * from post_table where post_id = %(post_id)s;"
        cursor = connection.cursor()
        cursor.execute(sql, {"post_id": post_id})
        if not cursor.fetchone():
            raise PostNotFound('The post could not be found.')

        # insert the image into the database
        sql = f"INSERT INTO post_picture_table VALUES (default, %(post_id)s, %(image)s)"
        cursor = connection.cursor()
        cursor.execute(sql, {"post_id": post_id, "image": image})
        connection.commit()

        # get the new image from the database and send it back
        sql = f"select picture from post_picture_table where post_id = %(post_id)s;"
        cursor.execute(sql, {"post_id": post_id})
        connection.commit()
        image = cursor.fetchone()[0]
        image_decoded = image.decode('utf-8')
        return image_decoded
