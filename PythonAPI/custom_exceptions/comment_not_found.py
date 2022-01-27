<<<<<<< HEAD:PythonAPI/custom_exceptions/group_name_already_taken.py
class GroupNameAlreadyTaken(Exception):
=======
class CommentNotFound(Exception):
>>>>>>> origin/eJennings/Python:PythonAPI/custom_exceptions/comment_not_found.py
    def __init__(self, message):
        self.message = message
