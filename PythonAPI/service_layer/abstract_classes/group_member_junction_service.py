from abc import ABC, abstractmethod

from entities.group_member_junction import GroupMemberJunction


class GroupMemberJunctionServiceAbs(ABC):
    @abstractmethod
    # A method to display a list of users in a particular group
    def get_all_users_in_a_group(self, group_id) -> list[GroupMemberJunction]:
        pass

    @abstractmethod
    # method to handle when the user decides to leave a group or any group
    def leave_group(self, user_id: int, group_id: int):
        pass
