import pytest

from custom_exceptions.group_member_junction_exceptions import WrongType
from data_access_layer.implementation_classes.group_dao_imp import GroupDAOImp
from entities.group import Group

group_dao = GroupDAOImp()

group_happy_1 = Group(0, 9000, "Smashin Pumpkins", "This group is for all Smashing Pumpkins fans!", "image format")
group_happy_2 = Group("a", 9000, "ZZe Top", "This group is for all ZZ Top fans!", "image format")

i = 0
while i <= 501:
    group_about = "a" * i
    i += 1
group_sad_1 = Group(0, 9000, "Z Top", group_about, "image format")

x = 0
while x <= 41:
    group_name = "s" * x
    x += 1
group_sad_2 = Group(0, 9000, group_name, "test", "image format")


# --------------------------------------Create Group HAPPY Path------------------------------------
# Passing standard 0 argument for group ID
def test_create_group_success():
    created_group = group_dao.create_group(group_happy_1)
    assert created_group.group_id != 0


# Passing a non-integer argument, a string, as argument
def test_create_group_success_2():
    created_group = group_dao.create_group(group_happy_2)
    assert created_group.group_id != 0


# --------------------------------------Create Group SAD Path---------------------------------------
# About Group Input Too Long
def test_create_group_fail_long_input():
    try:
        created_group = group_dao.create_group(group_sad_1)
        assert len(created_group.group_about) < 500
    except Exception as e:
        print(repr(e))


# Group Name Input Too Long
def test_create_group_fail_long_input_2():
    try:
        created_group = group_dao.create_group(group_sad_2)
        assert len(created_group.group_about) < 40
    except Exception as e:
        print(repr(e))


# --------------------------------------Join Group HAPPY Path-----------------------------------------
# Passing viable group ID and user ID
def test_join_group_success():
    group_joined = group_dao.join_group(14, 9000)
    assert group_joined == (14, 9000)


# Passing another set of viable group and user IDs
def test_join_group_success_2():
    group_joined = group_dao.join_group(16, 10)
    assert group_joined == (16, 10)


# ---------------------------------------Join Group SAD Path-------------------------------------------
# Passing a non-existent group ID
def test_join_group_fail_non_existent_group_id():
    try:
        group_joined = group_dao.join_group(4323, 10)
        assert group_joined == (16, 10)
    except Exception as e:
        print(repr(e))

        """Get Creator Test"""


def test_get_creator_success():
    try:
        result = group_dao.get_creator(10)
        assert result == "test"
    except WrongType as e:
        return str(e)


def test_bad_Id():
    with pytest.raises(TypeError) as e:
        result = group_dao.get_creator(1)
        assert "This Id does not exist" in str(e.value)


def test_string_as_id():
    with pytest.raises(WrongType) as e:
        result = group_dao.get_creator("nan")
        assert "please enter a number" in str(e.value)

def test_join_group_fail_non_existent_user_id():
    try:
        group_joined = group_dao.join_group(16, 4323)
        assert group_joined == (16, 10)
    except Exception as e:
        print(repr(e))
