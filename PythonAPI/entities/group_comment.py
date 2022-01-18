class GroupComment:
    def __init__(self,
                 comment_id: int = 0,  # primary key
                 post_id: int = 0,  # foreign key
                 user_id: int = 0,  # foreign key
                 group_id: int = 0,  # foreign key
                 username: str = "Null",
                 reply_username: str = "Null",
                 comment_text: str = "Null",
                 date_time_of_creation: str = "Null",
                 viewed: bool = False
                 ):

        self.comment_id = comment_id
        self.post_id = post_id
        self.user_id = user_id
        self.group_id = group_id
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
            "group_id": self.group_id,
            "username": self.username,
            "reply_username": self.reply_username,
            "comment_text": self.comment_text,
            "date_time_of_creation": self.date_time_of_creation,
            "viewed": self.viewed
        }
        return dictionary
