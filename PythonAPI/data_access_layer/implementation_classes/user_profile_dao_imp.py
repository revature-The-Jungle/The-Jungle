from custom_exceptions.user_image_not_found import UserImageNotFound
from custom_exceptions.user_not_found import UserNotFound
from data_access_layer.abstract_classes.user_profile_dao import UserProfileDAO
from entities.user import User
from util.database_connection import connection


class UserProfileDAOImp(UserProfileDAO):

    def get_user_profile(self, user_id: int) -> User:
        pass

    def update_user_profile(self, user: User) -> User:
        """ a method used to update information for the profile besides the image"""

        sql = f"update user_table set user_about = %s, user_birth_date = %s where user_id = %s"
        cursor = connection.cursor()
        cursor.execute(sql, user.user_about, user.user_birth_date, user.user_id)
        connection.commit()
        return user

    def get_user_image(self, user_id: int) -> str:
        """a method to get a user image from the database"""  # need to create a custom exception and database checker

        # Check to see if the post id is in the database, raise an error otherwise.
        sql = f"select user_id from user_picture_table where user_id = {user_id};"
        cursor = connection.cursor()
        cursor.execute(sql)
        if not cursor.fetchone():
            raise UserImageNotFound('The user image could not be found.')

        sql = f"select picture from user_picture_table where user_id = {user_id};"
        cursor.execute(sql)
        image = cursor.fetchone()[0]
        image_decoded = image.decode('utf-8')
        return image_decoded

    def update_user_image(self, user_id: int, image: str) -> str:
        """a method to place a user image into the database"""

        # Check to see if the user id is in the database, raise an error otherwise.
        sql = f"select user_id from user_table where user_id = {user_id};"
        cursor = connection.cursor()
        cursor.execute(sql)
        if not cursor.fetchone():
            raise UserNotFound('The user could not be found.')

        # delete any existing image from the database and place the image in the database
        sql = f"DELETE FROM user_picture_table where user_id = {user_id}; " \
              f"INSERT INTO user_picture_table VALUES (default, {user_id}, '{image}');"
        cursor.execute(sql)
        connection.commit()

        # get the new image and send it back up to the service layer
        sql = f"select picture from user_picture_table where user_id = {user_id};"
        cursor.execute(sql)
        image = cursor.fetchone()[0]
        image_decoded = image.decode('utf-8')
        return image_decoded

    def update_user_image_format(self, user_id: int, image_data: str) -> User:
        pass

    def update_password(self, user_id: int, password: str) -> User:
        """Stretch"""
        pass
