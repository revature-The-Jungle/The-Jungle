from abc import ABC, abstractmethod

from entities.user import User


class UserProfileService(ABC):

    @abstractmethod
    def service_get_user_profile_service(self, user_id: int) -> User:
        pass

    @abstractmethod
    def update_user_profile_service(self, user: User) -> User:
        pass

    @abstractmethod
    def get_user_image_service(self, user_id: int) -> str:
        pass

    @abstractmethod
    def update_user_image_service(self, user_id: int, image: str) -> str:
        pass

    @abstractmethod
    def update_user_image_format_service(self, user_id: int, image_data: str) -> User:
        pass

    @abstractmethod
    def update_password_service(self, user_id: int, password: str) -> User:
        """Stretch"""
        pass
