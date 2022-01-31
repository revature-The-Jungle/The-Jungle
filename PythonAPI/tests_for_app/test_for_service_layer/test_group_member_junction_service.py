from unittest.mock import MagicMock, patch

from data_access_layer.implementation_classes.group_member_junction_dao_imp import GroupMemberJunctionDao
from entities.group_member_junction import GroupMemberJunction
from service_layer.implementation_classes.group_member_junction_service_imp import GroupMemberJunctionService
from custom_exceptions.group_member_junction_exceptions import *

group_mem_dao = GroupMemberJunctionDao()
group_mem_service = GroupMemberJunctionService(group_mem_dao)
mem_list: list[GroupMemberJunction] = [GroupMemberJunction("first name", "last_name", 1, 1),
                                       GroupMemberJunction("test", "test", 2, 1)]


def test_leave_group():
    group_mem_service.group_member_junction_dao.get_all_users_in_a_group = MagicMock(return_value=mem_list)
    group_mem_service.group_member_junction_dao.leave_group = MagicMock.return_value = True
    try:
        group_mem_service.leave_group(1,2)
        assert False
    except WrongId as e:
        assert str(e) == "Wrong Group ID"
