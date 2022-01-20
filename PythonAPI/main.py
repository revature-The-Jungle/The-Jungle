from flask import Flask, request, jsonify
from flask_cors import CORS

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
def post_a_post_image(post_id):
    """Needs a lot more work. Proof of concept regarding creating images in the database."""
    image = request.data
    image_decoded = image.decode('utf-8')
    create_post_service.create_post_image_service(post_id, image_decoded)
    return "success"


app.run()
