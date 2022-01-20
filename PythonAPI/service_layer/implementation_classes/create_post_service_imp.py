from custom_exceptions.image_must_be_a_string import ImageMustBeAString
from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from entities.post import Post
from service_layer.abstract_classes.create_post_service import CreatePostService
from data_access_layer.implementation_classes.create_post_dao_imp import CreatePostDAOImp


class CreatePostServiceImp(CreatePostService):

    def __init__(self, create_post_dao):
        self.create_post_dao: CreatePostDAOImp = create_post_dao

    def create_post_service(self, post: Post) -> Post:
        """not created yet"""
        # check to make sure that there is a user_id in the database user_table
        pass

    def create_post_image_service(self, post_id: int, image: str) -> str:
        """service layer image checks"""
        # Check to make sure that the post_id is an integer
        if not str(post_id).isnumeric():
            raise PostIdMustBeAnInteger("The post id must be an integer.")

        # Check to make sure that the image is a string
        if not type(image) == str:
            raise ImageMustBeAString("The image must be a string format.")

        return self.create_post_dao.create_post_image(post_id, image)
