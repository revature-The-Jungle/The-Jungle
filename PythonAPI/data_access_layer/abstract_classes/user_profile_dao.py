from abc import ABC, abstractmethod

from entities.user import User


class UserProfileDAO(ABC):

    @abstractmethod
    def get_user_profile(self, user_id: int) -> User:
        pass

    @abstractmethod
    def update_user_profile(self, user: User) -> User:
        pass

    @abstractmethod
    def get_user_image(self, user_id: int) -> str:
        pass

    @abstractmethod
    def update_user_image(self, user_id: int, image: str) -> str:
        pass

    @abstractmethod
    def update_image_format(self, user_id: int, image_data: str) -> User:
        pass

    @abstractmethod
    def update_password(self, user_id: int, password: str) -> User:
        """Stretch"""
        pass
