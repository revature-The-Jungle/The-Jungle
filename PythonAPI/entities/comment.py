class Comment:
    def __init__(self,
                 comment_id: int = None,  # primary key
                 post_id: int = None,  # foreign key
                 user_id: int = None,  # foreign key
                 username: str = None,
                 reply_username: str = None,
                 comment_text: str = None,
                 date_time_of_creation: str = None,
                 viewed: bool = False
                 ):

        self.comment_id = comment_id
        self.post_id = post_id
        self.user_id = user_id
        self.username = username
        self.reply_username = reply_username
        self.comment_text = comment_text
        self.date_time_of_creation = date_time_of_creation
        self.viewed = viewed

    def make_dictionary(self):
        dictionary = {
            "comment_id": self.comment_id,
            "post_id": self.post_id,
            "user_id": self.user_id,
            "username": self.username,
            "reply_username": self.reply_username,
            "comment_text": self.comment_text,
            "date_time_of_creation": self.date_time_of_creation,
            "viewed": self.viewed
            }
        return dictionary
