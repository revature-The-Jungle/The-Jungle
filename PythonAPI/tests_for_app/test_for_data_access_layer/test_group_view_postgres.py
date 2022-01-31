from data_access_layer.implementation_classes.group_view_postgres_dao_imp import GroupViewPostgresDao

group_view_postgres_dao = GroupViewPostgresDao()


# Fix this test
def test_view_group_by_id():
    groups = group_view_postgres_dao.get_group_by_id(9)
    assert groups.group_name == "Tool Fans"


def test_get_all_group():
    result = group_view_postgres_dao.get_all_groups()
    assert len(result) > 2
