class Group:
    def __init__(self,
                 group_id: int = None,  # primary key
                 user_id: int = None,  # the original user that created the group
                 group_name: str = None,  # must be unique
                 group_about: str = None,
                 group_image_data: str = None,
                 pending_members: list[str] = None
                 ):

        self.group_id = group_id
        self.user_id = user_id
        self.group_name = group_name
        self.group_about = group_about
        self.group_image_data = group_image_data
        self.pending_members = pending_members

    def make_dictionary(self):
        dictionary = {
            "group_id": self.group_id,
            "user_id": self.user_id,
            "group_name": self.group_name,
            "group_about": self.group_about,
            "group_image_data": self.group_image_data,
            "pending_members": self.pending_members
            }
        return dictionary
