from data_access_layer.abstract_classes.postfeed_dao import PostfeedDao
from entities.post import Post
from util.database_connection import connection


class PostfeedDaoimpl(PostfeedDao):
    def get_all_posts(self) -> list[Post]:
        sql = "select * from post_table"
        cursor = connection.cursor()
        cursor.execute(sql)
        post_record = cursor.fetchall()
        post_list = []
        for post in post_record:
            post_list.append(Post(*post))
        return post_list





    def delete_a_post(self, postid: int) -> bool:
        sql = " delete from post_table where post_id = %s"
        cursor = connection.cursor()
        cursor.execute(sql,[postid])
        connection.commit()
        return True
