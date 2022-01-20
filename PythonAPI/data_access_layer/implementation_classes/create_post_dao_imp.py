from PythonAPI.data_access_layer.abstract_classes.create_post_dao import CreatePostDAO
from PythonAPI.entities.post import Post
from PythonAPI.util.database_connection import connection


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


    def create_post_image(self, image: str) -> bool:
        """a method to place an image into the database"""
        pass
