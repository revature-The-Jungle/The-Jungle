<<<<<<< HEAD:PythonAPI/data_access_layer/implementation_classes/user_profile_dao.py
from custom_exceptions.follower_not_found import FollowerNotFound
from custom_exceptions.user_image_not_found import UserImageNotFound
from custom_exceptions.user_not_found import UserNotFound
from data_access_layer.abstract_classes.user_profile_dao_abs import UserProfileDAO
=======
from data_access_layer.abstract_classes.user_profile_dao import UserProfileDAO
>>>>>>> origin/mBahrami/Python/post-comments/sp2:PythonAPI/data_access_layer/implementation_classes/user_profile_dao_imp.py
from entities.user import User


class UserProfileDAOImp(UserProfileDAO):
    def get_user_profile(self, user_id: int) -> User:
<<<<<<< HEAD:PythonAPI/data_access_layer/implementation_classes/user_profile_dao.py
        """Grabs data from the user profile by user id"""
        sql = 'select * from user_table where user_id = %(user_id)s'
        cursor = connection.cursor()
        cursor.execute(sql, {"user_id": user_id})
        profile_record = cursor.fetchone()
        if profile_record:
            user = User(*profile_record)
            return user
        else:
            raise UserNotFound(user_not_found_string)

    def update_user_profile(self, user: User) -> User:
        """ A method used to update information for the profile besides the image"""

        sql = "select * from user_table where user_id = %(user_id)s"
        cursor = connection.cursor()
        cursor.execute(sql, {'user_id': user.user_id})
        if not cursor.fetchone():
            raise UserNotFound(user_not_found_string)

        sql = "update user_table set user_about = %(user_about)s, user_birth_date = %(user_birth_date)s where user_id " \
              "= %(user_id)s "
        cursor.execute(sql, {'user_about': user.user_about, 'user_birth_date': user.user_birth_date,
                             'user_id': user.user_id})

        sql = "select * from user_table where user_id = %(user_id)s"
        cursor.execute(sql, {"user_id": user.user_id})

        connection.commit()
        return user

    def get_user_image(self, user_id: int) -> str:
        """a method to get a user image from the database"""  # need to create a custom exception and database checker

        # Check to see if the post id is in the database, raise an error otherwise.
        sql = f"select user_id from user_picture_table where user_id = %(user_id)s;"
        cursor = connection.cursor()
        cursor.execute(sql, {"user_id": user_id})
        if not cursor.fetchone():
            raise UserImageNotFound('The user image could not be found.')

        sql = "select picture from user_picture_table where user_id = %(user_id)s;"
        cursor.execute(sql, {"user_id": user_id})
        image = cursor.fetchone()[0]
        image_decoded = image.decode('utf-8')
        return image_decoded

    def update_user_image(self, user_id: int, image: str) -> str:
        """a method to place a user image into the database"""

        # Check to see if the user id is in the database, raise an error otherwise.
        sql = "select * from user_table where user_id = %(user_id)s;"
        cursor = connection.cursor()
        cursor.execute(sql, {"user_id": user_id})
        if not cursor.fetchone():
            raise UserNotFound(user_not_found_string)
=======
        pass

    def get_user_image(self, user_id: int) -> str:
        pass
>>>>>>> origin/mBahrami/Python/post-comments/sp2:PythonAPI/data_access_layer/implementation_classes/user_profile_dao_imp.py

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

    def get_user_followers(self, user_id: int) -> dict[str:int]:
        """Returns a dictionary with username as key and their userId as the value of the followers of userID"""
        sql = "select * from user_table where user_id = %(user_id)s"
        cursor = connection.cursor()
        cursor.execute(sql, {'user_id': user_id})
        if not cursor.fetchone():
            raise UserNotFound(user_not_found_string)

        sql = "select user_table.username, user_follower_id from user_follow_junction_table" \
              " inner join user_table on user_follow_junction_table.user_follower_id = user_table.user_id" \
              " where user_follow_junction_table.user_id = %(user_id)s;"
        cursor = connection.cursor()
        cursor.execute(sql, {"user_id": user_id})
        connection.commit()
        follower_records = cursor.fetchall()
        follower_dict = {}
        for follower in follower_records:
            follower_dict.update({follower[0]: follower[1]})
        return follower_dict

    def get_users_following_user(self, user_id: int) -> dict[str:int]:
        """Stretch"""
        sql = "select * from user_table where user_id = %(user_id)s"
        cursor = connection.cursor()
        cursor.execute(sql, {'user_id': user_id})
        if not cursor.fetchone():
            raise UserNotFound(user_not_found_string)

        sql = "select user_table.username, user_table.user_id from user_follow_junction_table" \
              " inner join user_table on user_follow_junction_table.user_id = user_table.user_id" \
              " where user_follower_id = %(user_id)s;"
        cursor = connection.cursor()
        cursor.execute(sql, {"user_id": user_id})
        connection.commit()
        following_records = cursor.fetchall()
        following_dict = {}
        for follower in following_records:
            following_dict.update({follower[0]: follower[1]})
        return following_dict

    def follow_user(self, user_follower_id: int, user_being_followed_id: int) -> bool:

        sql = "select * from user_table where user_id = %(user_id)s"
        cursor = connection.cursor()
        cursor.execute(sql, {"user_id": user_follower_id})
        if not cursor.fetchone():
            raise UserNotFound(user_not_found_string)

        sql = "select * from user_table where user_id = %(user_id)s"
        cursor = connection.cursor()
        cursor.execute(sql, {"user_id": user_being_followed_id})
        if not cursor.fetchone():
            raise UserNotFound(user_not_found_string)

        sql = "insert into user_follow_junction_table values(%(user_follower_id)s, %(user_being_followed_id)s, false)"
        cursor = connection.cursor()
        cursor.execute(sql, {"user_follower_id": user_follower_id, "user_being_followed_id": user_being_followed_id})
        connection.commit()
        return True

    def unfollow_user(self, user_follower_id: int, user_being_followed_id: int) -> bool:
        sql = "select * from user_follow_junction_table where user_follower_id = %(user_follower_id)s" \
              " and user_id = %(user_being_followed_id)s"
        cursor = connection.cursor()
        cursor.execute(sql, {'user_follower_id': user_follower_id, "user_being_followed_id": user_being_followed_id})
        if not cursor.fetchone():
            raise FollowerNotFound("The follower was not found.")

        sql = "delete from user_follow_junction_table where user_follower_id = %(user_follower_id)s" \
              " and user_id = %(user_being_followed_id)s"
        cursor = connection.cursor()
        cursor.execute(sql, {"user_follower_id": user_follower_id, "user_being_followed_id": user_being_followed_id})
        connection.commit()
        return True

