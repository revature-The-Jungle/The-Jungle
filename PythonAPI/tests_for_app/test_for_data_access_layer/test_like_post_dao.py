from data_access_layer.implementation_classes.like_post_dao import LikePostDaoImp

like_post_dao = LikePostDaoImp()


def test_like_post_success():
    assert like_post_dao.like_post(9002)
