class Reaction:
    def __init__(self,
                 reaction_id: int = None,
                 post_id: int = None,
                 reactor_id: int = None,
                 reaction_status: str = None,
                 ):

        self.reaction_id = reaction_id
        self.post_id = post_id
        self.reactor_id = reactor_id
        self.reaction_status = reaction_status

    def make_dictionary(self):
        dictionary = {
            "reaction_id": self.reaction_id,
            "dislikes": self.dislikes,
            }
        return dictionary
