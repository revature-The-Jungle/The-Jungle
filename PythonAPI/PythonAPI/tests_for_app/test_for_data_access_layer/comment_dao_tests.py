from datetime import datetime

from data_access_layer.abstract_classes import comment_dao
from data_access_layer.abstract_classes.comment_dao import CommentDAO
from data_access_layer.implementation_classes.comment_dao_imp import CommentDAOImp
from entities import comment

comment_dao = CommentDAOImp()


user_id = 1;

def test_create_comment_success():
    created_comment = comment_dao.create_comment(3, 1, "hello world", 1, 1)
    assert created_comment.comment_id != 0


def test_delete_comment_success():
    result = comment_dao.delete_comment(4)
    assert result == True


def test_to_get_all_comments_by_post_id():
    returned_comments = comment_dao.get_comment_by_post_id(3)
    assert len(returned_comments) == 3