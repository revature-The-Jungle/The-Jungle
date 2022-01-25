from flask import Flask, request, jsonify
from flask_cors import CORS

from data_access_layer.implementation_classes.postfeed_dao_imp import PostFeedDaoImp
from service_layer.implementation_classes.postfeed_service_imp import PostFeedServiceImp
from custom_exceptions.connection_error import ConnectionErrorr
from data_access_layer.implementation_classes.comment_dao_imp import CommentDAOImp
from service_layer.implementation_classes.comment_service_imp import CommentServiceImp
from custom_exceptions.group_member_junction_exceptions import WrongId
from custom_exceptions.image_format_must_be_a_string import ImageFormatMustBeAString
from custom_exceptions.image_must_be_a_string import ImageMustBeAString
from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from custom_exceptions.post_image_not_found import PostImageNotFound
from custom_exceptions.post_not_found import PostNotFound
from custom_exceptions.post_text_must_be_a_string import PostTextMustBeAString
from custom_exceptions.user_id_must_be_an_integer import UserIdMustBeAnInteger
from custom_exceptions.user_image_not_found import UserImageNotFound
from custom_exceptions.birth_date_is_null import BirthDateIsNull
from custom_exceptions.too_many_characters import TooManyCharacters
from custom_exceptions.user_not_found import UserNotFound
from data_access_layer.implementation_classes.create_post_dao_imp import CreatePostDAOImp
from data_access_layer.implementation_classes.user_profile_dao_imp import UserProfileDAOImp
from entities.post import Post
from entities.user import User
from service_layer.implementation_classes.create_post_service_imp import CreatePostServiceImp
from service_layer.implementation_classes.group_member_junction_service_imp import GroupMemberJunctionService
from data_access_layer.implementation_classes.group_member_junction_dao_imp import GroupMemberJunctionDao

from service_layer.implementation_classes.user_profile_service_imp import UserProfileServiceImp
from data_access_layer.implementation_classes.group_view_postgres_dao_imp import GroupViewPostgresDao
from service_layer.implementation_classes.group_postgres_service_imp import GroupPostgresService
from data_access_layer.implementation_classes.like_post_dao_imp import LikePostDaoImp
from service_layer.implementation_classes.like_post_service_imp import LikePostServiceImp

import logging

logging.basicConfig(filename="records.log", level=logging.DEBUG,
                    format="[%(levelname)s] - %(asctime)s - %(name)s - : %(message)s in %(pathname)s:%(lineno)d")

# Setup flask
app: Flask = Flask(__name__)
CORS(app)


@app.get("/")  # basic check for app running
def on():
    return "python is running"


like_post_dao = LikePostDaoImp()
like_post_service = LikePostServiceImp(like_post_dao)
create_post_dao = CreatePostDAOImp()
create_post_service = CreatePostServiceImp(create_post_dao)
user_profile_dao = UserProfileDAOImp()
user_profile_service = UserProfileServiceImp(user_profile_dao)
group_view_dao = GroupViewPostgresDao()
group_service = GroupPostgresService(group_view_dao)
post_feed_dao = PostFeedDaoImp()
post_feed_service = PostFeedServiceImp(post_feed_dao)
comment_dao = CommentDAOImp()
comment_service = CommentServiceImp(comment_dao)


@app.get("/user/<user_id>")
def get_a_user_id(user_id: int):
    try:
        user = user_profile_service.service_get_user_profile_service(int(user_id))
        user_as_dictionary = user.make_dictionary()
        return jsonify(user_as_dictionary), 200
    except UserNotFound as e:
        return str(e), 400


@app.post("/post")
def create_a_post():  # Not yet tested
    """Method to create a new post in the database."""
    post_body = request.get_json()
    new_post = Post(user_id=post_body["user_id"],
                    post_text=post_body["post_text"],
                    image_format=post_body["image_format"])
    try:
        returned_post = create_post_service.create_post_service(new_post)
        returned_post_as_json = jsonify(returned_post.make_dictionary())
        return returned_post_as_json
    except UserIdMustBeAnInteger as e:
        return str(e), 400
    except ImageFormatMustBeAString as e:
        return str(e), 400
    except PostTextMustBeAString as e:
        return str(e), 400
    except UserNotFound as e:
        return str(e), 400


@app.post("/post/image/<post_id>")
def create_a_post_image(post_id):
    """Method to insert an image into the database. Returns the same image back from the database."""
    try:
        image = request.data
        image_decoded = image.decode('utf-8')
        return create_post_service.create_post_image_service(post_id, image_decoded), 201
    except PostIdMustBeAnInteger as e:
        return str(e), 400
    except ImageMustBeAString as e:
        return str(e), 400
    except PostNotFound as e:
        return str(e), 400


@app.get("/post/image/<post_id>")
def get_the_post_image(post_id):
    """Method to grab the post image from the database by the post id."""
    try:
        return create_post_service.get_post_image_service(post_id), 200
    except PostIdMustBeAnInteger as e:
        return str(e), 400
    except PostImageNotFound as e:
        return str(e), 400


@app.get("/user/image/<user_id>")
def get_the_user_image(user_id):
    """Method to grab a user image from the database by the user id."""
    try:
        return user_profile_service.get_user_image_service(user_id), 200
    except UserIdMustBeAnInteger as e:
        return str(e), 400
    except UserImageNotFound as e:
        return str(e), 400


