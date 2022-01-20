from unittest.mock import MagicMock, patch, Mock

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
    pass


def test_create_post_image_service_failure_not_str():
    pass
