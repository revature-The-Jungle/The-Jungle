class UserGroup:

    def __init__(self, p_id: int, user_id: int, group_id: int):
        self.group_id = group_id
        self.user_id = user_id
        self.p_id = p_id

    def make_dictionary(self):
        dictionary = {
            "group_id": self.group_id,
            "user_id": self.user_id,
            "p_id": self.p_id,
        }
        return dictionary