@app.post("/user/image/<user_id>")
def post_the_user_image(user_id):
    try:
        image = request.data
        image_decoded = image.decode('utf-8')
        return user_profile_service.update_user_image_service(user_id, image_decoded), 200
    except UserIdMustBeAnInteger as e:
        return str(e), 400
    except ImageMustBeAString as e:
        return str(e), 400
    except UserNotFound as e:
        return str(e), 400


@app.post("/user/imageFormat/<user_id>")
def post_the_user_image_format(user_id):
    try:
        image_data = request.get_json()
        returned_user = user_profile_service.update_user_image_format_service(user_id, image_data["image_format"])
        user_as_json = jsonify(returned_user.make_dictionary())
        return user_as_json, 200
    except UserIdMustBeAnInteger as e:
        return str(e), 400
    except ImageFormatMustBeAString as e:
        return str(e), 400
    except UserNotFound as e:
        return str(e), 400


@app.patch("/user/profile/update/<user_id>")
def update_profile_info(user_id):
    try:
        user_profile_data = request.get_json()
        new_user_profile = User(
            user_id,
            user_profile_data["firstName"],
            user_profile_data["lastName"],
            user_profile_data["email"],
            user_profile_data["username"],
            user_profile_data["passcode"],
            user_profile_data["userAbout"],
            user_profile_data["userBirthDate"],
            user_profile_data["userImageFormat"]
        )
        returned_user_profile = user_profile_service.update_user_profile_service(
            new_user_profile)
        user_profile_as_dictionary = returned_user_profile.make_dictionary()
        user_profile_as_json = jsonify(user_profile_as_dictionary)
        return user_profile_as_json, 200
    except UserNotFound as e:
        exception_dictionary = {"message": str(e)}
        exception_json = jsonify(exception_dictionary)
        return exception_json, 400
    except TooManyCharacters as e:
        exception_dictionary = {"message": str(e)}
        exception_json = jsonify(exception_dictionary)
        return exception_json, 400
    except BirthDateIsNull as e:
        exception_dictionary = {"message": str(e)}
        exception_json = jsonify(exception_dictionary)
        return exception_json, 400


@app.get("/group/<group_id>")
def get_group_by_id(group_id: str):
    result = group_service.service_get_group_by_id(int(group_id))
    result_as_dictionary = []
    for groups in result:
        dictionary_request = groups.make_dictionary()
        result_as_dictionary.append(dictionary_request)
    return jsonify(result_as_dictionary)


@app.get("/group")
def get_all_groups():
    groups_as_groups = group_service.service_get_all_groups()
    groups_as_dictionary = []
    for groups in groups_as_groups:
        dictionary_group = groups.make_dictionary()
        groups_as_dictionary.append(dictionary_group)
    return jsonify(groups_as_dictionary)


"""Group Junction API"""
group_mem_dao = GroupMemberJunctionDao()
group_junction_service = GroupMemberJunctionService(group_mem_dao)


@app.get("/GroupJunction/UserList")
def get_users_in_group_api():
    group_list = group_junction_service.get_all_users_in_a_group()
    group_dict = []
    for mem in group_list:
        dictionary_mem = mem.make_dictionary()
        group_dict.append(dictionary_mem)
    return jsonify(group_dict)


@app.delete("/group/leave/<user_id>/<group_id>")
def leave_group(user_id: str, group_id: str):
    try:
        group_junction_service.leave_group(int(user_id), int(group_id))
        message = "you have left the group"
        return jsonify(message)
    except TypeError as e:
        return jsonify(str(e))
    except WrongId as e:
        return jsonify(str(e))


@app.get("/postfeed")
def get_all_posts():
    try:
        post_as_post = post_feed_service.get_all_posts_service()
        posts_as_dictionary = []
        for post in post_as_post:
            dictionary_posts = post.make_dictionary()
            posts_as_dictionary.append(dictionary_posts)
        return jsonify(posts_as_dictionary)
    except ConnectionErrorr as e:
        return str(e), 400


@app.delete("/postfeed")
def delete_a_post():
    try:
        data = request.get_json()
        post_id = data["post_id"]
        boolean = post_feed_service.delete_a_post_service(post_id)
        return jsonify(boolean)
    except ConnectionErrorr as e:
        return str(e), 400


@app.post("/postfeed")
def add_likes_to_post():
    try:
        data = request.get_json()
        post_id = data["post_id"]
        return jsonify(like_post_service.service_like_post(post_id))
    except ConnectionErrorr as e:
        return str(e), 400


# delete comment information
@app.delete("/Comments")
def delete_comment():
    data = request.get_json()
    comment_id = data["commentid"]
    jsonify(comment_service.service_delete_comment(comment_id))
    return "Comment with id {} was deleted successfully".format(comment_id)


@app.get("/postfeed/<post_id>")
def get_comments_by_post_id(post_id: str):
    results = comment_service.service_get_comment_by_post_id(int(post_id))
    post_comments_as_dictionary = []
    for comments in results:
        dictionary_comment = comments.make_dictionary()
        post_comments_as_dictionary.append(dictionary_comment)
    return jsonify(post_comments_as_dictionary), 200


@app.post("/createComment")
def create_comment():
    body = request.get_json()
    post_id = body["postId"]
    user_id = body["userId"]
    group_id = body["groupId"]
    reply_user = body["replyUser"]
    comment_text = body["commentText"]
    comment_id = comment_service.service_create_comment(post_id, user_id, comment_text, group_id, reply_user)
    return jsonify(comment_id)


app.run()
