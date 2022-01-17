class Post:
    def __init__(self,
                 post_id: int = 0,  # primary key
                 user_id: int = 0,  # foreign key
                 username: str = "Null",
                 post_text: str = "Null",
                 youtube_url: str = "Null",
                 image_data: str = "Null",
                 likes: int = 0,
                 dislikes: int = 0,
                 date_time_of_creation: str = "Null"
                 ):

        self.post_id = post_id
        self.user_id = user_id
        self.username = username
        self.post_text = post_text
        self.youtube_url = youtube_url
        self.image_data = image_data
        self.likes = likes
        self.dislikes = dislikes
        self.date_time_of_creation = date_time_of_creation

    def make_dictionary(self):
        dictionary = {
            "post_id": self.post_id,
            "user_id": self.user_id,
            "username": self.username,
            "post_text": self.post_text,
            "youtube_url": self.youtube_url,
            "image_data": self.image_data,
            "likes": self.likes,
            "dislikes": self.dislikes,
            "date_time_of_creation": self.date_time_of_creation
            }
        return dictionary
