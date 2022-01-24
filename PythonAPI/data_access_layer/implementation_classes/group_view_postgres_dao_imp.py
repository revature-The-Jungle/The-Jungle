from typing import List
from data_access_layer.abstract_classes.group_view_dao import GroupViewDao
from entities.group import Group
from util.database_connection import connection


class GroupViewPostgresDao(GroupViewDao):
    def get_group_by_id(self, group_id) -> [Group]:
        sql = "select * from group_table where group_id = %s"
        cursor = connection.cursor()
        cursor.execute(sql, [group_id])
        group_records = cursor.fetchall()
        groups = []
        for group in group_records:
            groups.append(Group(*group))
        return groups

    def get_all_groups(self) -> List[Group]:
        sql = "select * from group_table"
        cursor = connection.cursor()
        cursor.execute(sql)
        group_records = cursor.fetchall()
        group_list = []
        for group in group_records:
            group_list.append(Group(*group))
        return group_list
