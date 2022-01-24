from data_access_layer.implementation_classes.group_view_dao_imp import GroupViewDaoImp
from entities.group import Group

group_view_dao_imp = GroupViewDaoImp()
group = Group(1, 12, "Comic Club", "We love superheros", "placeholder")


# testing to check that we are only working if group_id are the same
def test_get_group_success():
    returned_group: Group = group_view_dao_imp.get_group_by_id(1)
    assert returned_group.group_id == 1


# testing to verifies that the name of group matches it's group id
def test_info_in_group_valid():
    returned_group: Group = group_view_dao_imp.get_group_by_id(1)
    assert returned_group.group_name == "Comic Club"


# sad path for group_id that are 0
def test_unknown_group_id():
    returned_group: Group = group_view_dao_imp.get_group_by_id(0)
    if returned_group == 0:
        assert False
    else:
        assert True


# sad path for group_id that are negative
def test_negative_group_id():
    returned_group: Group = group_view_dao_imp.get_group_by_id(-1)
    if returned_group == -1:
        assert False
    else:
        assert True


# testing to success of getting all groups
def test_get_all_group_success():
    group_list = group_view_dao_imp.get_all_groups()
    assert len(group_list) >= 2

# need 1 sad path for this function maybe

