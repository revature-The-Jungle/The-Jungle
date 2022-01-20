from custom_exceptions.post_not_found import PostNotFound
from data_access_layer.implementation_classes.create_post_dao_imp import CreatePostDAOImp, CreatePostDAO

create_post_dao: CreatePostDAO = CreatePostDAOImp()


def test_create_post_image_success():
    """tests by using an existing"""
    assert create_post_dao.create_post_image(9000, "thisisahappytest")


def test_create_post_image_failure_no_post():
    """tests by using a post id that can't possibly exist"""
    try:
        create_post_dao.create_post_image(-5, "this is a sad test")
        assert False
    except PostNotFound as e:
        assert str(e) == 'The post could not be found.'
