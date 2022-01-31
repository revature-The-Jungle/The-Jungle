from typing import List

from custom_exceptions.connection_error import ConnectionErrorr
from data_access_layer.abstract_classes.postfeed_dao_abs import PostFeedDao
from entities.post import Post
from util.database_connection import connection


class PostFeedDaoImp(PostFeedDao):

    def get_all_posts(self) -> List[Post]:
        try:
            sql = "select * from post_table"
            cursor = connection.cursor()
            cursor.execute(sql)
            post_record = cursor.fetchall()
            post_list = []
            for post in post_record:
                post_list.append(Post(*post))
            return post_list
        except ConnectionErrorr:
            return "something went wrong"

    def delete_a_post(self, post_id: int) -> bool:
        try:
            sql = " delete from post_table where post_id = %s"
            cursor = connection.cursor()
            cursor.execute(sql, [post_id])
            connection.commit()
            return True
        except ConnectionErrorr:
            return False

    def get_all_posts_with_group_id(self, groupid: int) -> List[Post]:
        pass
