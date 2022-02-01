from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from custom_exceptions.user_id_must_be_an_integer import UserIdMustBeAnInteger
from data_access_layer.implementation_classes.postfeed_dao import PostFeedDaoImp
from service_layer.abstract_classes.postfeed_service_abs import PostfeedService


class PostFeedServiceImp(PostfeedService):

    def __init__(self, post_feed_dao):
        self.post_feed_dao: PostFeedDaoImp = post_feed_dao

    def get_all_posts_service(self):
        return self.post_feed_dao.get_all_posts()

    def delete_a_post_service(self, post_id: int):
        # Check to make sure the post_id is an integer
        if not str(post_id).isnumeric():
            raise PostIdMustBeAnInteger('The post id must be an integer.')

        return self.post_feed_dao.delete_a_post(post_id)

    def get_all_posts_by_user_id_service(self, user_id: int):
        # Check to make sure the user_id is an integer
        if not str(user_id).isnumeric():
            raise UserIdMustBeAnInteger('The user id must be an integer.')

        return self.post_feed_dao.get_all_posts_with_user_id(user_id)
