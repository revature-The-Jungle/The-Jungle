from unittest.mock import MagicMock

from PythonAPI.custom_exceptions.post_exceptions import InvalidInput
from PythonAPI.data_access_layer.implementation_classes.group_post_dao_imp import GroupPostDAO
from PythonAPI.entities.group_post import GroupPost
from PythonAPI.service_layer.implementation_classes.group_post_service_imp import GroupPostService

post_dao = GroupPostDAO()
post_service = GroupPostService(post_dao)


# ------------------------------ TEST CREATE POST ------------------------------
def test_mock_create_post_success():
    created_post = GroupPost(9000, 9000, 9000, "test create post service", "none", 0, "2020-1-24")
    post_service.post_dao.create_post = MagicMock(return_value=created_post)
    post_service.service_create_post(created_post)
    assert post_service.service_create_post(created_post)


def test_mock_create_empty_string():
    created_post = GroupPost(9000, 9000, 9000, "", "none", 0, "2020-1-24")
    try:
        post_service.post_dao.create_post = MagicMock(return_value=created_post)
        post_service.service_create_post(created_post)
        assert False
    except InvalidInput as e:
        assert str(e) == "No Input Given!"


def test_mock_create_long_string():
    created_post = GroupPost(9000, 9000, 9000, "Lorem ipsum dolor sit amet," +
                             "consectetuer adipiscing elit. Aenean commodo " +
                             "ligula eget dolor. Aenean massa. Cum sociis natoque " +
                             "penatibus et magnis dis parturient montes, nascetur ridiculus " +
                             "mus. Donec quam felis, ultricies nec, pellentesque eu, pretium " +
                             "quis, sem. Nulla consequat massa quis enim. Donec pede justo, " +
                             "fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, " +
                             "rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum " +
                             "felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. " +
                             "Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. " +
                             "Aenean leo ligula, porttitor eu,", "none", 0, "2020-1-24")
    try:
        post_service.post_dao.create_post = MagicMock(return_value=created_post)
        post_service.service_create_post(created_post)
        assert False
    except InvalidInput as e:
        assert str(e) == "Messages too long!"


def test_mock_create_post_negative_user_id():
    created_post = GroupPost(9000, -9000, 9000, "", "none", 0, "2020-1-24")
    try:
        post_service.post_dao.create_post = MagicMock(return_value=created_post)
        post_service.service_create_post(created_post)
        assert False
    except InvalidInput as e:
        assert str(e) == "Invalid Input!"


def test_mock_create_wrong_type():
    created_post = GroupPost("something", 9000, 9000, "", "none", 0, "2020-1-24")
    try:
        post_service.post_dao.create_post = MagicMock(return_value=created_post)
        post_service.service_create_post(created_post)
        assert False
    except InvalidInput as e:
        assert str(e) == "You need to enter number for post ID!"


# ------------------------------ TEST GET POST BY ID ------------------------------
def test_mock_get_post_success():
    post_service.service_get_post_by_id = MagicMock(return_value=post_service)
    created_post = GroupPost(9000, 9000, 9000, "test get post service", "none", 0, "2020-1-24")
    assert post_service.service_get_post_by_id(created_post.post_id)


def test_mock_get_post_failed():
    post_service.service_get_post_by_id = MagicMock(return_value=post_service)
    created_post = GroupPost(-9000, 9000, 9000, "test get post service", "none", 0, "2020-1-24")
    try:
        post_service.service_get_post_by_id(created_post.post_id)
        assert False
    except InvalidInput as e:
        assert str(e) == "Invalid Input!"


# ------------------------------ TEST GET ALL POSTS ------------------------------
def test_mock_get_all_posts_success():
    post_service.service_get_all_posts = MagicMock(return_value=post_service)
    assert post_service.service_get_all_posts()


# ------------------------------ TEST GET ALL POST BY GROUP ID ------------------------------
def test_mock_get_posts_by_group_id_success():
    post_service.service_get_all_posts_by_group_id = MagicMock(return_value=post_service)
    assert post_service.service_get_all_posts_by_group_id()


# ------------------------------ TEST DELETE POST BY ID ------------------------------
def test_mock_delete_post_success():
    post_service.service_delete_post_by_post_id = MagicMock(return_value=post_service)
    assert post_service.service_delete_post_by_post_id()
