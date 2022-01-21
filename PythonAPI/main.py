from flask import Flask, request, jsonify
from flask_cors import CORS

from custom_exceptions.image_must_be_a_string import ImageMustBeAString
from custom_exceptions.post_id_must_be_an_integer import PostIdMustBeAnInteger
from custom_exceptions.post_not_found import PostNotFound
from data_access_layer.implementation_classes.create_post_dao_imp import CreatePostDAOImp
from service_layer.implementation_classes.create_post_service_imp import CreatePostServiceImp

# Setup logging
import logging

logging.basicConfig(filename="records.log", level=logging.DEBUG,
                    format="[%(levelname)s] - %(asctime)s - %(name)s - : %(message)s in %(pathname)s:%(lineno)d")

# Setup flask
app: Flask = Flask(__name__)
CORS(app)

create_post_dao = CreatePostDAOImp()
create_post_service = CreatePostServiceImp(create_post_dao)


@app.get("/")  # basic check for app running
def on():
    return "python is running"


@app.post("/post/image/<post_id>")
def create_a_post_image(post_id):
    """Method to insert an image into the database. Returns the same image back from the database."""
    try:
        image = request.data
        image_decoded = image.decode('utf-8')
        return create_post_service.create_post_image_service(post_id, image_decoded)
    except PostIdMustBeAnInteger as e:
        return str(e), 400
    except ImageMustBeAString as e:
        return str(e), 400
    except PostNotFound as e:
        return str(e), 400


app.run()
