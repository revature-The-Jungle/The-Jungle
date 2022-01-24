from flask import Flask, request, jsonify
from data_access_layer.implementation_classes.postfeed_dao_imp import PostfeedDaoimpl
from service_layer.implementation_classes.postfeed_service_imp import PostfeedServiceImp
from custom_exceptions.connection_error import ConnectionErrorr
from data_access_layer.implementation_classes.like_post_dao_imp import LikePostDaoImp
from service_layer.implementation_classes.like_post_service_imp import LikePostServiceImp

from data_access_layer.implementation_classes.comment_dao_imp import CommentDAOImp
from entities.comment import Comment
from service_layer.implementation_classes.comment_service_imp import CommentServiceImp
postfeeddao = PostfeedDaoimpl()
postfeed_service = PostfeedServiceImp(postfeeddao)
like_post_dao=LikePostDaoImp()
like_post_service=LikePostServiceImp(like_post_dao)
comment_dao = CommentDAOImp()
comment_service = CommentServiceImp(comment_dao)


post_id = 1
user_id =1
comment_text = "gffg"
group_id = 1
reply_user = 1

comment_to_return = comment_service.service_create_comment(post_id, user_id, comment_text, group_id, reply_user)

print(comment_to_return)

print(postfeed_service.get_all_posts_service())