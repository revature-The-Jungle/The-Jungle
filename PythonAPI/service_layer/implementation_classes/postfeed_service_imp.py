from data_access_layer.implementation_classes.postfeed_dao_imp import PostfeedDaoimpl
from service_layer.abstract_classes.postfeed_service import PostfeedService


class PostfeedServiceImp(PostfeedService):

    def __init__(self, postfeeddao):
        self.postfeeddao: PostfeedDaoimpl = postfeeddao

    def get_all_posts_service(self):
        return self.postfeeddao.get_all_posts()

    def delete_a_post_service(self, postid: int):
        return self.postfeeddao.delete_a_post(postid)