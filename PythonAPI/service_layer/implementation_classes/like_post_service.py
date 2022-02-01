from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from data_access_layer.implementation_classes.like_post_dao import LikePostDaoImp
from service_layer.abstract_classes.like_post_service_abs import LikePostService


class LikePostServiceImp(LikePostService):

    def __init__(self, like_post_dao):
        self.like_post_dao: LikePostDaoImp = like_post_dao

    def service_like_post(self, post_id):
        # Check to make sure the post_id is an integer
        if not str(post_id).isnumeric():
            raise PostIdMustBeAnInteger('The post id must be an integer.')

        return self.like_post_dao.like_post(post_id)

    def service_like_comment(self, comment_id: int):
        """Not in the user stories."""
        pass
