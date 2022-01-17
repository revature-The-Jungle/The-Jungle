class User:
    def __init__(self,
                 user_id: int = 0,  # primary key
                 first_name: str = "Null",
                 last_name: str = "Null",
                 username: str = "Null",  # must be unique
                 password: str = "Null",
                 user_about: str = "Null",
                 user_birth_date: str = "Null",
                 user_image_data: str = "Null",
                 user_groups: list[str] = None,
                 user_friends: list[str] = None
                 ):

        self.user_id = user_id
        self.first_name = first_name
        self.last_name = last_name
        self.username = username
        self.password = password
        self.user_about = user_about
        self.user_birth_date = user_birth_date
        self.user_image_data = user_image_data
        self.user_groups = user_groups
        self.user_friends = user_friends

    def make_dictionary(self):
        dictionary = {
            "user_id": self.user_id,
            "first_name": self.first_name,
            "last_name": self.last_name,
            "username": self.username,
            "password": self.password,
            "user_about": self.user_about,
            "user_birth_date": self.user_birth_date,
            "user_image_data": self.user_image_data,
            "user_groups": self.user_groups,
            "user_friends": self.user_friends
            }
        return dictionary
