from PythonAPI.data_access_layer.implementation_classes.group_dao import GroupDAOImp
from PythonAPI.entities.group import Group

group_dao = GroupDAOImp()

group_happy_1 = Group(0, 9000, "Smashing Pumpkins", "This group is for all Smashing Pumpkins fans!", "image format")

group_happy_2 = Group("a", 9000, "ZZe Top", "This group is for all ZZ Top fans!", "image format")

i = 0
while i <= 501:
    group_about = "a" * i
    i += 1
group_sad_1 = Group(0, 9000, "Z Top", group_about, "image format")


# --------------------------------------Create Group HAPPY Path------------------------------------
def test_create_group_happy_1():
    created_group = group_dao.create_group(group_happy_1)
    assert created_group.group_id != 0


def test_create_group_happy_2():
    created_group = group_dao.create_group(group_happy_2)
    assert created_group.group_id != 0


# --------------------------------------Create Group SAD Path---------------------------------------
# About Group Input Too Long
def test_create_group_sad_1():
    try:
        created_group = group_dao.create_group(group_sad_1)
        assert created_group.group_about < 500
    except Exception as e:
        print(repr(e))


# --------------------------------------Join Group HAPPY Path-----------------------------------------
def test_join_group_happy_1():
    group_joined = group_dao.join_group(14, 9000)
    assert group_joined == (14, 9000)


def test_join_group_happy_2():
    group_joined = group_dao.join_group(16, 10)
    assert group_joined == (16, 10)


# ---------------------------------------Join Group SAD Path-------------------------------------------
def test_join_group_sad_1():
    try:
        group_joined = group_dao.join_group(4323, 10)
        assert group_joined == (16, 10)
    except Exception as e:
        print(repr(e))

