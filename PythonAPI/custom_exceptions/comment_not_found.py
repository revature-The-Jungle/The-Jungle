# class GroupNotFound(Exception):

class CommentNotFound(Exception):

    def __init__(self, message):
        self.message = message
