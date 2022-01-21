from PythonAPI.custom_exceptions.post_exceptions import PostNotFound
from PythonAPI.data_access_layer.abstract_classes.group_post_dao_abs import GroupPostDAOAbs
from PythonAPI.entities.group_post import GroupPost
from PythonAPI.util.database_connection import connection


class GroupPostDAO(GroupPostDAOAbs):
    def create_post(self, post: GroupPost) -> GroupPost:
        try:
            sql = "insert into post_table values(default, %s, %s, %s, %s, %s, default) returning post_id"
            cursor = connection.cursor()
            cursor.execute(sql, (post.user_id, post.group_id, post.post_text, post.image_format, post.likes))
            connection.commit()
            generated_id = cursor.fetchone()[0]
            post.post_id = generated_id
            return post
        except TypeError:
            raise TypeError("Too many arguments")

    def create_post_image(self, image: str) -> bool:
        pass

    def get_post_by_id(self, post_id: int) -> GroupPost:
        sql = "select * from post_table where post_id = %s"
        cursor = connection.cursor()
        cursor.execute(sql, [post_id])
        post_record = cursor.fetchone()
        post = GroupPost(*post_record)
        return post

    def get_all_posts(self) -> list[GroupPost]:
        try:
            sql = "select * from post_table"
            cursor = connection.cursor()
            cursor.execute(sql)
            post_records = cursor.fetchall()
            post_list = []
            for post in post_records:
                post_list.append(GroupPost(*post))
            return post_list
        except PostNotFound as e:
            raise str(e) == "Post Not Found!"

    def get_all_posts_by_group_id(self, group_id: int) -> list[GroupPost]:
        sql = "select * from post_table where group_id = %s"
        cursor = connection.cursor()
        cursor.execute(sql, [group_id])
        post_records = cursor.fetchall()
        post_list = []
        for post in post_records:
            post_list.append(GroupPost(*post))
        return post_list

    def delete_post_by_post_id(self, post_id: int) -> bool:
        try:
            sql = "delete from post_table where post_id = %s"
            cursor = connection.cursor()
            cursor.execute(sql, [post_id])
            connection.commit()
            return True
        except PostNotFound as e:
            raise str(e) == "Post Not Found!"
