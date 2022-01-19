class GroupMemberJunction:

    def __init__(self, user_id: int, group_id: int):
        self.group_id = group_id
        self.user_id = user_id

    def make_dictionary(self):
        dictionary = {
            "group_id": self.group_id,
            "user_id": self.user_id,
        }
        return dictionary
