from unittest.mock import MagicMock

from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from custom_exceptions.user_id_must_be_an_integer import UserIdMustBeAnInteger
from data_access_layer.implementation_classes.postfeed_dao import PostFeedDaoImp
from service_layer.implementation_classes.postfeed_service import PostFeedServiceImp

postfeed_dao = PostFeedDaoImp()
postfeed_service = PostFeedServiceImp(postfeed_dao)


def test_get_all_posts_service_success():
    postfeed_dao.get_all_posts = MagicMock(return_value=True)
    assert postfeed_service.get_all_posts_service()


def test_delete_a_post_service_success():
    postfeed_dao.delete_a_post = MagicMock(return_value=True)
    assert postfeed_service.delete_a_post_service(1)


def test_delete_a_post_service_failure_not_int():
    postfeed_dao.delete_a_post = MagicMock(return_value=True)
    try:
        postfeed_service.delete_a_post_service("fails")
        assert False
    except PostIdMustBeAnInteger as e:
        assert str(e) == 'The post id must be an integer.'


def test_get_all_posts_by_user_id_service_success():
    postfeed_dao.get_all_posts_with_user_id = MagicMock(return_value=True)
    assert postfeed_service.get_all_posts_by_user_id_service(1)


def test_get_all_posts_by_user_id_service_failure_not_int():
    postfeed_dao.get_all_posts_by_user_id = MagicMock(return_value=True)
    try:
        postfeed_service.get_all_posts_by_user_id_service("failure")
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == 'The user id must be an integer.'
