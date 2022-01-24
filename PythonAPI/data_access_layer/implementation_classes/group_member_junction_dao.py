import traceback
from typing import List

from PythonAPI.data_access_layer.abstract_classes.group_member_junction_abs import GroupMemberJunctionAbs
from PythonAPI.entities import group_member_junction
from PythonAPI.entities.group_member_junction import GroupMemberJunction
from PythonAPI.util.database_connection import connection
from custom_exceptions.group_member_junction_exceptions import *


class GroupMemberJunctionDao(GroupMemberJunctionAbs):
    # grabs all users first name last name user id and group id and puts it in a list
    def get_all_users_in_a_group(self) -> list[GroupMemberJunction] | str:
        try:
            sql = "select first_name, last_name, user_table.user_id, group_member_junction_table.group_id from " \
                  "user_table inner join group_member_junction_table on group_member_junction_table.user_id = " \
                  "user_table.user_id order by user_table.user_id"
            cursor = connection.cursor()
            cursor.execute(sql)
            group_record = cursor.fetchall()
            group_list = []
            for member in group_record:
                group_list.append(GroupMemberJunction(*member))
            return group_list
        except AssertionError:
            raise AssertionError("This is not allowed")
        except TypeError:
            raise TypeError("you have put the incorrect amount of arguments")

    # deletes user from group_member_junction_table
    def leave_group(self, user_id: int, group_id: int):
        check_list = self.get_all_users_in_a_group()
        for checks in check_list:
            if checks.group_id == group_id:
                if checks.user_id == user_id:
                    try:
                        sql = "delete from group_member_junction_table where user_id = %s and group_id = %s"
                        cursor = connection.cursor()
                        cursor.execute(sql, [user_id, group_id])
                        connection.commit()
                        return True
                    except TypeError:
                        raise TypeError("too many arguments")
        raise WrongId("Incorrect ID")