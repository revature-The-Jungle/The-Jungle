class Post:
    def __init__(self,
                 post_id: int = None,  # primary key
                 user_id: int = None,  # foreign key
                 group_id: int = None,  # foreign key
                 username: str = None,
                 post_text: str = None,
                 youtube_url: str = None,
                 image_data: str = None,
                 likes: int = None,
                 dislikes: int = None,
                 date_time_of_creation: str = None
                 ):

        self.post_id = post_id
        self.user_id = user_id
        self.group_id = group_id
        self.username = username
        self.post_text = post_text
        self.youtube_url = youtube_url
        self.image_data = image_data
        self.likes = likes
        self.dislikes = dislikes
        self.date_time_of_creation = date_time_of_creation

    @property
    def make_dictionary(self):
        dictionary = {
            "post_id": self.post_id,
            "user_id": self.user_id,
            "group_id": self.group_id,
            "username": self.username,
            "post_text": self.post_text,
            "youtube_url": self.youtube_url,
            "image_data": self.image_data,
            "likes": self.likes,
            "dislikes": self.dislikes,
            "date_time_of_creation": self.date_time_of_creation
            }
        return dictionary
