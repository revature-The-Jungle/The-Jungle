from data_access_layer.implementation_classes.like_post_dao_imp import LikePostDaoImp
from service_layer.abstract_classes.like_post_service import LikePostService





class LikePostServiceImp(LikePostService):
    def __init__(self, like_post_dao):
        self.like_post_dao: LikePostDaoImp = like_post_dao



    def service_like_post(self, post_id):
        return self.like_post_dao.like_post(post_id)
