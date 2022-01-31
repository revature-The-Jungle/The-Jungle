from unittest.mock import MagicMock

from data_access_layer.implementation_classes.comment_dao import CommentDAOImp
from service_layer.implementation_classes.comment_service import CommentServiceImp

comment_dao: CommentDAOImp = CommentDAOImp()
comment_service: CommentServiceImp = CommentServiceImp(comment_dao)


def test_service_create_comment_success():
    comment_dao.create_comment = MagicMock(return_value="comment created")
    assert comment_service.service_create_comment(1, 1, "Settra Rules!", 1, 1) == "comment created"


def test_service_get_comment_by_post_id_success():
    comment_dao.get_comment_by_post_id = MagicMock(return_value="here's the comment")
    assert comment_service.service_get_comment_by_post_id(1) == "here's the comment"


def test_service_delete_comment_success():
    comment_dao.delete_comment = MagicMock(return_value="the comment was deleted")
    assert comment_service.service_delete_comment(1) == "the comment was deleted"
