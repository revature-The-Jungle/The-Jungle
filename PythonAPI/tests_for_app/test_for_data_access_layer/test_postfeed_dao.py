from data_access_layer.implementation_classes.postfeed_dao_imp import PostfeedDaoimpl

PostfeedDao = PostfeedDaoimpl()


def test_get_all_posts():
    list = PostfeedDao.get_all_posts()
    assert len(list)>0


def test_delete_a_post():
    boolean = PostfeedDao.delete_a_post(1)
    assert boolean == True