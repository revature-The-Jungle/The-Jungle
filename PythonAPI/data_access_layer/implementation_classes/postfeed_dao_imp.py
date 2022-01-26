from custom_exceptions.connection_error import ConnectionErrorr
from data_access_layer.abstract_classes.postfeed_dao import PostfeedDao
from entities.post import Post
from util.database_connection import connection


class PostfeedDaoimpl(PostfeedDao):
    def get_all_posts(self) -> list[Post]:
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




    def get_all_posts_with_group_id(self, groupid: int) -> list[Post]:
        try:
            sql = "select * from post_table where group_id= %s"
            cursor = connection.cursor()
            cursor.execute(sql,[groupid])
            post_record = cursor.fetchall()
            post_list = []
            for post in post_record:
                post_list.append(Post(*post))
            return post_list
        except ConnectionErrorr:
            return "something went wrong"












    def delete_a_post(self, postid: int) -> bool:
       try:
            sql = " delete from post_table where post_id = %s"
            cursor = connection.cursor()
            cursor.execute(sql,[postid])
            connection.commit()
            return True
       except ConnectionErrorr:
            return False

