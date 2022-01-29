
from data_access_layer.abstract_classes.like_post_dao import LikePostDAO
from util.database_connection import connection


class LikePostDaoImp(LikePostDAO):
    def like_post(self,post_id:int):
            sql = "update post_table set likes = likes + 1 where post_id=%s returning likes"
            cursor = connection.cursor()
            cursor.execute(sql,[post_id])
            connection.commit()
            generated_likes_number = cursor.fetchone()[0]
            return generated_likes_number