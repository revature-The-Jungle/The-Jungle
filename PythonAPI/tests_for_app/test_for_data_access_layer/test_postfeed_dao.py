from data_access_layer.implementation_classes.postfeed_dao import PostFeedDaoImp

PostfeedDao = PostFeedDaoImp()


def test_get_all_posts_1():
    list = PostfeedDao.get_all_posts()
    assert len(list)>0


def test_get_all_posts_2():
    list = PostfeedDao.get_all_posts()
    assert len(list)<10000


def test_delete_a_post():
    boolean = PostfeedDao.delete_a_post(1)
    assert boolean == True


