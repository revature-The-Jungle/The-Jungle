from unittest.mock import MagicMock

from custom_exceptions.comment_id_must_be_an_integer import CommentIdMustBeAnInteger
from custom_exceptions.comment_must_be_a_string import CommentMustBeAString
from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from custom_exceptions.user_id_must_be_an_integer import UserIdMustBeAnInteger
from data_access_layer.implementation_classes.comment_dao import CommentDAOImp
from service_layer.implementation_classes.comment_service import CommentServiceImp

comment_dao: CommentDAOImp = CommentDAOImp()
comment_service: CommentServiceImp = CommentServiceImp(comment_dao)


def test_service_create_comment_success():
    comment_dao.create_comment = MagicMock(return_value="comment created")
    assert comment_service.service_create_comment(1, 1, "Settra Rules!", 1, 1) == "comment created"


def test_service_create_comment_failure_not_int():
    comment_dao.create_comment = MagicMock(return_value="comment created")
    try:
        comment_service.service_create_comment(1, "user_id", "Settra Rules!", 1, 1)
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == 'The user id must be an integer.'


def test_service_create_comment_failure_not_text():
    comment_dao.create_comment = MagicMock(return_value="comment created")
    try:
        comment_service.service_create_comment(1, 1, 1, 1, 1)
        assert False
    except CommentMustBeAString as e:
        assert str(e) == 'The comment must be a string.'


def test_service_get_comment_by_post_id_success():
    comment_dao.get_comment_by_post_id = MagicMock(return_value=True)
    assert comment_service.service_get_comment_by_post_id(1)


def test_service_get_comment_by_post_id_failure_not_int():
    comment_dao.get_comment_by_post_id = MagicMock(return_value="here's the comment")
    try:
        comment_service.service_get_comment_by_post_id("Whats the frequency, kenneth.")
        assert False
    except PostIdMustBeAnInteger as e:
        assert str(e) == 'The post id must be an integer.'


def test_service_delete_comment_success():
    comment_dao.delete_comment = MagicMock(return_value=True)
    assert comment_service.service_delete_comment(1)


def test_service_delete_comment_failure_not_int():
    comment_dao.delete_comment = MagicMock(return_value="the comment was deleted")
    try:
        comment_service.service_delete_comment("error")
        assert False
    except CommentIdMustBeAnInteger as e:
        assert str(e) == 'The comment id must be an integer.'
