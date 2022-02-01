from unittest.mock import MagicMock

from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from data_access_layer.implementation_classes.like_post_dao import LikePostDaoImp
from service_layer.implementation_classes.like_post_service import LikePostServiceImp

like_post_dao = LikePostDaoImp()
like_post_service = LikePostServiceImp(like_post_dao)


def test_service_like_post_success():
    like_post_dao.like_post = MagicMock(return_value="post liked")
    assert like_post_service.service_like_post(1)


def test_service_like_post_failure_not_int():
    like_post_dao.like_post = MagicMock(return_value="post liked")
    try:
        like_post_service.service_like_post("hello")
        assert False
    except PostIdMustBeAnInteger as e:
        assert str(e) == 'The post id must be an integer.'
