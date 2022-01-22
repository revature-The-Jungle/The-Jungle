from data_access_layer.implementation_classes.group_post_dao_imp import GroupPostDAO
from entities.group_post import GroupPost
from service_layer.abstract_classes.group_post_service_abs import GroupPostServiceAbs


class GroupPostService(GroupPostServiceAbs):
    def __init__(self, post_dao: GroupPostDAO):
        self.post_dao = post_dao

    def service_create_post(self, post: GroupPost) -> GroupPost:
        pass

    def service_create_post_image(self, image: str) -> bool:
        pass

    def service_get_post_by_id(self, post_id: int) -> GroupPost:
        pass

    def service_get_all_posts(self) -> list[GroupPost]:
        pass

    def service_get_all_posts_by_group_id(self, group_id: int) -> list[GroupPost]:
        pass

    def service_delete_post_by_post_id(self, post_id: int) -> bool:
        pass
