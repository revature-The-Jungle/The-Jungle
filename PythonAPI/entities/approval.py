class Approval:

    def __init__(self, approval_id: int, user_id: int, group_id: int, status):
        self.status = status
        self.group_id = group_id
        self.user_id = user_id
        self.approval_id = approval_id

    def make_dictionary(self):
        dictionary = {
            "group_id": self.group_id,
            "user_id": self.user_id,
            "approval_id": self.approval_id,
            "status": self.status
        }
        return dictionary
