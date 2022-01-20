class GroupMemberJunction:

    def __init__(self, user_id: int, group_id: int, first_name:str, last_name:str):
        self.last_name = last_name
        self.first_name = first_name
        self.group_id = group_id
        self.user_id = user_id

    def make_dictionary(self):
        dictionary = {
            "group_id": self.group_id,
            "user_id": self.user_id,
            "first_name": self.first_name,
            "last_name": self.last_name
        }
        return dictionary
