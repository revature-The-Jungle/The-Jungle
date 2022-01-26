from data_access_layer.abstract_classes.group_dao import GroupDAO
from entities.group import Group
from util.database_connection import connection
from custom_exceptions.group_member_junction_exceptions import WrongType
from custom_exceptions.input_too_long import InputTooLong
from custom_exceptions.group_id_nonexistent import GroupIdNonExistent


class GroupDAOImp(GroupDAO):
    def get_creator(self, group_id: int):
        if not isinstance(group_id,int):
            raise WrongType("please enter a number")
        else:
            try:
                sql = "select username  from user_table inner join group_table on " \
                      "group_table.user_id = user_table.user_id where group_id = %s"
                cursor = connection.cursor()
                cursor.execute(sql, [group_id])
                creator_record = cursor.fetchone()[0]
                return creator_record
            except TypeError:
                raise TypeError("This Id does not exist")

    def create_group(self, group: Group):
        try:
            sql = 'insert into group_table values(default, %s, %s, %s, %s) returning group_id'
            cursor = connection.cursor()
            cursor.execute(sql, (group.user_id, group.group_name, group.group_about, group.image_format))
            connection.commit()
            group_id = cursor.fetchone()[0]
            group.group_id = group_id
            return group
        except Exception:
            raise InputTooLong("You have exceeded the maximum length!")

    def join_group(self, group_id: int, user_id: int):
        try:
            sql = "insert into group_member_junction_table values(%s, %s) returning group_id, user_id"
            cursor = connection.cursor()
            cursor.execute(sql, (group_id, user_id))
            connection.commit()
            group_joined = cursor.fetchone()
            return group_joined
        except Exception:
            raise GroupIdNonExistent(UserIdNonExistent)
