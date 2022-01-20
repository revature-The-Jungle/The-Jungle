import traceback
from typing import List

from PythonAPI.data_access_layer.abstract_classes.group_member_junction_abs import GroupMemberJunctionAbs
from PythonAPI.entities import group_member_junction
from PythonAPI.entities.group_member_junction import GroupMemberJunction
from PythonAPI.util.database_connection import connection


class GroupMemberJunctionDao(GroupMemberJunctionAbs):
    def get_all_users_in_a_group(self) -> list[GroupMemberJunction] | str:
        try:
            sql = "select first_name, last_name from user_table inner join group_member_junction_table on " \
                  "group_member_junction_table.user_id = user_table.user_id order by user_table.user_id"
            cursor = connection.cursor()
            cursor.execute(sql)
            group_record = cursor.fetchall()
            group_list = []
            if group_record is True:
                for member in group_record:
                    group_list.append(GroupMemberJunction(*member))
                return group_list
            else:
                return "no list was generated"
        except TypeError as e:
            print("you have input the incorrect amount of arguments")
            print(traceback.format_exc())

    def leave_group(self, user_id: int, group_id: int):
        try:
            sql = "delete from group_member_junction_table where user_id = % and group_id"
            cursor = connection.cursor()
            cursor.execute(sql, [user_id, group_id])
            connection.commit()
            return True
        except TypeError as e:
            print("please check your arguments")
            print(traceback.format_exc())
