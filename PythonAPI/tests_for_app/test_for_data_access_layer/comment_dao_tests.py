from datetime import datetime


from data_access_layer.abstract_classes import comment_dao_abs
from data_access_layer.abstract_classes.comment_dao_abs import CommentDAO
from data_access_layer.implementation_classes.comment_dao import CommentDAOImp
from entities import comment

comment_dao = CommentDAOImp()


# Create Comment Tests--------------------------------------------
# Happy Path Test 1
def test_create_comment_success():
    created_comment = comment_dao.create_comment(3, 1, "hello world", 1, 1)
    assert created_comment.comment_id != 0

# Happy Path Test 2
def test_create_comment_success_2():
    try:
         created_comment = comment_dao.create_comment(3, 1, " ", 1, 1)
    except CommentNotCreated as e:
        assert str(e) == "Comment Not Created"

# Sad Path Test
# Test Needs to be reworked.
#     def test_create_comment_fail():


# Delete Comment Tests--------------------------------------------
# Happy Path Test 1
def test_delete_comment_success():
    result = comment_dao.delete_comment(4)
    assert result == True


# Happy Path Test 2
def test_delete_comment_success_2():
    result = comment_dao.delete_comment(9)
    assert result != False


# Test delete comment failed
def test_delete_comment_by_id_fail_comment_not_found():
    try:
        comment_dao.delete_comment(1000)
    except CommentNotFound as e:
        assert str(e) == "Comment Not Found"


# ------------------------End Delete Comment

# Get All Comment By Post Id Tests--------------------------------------------
# Happy Path Test 1
def test_to_get_all_comments_by_post_id_list_success_1():
    returned_comments = comment_dao.get_comment_by_post_id(3)
    assert len(returned_comments) != 0


# Happy Path Test 2
def test_to_get_all_comments_by_post_id_list_success_2():
    returned_comments = comment_dao.get_comment_by_post_id(3)
    assert len(returned_comments) >= 1


# Test post does not exist to place comment
def test_no_post_found():
    try:
        returned_comments = comment_dao.get_comment_by_post_id(300)
    except NoPostFound as e:
        assert str(e) == "No Post Found"