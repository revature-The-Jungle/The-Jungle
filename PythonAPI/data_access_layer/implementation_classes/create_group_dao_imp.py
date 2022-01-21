from PythonAPI.data_access_layer.abstract_classes.create_group import GroupDAO
from PythonAPI.util.database_connection import connection


class GroupDAOImp(GroupDAO):
    def create_group(self, user_id: int, group_name: str, group_about: str, image_format: str):
        sql = "insert into group_table values(default, %s, %s, %s, %s)"
        cursor = connection.cursor()

    def join_group_by_group_id(self, group_id: int):
        pass
