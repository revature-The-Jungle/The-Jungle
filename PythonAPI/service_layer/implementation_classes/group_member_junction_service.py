from data_access_layer.implementation_classes.group_member_junction_dao_imp import GroupMemberJunctionDao
from entities.group_member_junction import GroupMemberJunction
from service_layer.abstract_classes.group_member_junction_service import GroupMemberJunctionServiceAbs
from custom_exceptions.group_member_junction_exceptions import *


class GroupMemberJunctionService(GroupMemberJunctionServiceAbs):
    def __init__(self, group_member_junction_dao: GroupMemberJunctionDao):
        self.group_member_junction_dao = group_member_junction_dao

    def get_all_users_in_a_group(self) -> list[GroupMemberJunction]:
        return self.group_member_junction_dao.get_all_users_in_a_group()

    def leave_group(self, user_id: int, group_id: int):
        check_list = self.group_member_junction_dao.get_all_users_in_a_group()

        if not isinstance(user_id, int) and isinstance(group_id, int):
            raise WrongType("only use numbers")
        else:
            for check in check_list:
                if check.group_id == group_id:
                    if check.user_id != user_id:
                        raise WrongId("The user Id is incorrect")
                    elif check.user_id == user_id:
                        return self.group_member_junction_dao.leave_group(user_id,group_id)
                else:
                    raise WrongId("Wrong Group ID")
