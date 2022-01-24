class PostNotFound(Exception):
    def __init__(self, message):
        self.message = message


class InvalidInput(Exception):
    def __init__(self, message):
        self.message = message
