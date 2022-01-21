from custom_exceptions.User_Id_Not_Found import UserIdNotFoundException
from custom_exceptions.image_must_be_a_string import ImageMustBeAString
from custom_exceptions.user_id_must_be_an_integer import UserIdMustBeAnInteger
from data_access_layer.implementation_classes.user_profile_dao_imp import UserProfileDAOImp
from entities.user import User
from service_layer.abstract_classes.user_profile_service import UserProfileService


class UserProfileServiceImp(UserProfileService):
    def __init__(self, user_profile_dao):
        self.user_profile_dao: UserProfileDAOImp = user_profile_dao

    # Needs more work
    def service_get_user_profile_service(self, user_id: int) -> User:
        try:
            if user_id == user_id:
                return self.user_profile_dao.get_user_profile(user_id)
        except UserIdNotFoundException:
            raise UserIdNotFoundException("User Id Does Not Exist")
            # print("raise UserIdNotFoundException")

        # user_list = self.user_profile_dao.get_user_profile(10000)
        # for existing_user in user_list:
        #     if existing_user.user_id == user_id:
        #         return self.user_profile_dao.get_user_profile(user_id)
        # raise UserIdNotFoundException("User Id Does Not Exist")

    def update_user_profile_service(self, user: User) -> User:
        pass

    def get_user_image_service(self, user_id: int) -> str:
        # Check to make sure the user_id is an integer
        if not str(user_id).isnumeric():
            raise UserIdMustBeAnInteger("The user id must be an integer.")

        return self.user_profile_dao.get_user_image(user_id)

    def update_user_image_service(self, user_id: int, image: str) -> str:
        # Check to make sure the user_id is an integer
        if not str(user_id).isnumeric():
            raise UserIdMustBeAnInteger("The user id must be an integer.")

        # Check to make sure that the image is a string
        if not type(image) == str or not image:
            raise ImageMustBeAString("The image must be a string format.")

        return self.user_profile_dao.update_user_image(user_id, image)

    def update_user_image_format_service(self, user_id: int, image_data: str) -> User:
        pass

    def update_password_service(self, user_id: int, password: str) -> User:
        pass
