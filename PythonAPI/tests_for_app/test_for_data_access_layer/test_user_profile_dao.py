from psycopg.errors import ForeignKeyViolation

from data_access_layer.implementation_classes.user_profile_dao_imp import UserProfileDAOImp, UserProfileDAO

user_profile_dao: UserProfileDAO = UserProfileDAOImp()


def test_get_user_profile():
    show_user = user_profile_dao.get_user_profile(10000)
    assert show_user.user_id == 10000


def test_get_user_image_success():
    image = user_profile_dao.get_user_image(10000)
    print(image)
    assert image


def test_update_user_image_success():
    assert user_profile_dao.update_user_image(10000, "thisisahappytest")


# def test_update_user_image_failure():
#     try:
#         user_profile_dao.update_user_image(-5, "this is a sad test")
#         assert False
#     except ForeignKeyViolation:
#         assert True


def update_image_format():
    pass


def update_first_name():
    pass


def update_last_name():
    pass


def update_username():
    pass


def update_password():
    pass


def update_about_me():
    pass


def update_birthdate():
    pass
