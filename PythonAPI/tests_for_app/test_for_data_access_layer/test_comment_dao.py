from pytest import fixture

from custom_exceptions.comment_not_found import CommentNotFound
from custom_exceptions.post_not_found import PostNotFound
from data_access_layer.implementation_classes.comment_dao import CommentDAOImp
from util.database_connection import connection

comment_dao = CommentDAOImp()


@fixture
def create_fake_user():
    """For putting a fake user into the database for testing then removing the fake user."""
    # this is the setup
    sql = "Delete from user_table where user_id >= 100000000;" \
          "Insert into user_table values(100000000, 'first10000', 'last10000', 'email@email.com', 'username1000000', " \
          "'passcode100000', 'about', '1991-08-06', 'gif');" \
          "Insert into user_table values(100000001, 'first10000', 'last10000', 'email100000001@email.com', 'username1000001', " \
          "'passcode100000', 'about', '1991-08-06', 'gif');"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    yield  # everything after the yield is the teardown and called after each test
    sql = "delete from user_table where user_id = 100000000;"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()


@fixture
def create_fake_post(create_fake_user):  # notice that the other fixture has been injected into this one.
    """For putting a fake post into the database for testing then removing the fake user."""
    sql = "Insert into post_table values(100000000, 100000000); " \
          "Insert into post_table values(100000001, 100000000);"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    # no yield necessary because of the database cascade delete,
    # deleting the user will also delete the post from the user in the database


@fixture
def create_fake_group(create_fake_user):  # notice that the other fixture has been injected into this one.
    """For putting a fake post into the database for testing then removing the fake user."""
    sql = "Insert into group_table values(100000000, 100000000, 'Keyser Söze'); "
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    # no yield necessary because of the database cascade delete,
    # deleting the user will also delete the group from the user in the database


@fixture
def create_fake_comment(create_fake_post):
    """For putting a fake post into the database for testing then removing the fake user."""
    sql = "Insert into comment_table values(100000000, 100000000, 100000000, Null, 100000001, 'Keaton');" \
          "Insert into comment_table values(100000001, 100000000, 100000000, Null, 100000001, 'Keaton');" \
          "Insert into comment_table values(100000002, 100000001, 100000000, Null, 100000001, 'Keaton');"
    cursor = connection.cursor()
    cursor.execute(sql)
    connection.commit()
    # no yield necessary because of the database cascade delete,
    # deleting the user will also delete the post from the user in the database


# Create Comment Tests--------------------------------------------
# Happy Path Test 1
def test_create_comment_success_1(create_fake_post, create_fake_group):
    created_comment = comment_dao.create_comment(100000000, 100000000, "hello world 1", 100000000, 100000001)
    print(created_comment.make_dictionary())
    assert created_comment.comment_id != 0


# Happy Path Test 1
def test_create_comment_success_2(create_fake_post, create_fake_group):
    created_comment = comment_dao.create_comment(100000001, 100000001, "hello world 2", 100000000, 10000000)
    print(created_comment.make_dictionary())
    assert created_comment.comment_id != 0


# Happy Path Test 2
def test_create_comment_failure_no_post():
    try:
        comment_dao.create_comment(100000000, 100000000, "hello world 3", 100000000, 100000001)
        assert False
    except PostNotFound as e:
        assert str(e) == 'The post could not be found.'


# Delete Comment Tests--------------------------------------------
# Happy Path Test 1
def test_delete_comment_success(create_fake_comment):
    result = comment_dao.delete_comment(100000000)
    print(result)
    assert result


# Happy Path Test 2
def test_delete_comment_success_2(create_fake_comment):
    result = comment_dao.delete_comment(100000001)
    print(result)
    assert result


# Test delete comment failed
def test_delete_comment_by_id_fail_comment_not_found():
    try:
        comment_dao.delete_comment(100000000)
        assert False
    except CommentNotFound as e:
        assert str(e) == "Comment Not Found"


# ------------------------End Delete Comment

# Get All Comment By Post Id Tests--------------------------------------------
# Happy Path Test 1
def test_to_get_all_comments_by_post_id_list_success_1(create_fake_comment):
    returned_comments = comment_dao.get_comment_by_post_id(100000000)
    for comment in returned_comments:
        print(comment.make_dictionary())
    assert len(returned_comments) == 2


# Happy Path Test 2
def test_to_get_all_comments_by_post_id_list_success_2(create_fake_comment):
    returned_comments = comment_dao.get_comment_by_post_id(100000001)
    print(returned_comments)
    assert len(returned_comments) == 1


# Test post does not exist to place comment
def test_to_get_all_comments_by_post_id_list_fail_no_post():
    try:
        comment_dao.get_comment_by_post_id(100000000)
        assert False
    except PostNotFound as e:
        assert str(e) == 'The post could not be found.'
