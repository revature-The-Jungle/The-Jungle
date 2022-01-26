from abc import ABC, abstractmethod


class PostfeedService(ABC):


    @abstractmethod
    def get_all_posts_service(self):
        pass

    @abstractmethod
    def delete_a_post_service(self, post_id :int):
        pass