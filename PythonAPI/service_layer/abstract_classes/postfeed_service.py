from abc import ABC, abstractmethod


class PostfeedService(ABC):


    @abstractmethod
    def get_all_posts_service(self):
        pass

    @abstractmethod
    def get_all_posts_by_groupid_service(self, groupid: int):
        pass

    @abstractmethod
    def delete_a_post_service(self, postid :int):
        pass