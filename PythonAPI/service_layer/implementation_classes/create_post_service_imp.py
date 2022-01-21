from entities.post import Post
from service_layer.abstract_classes.create_post_service import CreatePostService
from data_access_layer.implementation_classes.create_post_dao_imp import CreatePostDAOImp


class CreatePostServiceImp(CreatePostService):

    def __init__(self, create_post_dao):
        self.create_post_dao: CreatePostDAOImp = create_post_dao

    def create_post_service(self, post: Post) -> Post:
        """not created yet"""
        pass

    def create_post_image_service(self, post_id: int, image: str) -> bool:
        """service layer image checks"""
        return self.create_post_dao.create_post_image(post_id, image)
