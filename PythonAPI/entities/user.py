class User:
    def __init__(self,
                 user_id: int = None,  # primary key
                 first_name: str = None,
                 last_name: str = None,
                 username: str = None,  # must be unique
                 password: str = None,
                 user_about: str = None,
                 user_birth_date: str = None,
                 user_image_data: str = None,
                 bookmarked_posts: list[int] = None,  # stretch
                 user_groups: list[str] = None,
                 user_events: list[str] = None,  # stretch
                 user_followed: list[str] = None,  # stretch
                 user_see_first: list[str] = None  # stretch
                 ):

        self.user_id = user_id
        self.first_name = first_name
        self.last_name = last_name
        self.username = username
        self.password = password
        self.user_about = user_about
        self.user_birth_date = user_birth_date
        self.user_image_data = user_image_data
        self.bookmarked_posts = bookmarked_posts
        self.user_groups = user_groups
        self.user_events = user_events
        self.user_followed = user_followed
        self.user_see_first = user_see_first

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
            "bookmarked_posts": self.bookmarked_posts,
            "user_groups": self.user_groups,
            "user_events": self.user_events,
            "user_followed": self.user_followed,
            "user_see_first": self.user_see_first
            }
        return dictionary