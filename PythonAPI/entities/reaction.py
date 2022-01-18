# Not in use
class Reaction:
    def __init__(self,
                 reaction_id: int = None,  # primary key
                 post_id: int = None,  # foreign key
                 reactions: dict[str:str] = None  # dictionary key username: reaction
                 ):

        self.reaction_id = reaction_id
        self.post_id = post_id
        self.reactions = reactions

    def make_dictionary(self):
        dictionary = {
            "reaction_id": self.reaction_id,
            "post_id": self.post_id,
            "reactions": self.reactions,
            }
        return dictionary
