from unittest.mock import MagicMock, patch, Mock

from custom_exceptions.User_Id_Not_Found import UserIdNotFoundException
from custom_exceptions.image_must_be_a_string import ImageMustBeAString
from custom_exceptions.user_id_must_be_an_integer import UserIdMustBeAnInteger
from data_access_layer.abstract_classes.user_profile_dao import UserProfileDAO
from data_access_layer.implementation_classes.user_profile_dao_imp import UserProfileDAOImp
from service_layer.abstract_classes.user_profile_service import UserProfileService
from service_layer.implementation_classes.user_profile_service_imp import UserProfileServiceImp

user_profile_dao: UserProfileDAO = UserProfileDAOImp()
user_profile_service: UserProfileService = UserProfileServiceImp(user_profile_dao)


def test_get_user_profile_service():
    user_profile_dao.get_user_profile = MagicMock(user_id='test purpose')
    assert user_profile_service.service_get_user_profile_service(1)


def test_update_user_profile_service():
    pass


def test_get_user_image_service_success():
    user_profile_dao.get_user_image = MagicMock(return_value="thisisareturnvalue")
    assert user_profile_service.get_user_image_service(1)


def test_get_user_image_service_failure_not_int():
    try:
        user_profile_service.get_user_image_service(1.0)
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_update_user_image_service_success():
    user_profile_dao.update_user_image = MagicMock(return_value="thisisareturnvalue")
    assert user_profile_service.update_user_image_service(1, "thisisasketch")


def test_update_user_image_service_failure_not_int():
    try:
        user_profile_service.update_user_image_service(1.0, "thisisasketch")
        assert False
    except UserIdMustBeAnInteger as e:
        assert str(e) == "The user id must be an integer."


def test_update_user_image_service_failure_not_string():
    try:
        user_profile_service.update_user_image_service(1, b"thisisasketch")
        assert False
    except ImageMustBeAString as e:
        assert str(e) == "The image must be a string format."


def test_update_user_image_format_service():
    pass


def test_update_password_service_failure():
    pass
