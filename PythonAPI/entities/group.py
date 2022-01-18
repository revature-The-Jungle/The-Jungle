class Group:
    def __init__(self,
                 group_id: int = None,  # primary key
                 user_id: int = None,  # the original user that created the group
                 group_name: str = None,  # must be unique
                 group_about: str = None,
                 group_image_data: str = None,
                 group_members: list[str] = None
                 ):

        self.group_id = group_id
        self.user_id = user_id
        self.group_name = group_name
        self.group_about = group_about
        self.group_image_data = group_image_data
        self.group_members = group_members

    def make_dictionary(self):
        dictionary = {
            "group_id": self.group_id,
            "user_id": self.user_id,
            "group_name": self.group_name,
            "group_about": self.group_about,
            "group_image_data": self.group_image_data,
            "group_members": self.group_members
            }
        return dictionary
