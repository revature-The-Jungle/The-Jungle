from data_access_layer.abstract_classes import comment_dao
from data_access_layer.implementation_classes.comment_dao_imp import CommentDAOImp
from entities import comment
from entities.comment import Comment
from service_layer.abstract_classes.comment_service import CommentService


class CommentServiceImp(CommentService):
    def __init__(self, comment_dao: CommentDAOImp):
        self.comment_dao = comment_dao

    def service_create_comment(self, post_id: int, user_id: int, comment_text: str, group_id: int, reply_user: int) -> Comment:
        id = self.comment_dao.create_comment(post_id, user_id, comment_text, group_id, reply_user )
        return id

    def service_get_comment_by_post_id(self, post_id: int) -> list[Comment]:
        return self.comment_dao.get_comment_by_post_id(post_id)

    def service_delete_comment(self, comment_id: int) -> bool:
        return self.comment_dao.delete_comment(comment_id)