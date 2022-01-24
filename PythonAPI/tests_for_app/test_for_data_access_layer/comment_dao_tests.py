from datetime import datetime

from data_access_layer.abstract_classes import comment_dao
from data_access_layer.abstract_classes.comment_dao import CommentDAO
from data_access_layer.implementation_classes.comment_dao_imp import CommentDAOImp
from entities import comment

comment_dao = CommentDAOImp()


user_id = 1;

def test_create_comment_success1():
    id = comment_dao.create_comment(1, 1, "hello world", 1, 1)
    assert id != 0

def test_create_comment_success2():
    id = comment_dao.create_comment(1, 11, "Good Bye", 2, 1)
    assert id != 0



def test_delete_comment_success1():
    result = comment_dao.delete_comment(4)
    assert result == True


def test_delete_comment_success2():
    result = comment_dao.delete_comment(14)
    assert result == True


def test_to_get_all_comments_by_post_id1():
    returned_comments = comment_dao.get_comment_by_post_id(3)
    assert len(returned_comments) == 3


def test_to_get_all_comments_by_post_id2():
    returned_comments = comment_dao.get_comment_by_post_id(12)
    assert len(returned_comments) == 1