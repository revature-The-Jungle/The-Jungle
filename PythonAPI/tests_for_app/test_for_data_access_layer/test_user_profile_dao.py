from custom_exceptions.user_image_not_found import UserImageNotFound
from custom_exceptions.user_not_found import UserNotFound
from data_access_layer.implementation_classes.user_profile_dao_imp import UserProfileDAOImp, UserProfileDAO
from entities.user import User

user_profile_dao: UserProfileDAO = UserProfileDAOImp()
user_about_me_for_tests = "Updating Profile About me"


def test_get_user_profile():
    show_user = user_profile_dao.get_user_profile(10000)
    assert show_user.user_id == 10000


def test_update_user_profile_success():
    """Happy test to see if user is updated correctly"""
    updated_user = User(10000, "test_first_name", "test_last_name", "test@email.com", "test_username", "test_passcode",
                        user_about_me_for_tests, "2022-01-21", "test_image")
    updated_profile: User = user_profile_dao.update_user_profile(updated_user)
    assert updated_profile.user_about == user_about_me_for_tests


def test_update_user_profile_failure_no_user():
    """Sad test to see if there is no user found by the ID"""
    updated_user_fail = User(1000000000, "test_first_name", "test_last_name", "test@email.com", "test_username",
                             "test_passcode", user_about_me_for_tests, "2022-01-21", "test_image")
    try:
        user_profile_dao.update_user_profile(updated_user_fail)
        assert False
    except UserNotFound as e:
        assert str(e) == 'The user could not be found.'

def test_get_user_image_success():
    image = user_profile_dao.get_user_image(10000)
    assert image


def test_get_user_image_failure_no_image():
    try:
        user_profile_dao.get_user_image(-5)
        assert False
    except UserImageNotFound as e:
        assert str(e) == 'The user image could not be found.'


def test_update_user_image_success():
    assert user_profile_dao.update_user_image(10000, "thisisahappytest")


def test_update_user_image_failure_no_user():
    try:
        user_profile_dao.update_user_image(-5, "this is a sad test")
        assert False
    except UserNotFound as e:
        assert str(e) == 'The user could not be found.'


def test_update_image_format():
    pass


def test_update_password():
    pass
