from custom_exceptions.group_exceptions import NullValues, InputTooLong, InputTooShort, GroupNameTaken
from data_access_layer.implementation_classes.group_dao import GroupDAOImp
from data_access_layer.implementation_classes.group_view_postgres_dao import GroupViewPostgresDao
from entities.group import Group
from service_layer.abstract_classes.group_service_abs import GroupService


class GroupPostgreService(GroupService):
    def __init__(self, group_dao: GroupDAOImp, group_view_dao: GroupViewPostgresDao):
        self.group_dao = group_dao
        self.group_view_dao = group_view_dao

    def service_create_group(self, group: Group):
        if len(group.group_name) == 0 or len(group.group_about) == 0 or len(group.image_format) == 0:
            raise NullValues("You must fill in all inputs!")
        if len(group.group_name) < 3:
            raise InputTooShort("Group name should be at least three characters long!")
        if len(group.group_name) > 40 or len(group.image_format) > 40:
            raise InputTooLong("You have exceeded the 40-character limit!")
        if len(group.group_about) > 500:
            raise InputTooLong("You have exceeded the 500-character limit!")
        all_groups = self.group_view_dao.get_all_groups()
        for current_group in all_groups:
            if current_group.group_name == group.group_name:
                raise GroupNameTaken("The group name you entered is already taken! Please try another group name.")
        return self.group_dao.create_group(group)

    def service_join_group(self, group_id: int, user_id: int):
        self.group_dao.join_group(group_id, user_id)
