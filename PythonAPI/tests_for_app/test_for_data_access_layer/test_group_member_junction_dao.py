from PythonAPI.data_access_layer.implementation_classes.group_member_junction_dao import GroupMemberJunctionDao
from PythonAPI.entities.group_member_junction import GroupMemberJunction

group_member_junction_dao = GroupMemberJunctionDao()


def test_get_all_users_in_a_group():
    result = group_member_junction_dao.get_all_users_in_a_group()
    assert len(result) >= 2


def test_list_contains_correct_info():
    result = group_member_junction_dao.get_all_users_in_a_group()
    print(result)
    mem = result[1]
    assert mem.first_name == "test create post"


def test_leave_group():
    result = group_member_junction_dao.leave_group(10000, 4)
    assert result


def test_leave_group2():
    result = group_member_junction_dao.leave_group(9000, 4)
    assert result
