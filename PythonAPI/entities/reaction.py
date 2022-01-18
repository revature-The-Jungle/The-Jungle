class Reaction:
    def __init__(self,
                 post_id: int = None,
                 likes: int = None,
                 dislikes: int = None,
                 ):

        self.likes = likes
        self.dislikes = dislikes

    def make_dictionary(self):
        dictionary = {
            "likes": self.likes,
            "dislikes": self.dislikes,
            }
        return dictionary
