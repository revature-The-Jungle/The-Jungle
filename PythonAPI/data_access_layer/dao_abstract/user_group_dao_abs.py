from abc import ABC, abstractmethod

from PythonAPI.entities.user_group import UserGroup


class UserGroupDaoAbs(ABC):

    @abstractmethod
    # A method to display a list of users in a particular group
    def get_all_users_in_a_group(self) -> list[UserGroup]:
        pass

    @abstractmethod
    # method to handle when the user decides to leave a group or any group
    def leave_group(self,user_id:int, group_id:int):
        pass