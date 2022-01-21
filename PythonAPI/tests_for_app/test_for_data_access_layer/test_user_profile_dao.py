from custom_exceptions.User_Id_Not_Found import UserIdNotFoundException
from custom_exceptions.user_image_not_found import UserImageNotFound
from custom_exceptions.user_not_found import UserNotFound
from data_access_layer.implementation_classes.user_profile_dao_imp import UserProfileDAOImp, UserProfileDAO
from entities.user import User

user_profile_dao: UserProfileDAO = UserProfileDAOImp()


def test_get_user_profile_success():
    # assert user_profile_dao.get_user_profile(10000)
    show_user = user_profile_dao.get_user_profile(10)
    print(show_user)
    assert show_user.user_id == 10


def test_get_user_profile_success_2():
    # assert user_profile_dao.get_user_profile(10000)
    show_user = user_profile_dao.get_user_profile(10000)
    print(show_user)
    assert show_user.user_id == 10000


def test_get_user_profile_fail():
    try:
        user_profile_dao.get_user_profile(1)
        assert False
    except UserIdNotFoundException as e:
        assert str(e) == "User Id Does Not Exist"


# def test_update_user_profile(): """Happy test to see if user is updated correctly""" updated_user = User(10000,
# "test_first_name", "test_last_name", "test@email.com", "test_username", "test_passcode", "Updating Profile About
# me", "2022-01-21", "test_image") updated_profile: User = UserProfileDAOImp.update_user_profile(updated_user) assert
# updated_profile.user_about == "Updating Profile About me"


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
