from flask import Flask, request, jsonify
from data_access_layer.implementation_classes.postfeed_dao_imp import PostfeedDaoimpl
from service_layer.implementation_classes.postfeed_service_imp import PostfeedServiceImp
from custom_exceptions.connection_error import ConnectionErrorr
# Setup logging


# Setup flask
app: Flask = Flask(__name__)

postfeeddao = PostfeedDaoimpl()
postfeed_service = PostfeedServiceImp(postfeeddao)


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









app.run()
