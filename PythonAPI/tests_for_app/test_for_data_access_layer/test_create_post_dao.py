from psycopg.errors import ForeignKeyViolation

from data_access_layer.implementation_classes.create_post_dao_imp import CreatePostDAOImp, CreatePostDAO

create_post_dao: CreatePostDAO = CreatePostDAOImp()


def test_create_post_image_success():
    """tests by using an existing"""
    assert create_post_dao.create_post_image(9000, "thisisahappytest")


# def test_create_post_image_failure():
#     """tests by using a post id that can't possibly exist"""
#     try:
#         create_post_dao.create_post_image(-5, "this is a sad test")
#         assert False
#     except ForeignKeyViolation:
#         assert True
