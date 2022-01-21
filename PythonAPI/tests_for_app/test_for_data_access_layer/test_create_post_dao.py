from custom_exceptions.post_not_found import PostNotFound
from data_access_layer.implementation_classes.create_post_dao_imp import CreatePostDAOImp, CreatePostDAO
from entities.post import Post

create_post_dao: CreatePostDAO = CreatePostDAOImp()


def test_create_post_image_success_1():
    """tests by using an existing"""
    assert create_post_dao.create_post_image(9000, "thisisahappytest")


def test_create_post_image_success_2():
    """tests by using an existing"""
    assert create_post_dao.create_post_image(9000, "thisIsAlsoAHappyTest")


def test_create_post_image_failure_no_post():
    """tests by using a post id that can't possibly exist"""
    try:
        create_post_dao.create_post_image(100000000000000000000000, "this is a sad test")
        assert False
    except PostNotFound as e:
        assert str(e) == 'The post could not be found.'


# Test create post successful
def test_create_post_success():
    post_to_be_created: Post = Post(9000, 9000, 9000, "test create post", "", 0, "")
    created_post = create_post_dao.create_post(post_to_be_created)
    assert created_post.post_id != 0
