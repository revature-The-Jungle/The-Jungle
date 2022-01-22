from abc import ABC, abstractmethod

from entities.comment import Comment


class CommentService(ABC):

    # create reimbursement request
    @abstractmethod
    def service_create_comment(self, post_id: int, user_id: int, group_id: int, reply_user: int, comment_text: str) -> Comment:
        pass

    # get all comments by post id
    @abstractmethod
    def service_get_comment_by_post_id(self, post_id: int) -> list[Comment]:
        pass

    # delete comment
    @abstractmethod
    def service_delete_comment(self, comment_id: int) -> bool:
        pass