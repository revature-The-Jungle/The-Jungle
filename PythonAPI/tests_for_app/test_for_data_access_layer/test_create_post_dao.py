from data_access_layer.implementation_classes.create_post_dao_imp import CreatePostDAOImp

create_post_dao = CreatePostDAOImp()


def test_create_post_image_success():
    """a method to place an image into the database"""
    assert create_post_dao.create_post_image(10_000, "this is a test")
