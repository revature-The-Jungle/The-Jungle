from PythonAPI.data_access_layer.implementation_classes.group_member_junction_dao import GroupMemberJunctionDao

group_member_junction_dao = GroupMemberJunctionDao()


def test_get_all_users_in_a_group():
    result = group_member_junction_dao.get_all_users_in_a_group()
    assert len(result) >= 2


def test_leave_group():
    result = group_member_junction_dao.leave_group()
    assert result
