from data_access_layer.abstract_classes.user_profile_dao import UserProfileDAO
from entities.user import User


class UserProfileDAOImp(UserProfileDAO):
    def get_user_profile(self, user_id: int) -> User:
        pass

    def get_user_image(self, user_id: int) -> str:
        pass

    def update_user_image(self, user_id: int, image: str) -> bool:
        pass

    def update_image_format(self, user_id: int, image_data: str) -> User:
        pass

    def update_first_name(self, user_id: int, first_name: str) -> User:
        pass

    def update_last_name(self, user_id: int, last_name: str) -> User:
        pass

    def update_username(self, user_id: int, username: str) -> User:
        pass

    def update_password(self, user_id: int, password: str) -> User:
        pass

    def update_about_me(self, user_id: int, about_me: str) -> User:
        pass

    def update_birthdate(self, user_id: int, birthdate: str) -> User:
        pass
