from custom_exceptions.postIdNonExistent import postidnoneexistent
from data_access_layer.abstract_classes.comment_dao_abs import CommentDAO
from entities import comment
from entities.comment import Comment
from util.database_connection import connection


class CommentDAOImp(CommentDAO):

    def create_comment(self, post_id: int, user_id: int, comment_text: str, group_id: int, reply_user: int) -> Comment:
        sql = "insert into comment_table values(default, %s, %s, %s, %s, %s, %s, default) returning comment_id "
        cursor = connection.cursor()
        cursor.execute(sql, ( post_id, user_id, group_id, reply_user, comment_text, 0 ))
        connection.commit()
        generated_id = cursor.fetchone()[0]
        return generated_id

    def get_comment_by_post_id(self, post_id: int) -> list[Comment]:
       try:
        sql = "select * from comment_table where post_id = %s"
        cursor = connection.cursor()
        cursor.execute(sql, [post_id])
        comment_record = cursor.fetchall()
        comment_list = []
        for comments in comment_record:
            comment_list.append(Comment(*comments))
        return comment_list
       except Exception:
           raise postidnoneexistent

    def delete_comment(self, comment_id: int) -> bool:
        sql = "delete from comment_table where comment_id = %s"
        cursor = connection.cursor()
        cursor.execute(sql, [comment_id])
        connection.commit()
        return True