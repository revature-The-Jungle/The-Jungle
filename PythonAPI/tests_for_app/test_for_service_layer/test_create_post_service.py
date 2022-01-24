from unittest.mock import MagicMock, patch, Mock

from custom_exceptions.image_must_be_a_string import ImageMustBeAString
from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from data_access_layer.abstract_classes.create_post_dao import CreatePostDAO
from data_access_layer.implementation_classes.create_post_dao_imp import CreatePostDAOImp
from service_layer.abstract_classes.create_post_service import CreatePostService
from service_layer.implementation_classes.create_post_service_imp import CreatePostServiceImp

create_post_dao: CreatePostDAO = CreatePostDAOImp()
create_post_service: CreatePostService = CreatePostServiceImp(create_post_dao)


def test_create_post_image_service_success():
    create_post_dao.create_post_image = MagicMock(return_value="thisisareturnvalue")
    assert create_post_service.create_post_image_service(1, "thisisaforwardvalue")


def test_create_post_image_service_failure_not_int():
    try:
        create_post_service.create_post_image_service(1.0, "thisisaforwardvalue")
        assert False
    except PostIdMustBeAnInteger as e:
        assert str(e) == "The post id must be an integer."


def test_create_post_image_service_failure_not_str():
    try:
        create_post_service.create_post_image_service(1, b"thisisaforwardvalue")
        assert False
    except ImageMustBeAString as e:
        assert str(e) == "The image must be a string format."

