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




# Setup flask
app: Flask = Flask(__name__)




@app.get("/")  # basic check for app running
def on():
    return "python is running"


@app.get("/postfeed")
def get_all_posts():
    try:
        post_as_post = postfeed_service.get_all_posts_service()
        posts_as_dictionary = []
        for post in post_as_post:
            dicionary_posts = post.make_dictionary()
            posts_as_dictionary.append(dicionary_posts)
        return jsonify(posts_as_dictionary)
    except ConnectionErrorr:
        return str(e), 400

@app.delete("/postfeed")
def delete_a_post():
    try:
        data = request.get_json()
        postid = data["postid"],
        boolean = postfeed_service.delete_a_post_service(postid)
        return jsonify(boolean)
    except ConnectionErrorr :
        return str(e), 400

@app.post("/postfeed")
def add_likes_to_post():
   try:
    data = request.get_json()
    postid = data["postid"],
    return jsonify(like_post_service.service_like_post(postid))
   except ConnectionErrorr :
       return str(e), 400



# delete comment information
@app.delete("/Comments")
def delete_comment():
        data = request.get_json()
        comment_id = data["commentid"],
        jsonify(comment_service.service_delete_comment(comment_id))
        return "Comment with id {} was deleted successfully".format(comment_id)







@app.get("/postfeed/<post_id>")
def get_comments_by_post_id(post_id: str):
   try:
    results = comment_service.service_get_comment_by_post_id(int(post_id))
    post_comments_as_dictionary = []
    for comments in results:
        dictionary_comment = comments.make_dictionary()
        post_comments_as_dictionary.append(dictionary_comment)
    return jsonify(post_comments_as_dictionary), 200
   except Exception:
       return "something went wrong"


@app.post("/createComment")
def create_comment():
    body = request.get_json()
    post_id = body["postId"],
    user_id = body["userId"],
    group_id = body["groupId"],
    reply_user = body["replyUser"],
    comment_text = body["commentText"],
    comment_id = comment_service.service_create_comment(post_id, user_id, comment_text, group_id, reply_user)
    return jsonify(comment_id)



app.run()
