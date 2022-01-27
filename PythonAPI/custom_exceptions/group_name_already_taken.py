<<<<<<< HEAD
<<<<<<< HEAD:PythonAPI/custom_exceptions/group_name_already_taken.py
class GroupNameAlreadyTaken(Exception):
=======
class CommentNotFound(Exception):
>>>>>>> origin/eJennings/Python:PythonAPI/custom_exceptions/comment_not_found.py
=======
class GroupNameAlreadyTaken(Exception):
>>>>>>> origin/eJennings/Python
    def __init__(self, message):
        self.message = message
