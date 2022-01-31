import pytest

from data_access_layer.implementation_classes.group_member_junction_dao_imp import GroupMemberJunctionDao
from entities.group_member_junction import GroupMemberJunction
from custom_exceptions.group_member_junction_exceptions import *

group_member_junction_dao = GroupMemberJunctionDao()


def test_get_all_users_in_a_group():
    result = group_member_junction_dao.get_all_users_in_a_group()
    assert len(result) >= 2


def test_list_contains_correct_info():
    result = group_member_junction_dao.get_all_users_in_a_group()
    print(result)
    mem = result[1]
    assert mem.first_name == "test create post"


def test_to_many():
    with pytest.raises(TypeError) as e:
        group_member_junction_dao.get_all_users_in_a_group(1)
        assert "you have put the incorrect amount of arguments" in str(e.value)


def test_leave_group():
    result = group_member_junction_dao.leave_group(10000, 9)
    assert result


def test_leave_group2():
    result = group_member_junction_dao.leave_group(10000, 10)
    assert result


def test_failed_leave_group():
    with pytest.raises(TypeError) as e:
        group_member_junction_dao.leave_group(16, 10, 5)
        assert "too many arguments" in str(e.value)


def test_wrong_user():
    try:
        group_member_junction_dao.leave_group(1, 1)
    except WrongId as e:
        assert str(e) == "Incorrect ID"
