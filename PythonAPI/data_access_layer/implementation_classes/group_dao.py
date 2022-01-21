from PythonAPI.data_access_layer.abstract_classes.group_dao_abs import GroupDAO
from PythonAPI.entities.group import Group
from PythonAPI.util.database_connection import connection
from custom_exceptions.GroupIdNonExistent import GroupIdNonExistent
from custom_exceptions.InputTooLong import InputTooLong


class GroupDAOImp(GroupDAO):
    def create_group(self, group: Group):
        try:
            sql = "insert into group_table values(default, %s, %s, %s, %s) returning group_id"
            cursor = connection.cursor()
            cursor.execute(sql, (group.user_id, group.group_name, group.group_about, group.image_format))
            connection.commit()
            group_id = cursor.fetchone()[0]
            group.group_id = group_id
            return group
        except Exception:
            raise InputTooLong

    def join_group(self, group_id: int, user_id: int):
        try:
            sql = "insert into group_member_junction_table values(%s, %s) returning group_id, user_id"
            cursor = connection.cursor()
            cursor.execute(sql, (group_id, user_id))
            connection.commit()
            group_joined = cursor.fetchone()
            return group_joined
        except Exception:
            raise GroupIdNonExistent
