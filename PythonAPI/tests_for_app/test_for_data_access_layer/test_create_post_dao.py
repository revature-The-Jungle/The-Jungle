from PythonAPI.data_access_layer.implementation_classes.create_post_dao import CreatePostDAOImp
from PythonAPI.entities.post import Post

post_dao = CreatePostDAOImp()


# Test create post successful
def test_create_post_success():
    post_to_be_created: Post = Post(9000, 9000, 9000, "test create post", "", 0, "")
    created_post = post_dao.create_post(post_to_be_created)
    assert created_post.post_id != 0
