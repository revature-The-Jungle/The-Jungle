
from data_access_layer.implementation_classes.like_post_dao_imp import LikePostDaoImp

#Happy Path Testing
def test_like_post_success():
    assert like_post_dao.like_post(1)

#Happy Path Testing
def test_like_post_success():
    assert like_post_dao.like_post(2)



like_post_dao=LikePostDaoImp()