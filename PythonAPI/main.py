from flask import Flask, request, jsonify
from flask_cors import CORS

from custom_exceptions.birth_date_is_null import BirthDateIsNull
from custom_exceptions.too_many_characters import TooManyCharacters
from custom_exceptions.user_not_found import UserNotFound
from data_access_layer.implementation_classes.create_post_dao_imp import CreatePostDAOImp
from data_access_layer.implementation_classes.user_profile_dao_imp import UserProfileDAOImp
from entities.user import User
from service_layer.implementation_classes.create_post_service_imp import CreatePostServiceImp
from service_layer.implementation_classes.user_profile_service_imp import UserProfileServiceImp

# Setup logging
import logging

logging.basicConfig(filename="records.log", level=logging.DEBUG,
                    format="[%(levelname)s] - %(asctime)s - %(name)s - : %(message)s in %(pathname)s:%(lineno)d")

# Setup flask
app: Flask = Flask(__name__)
CORS(app)

create_post_dao = CreatePostDAOImp()
create_post_service = CreatePostServiceImp(create_post_dao)

user_profile_dao = UserProfileDAOImp()
user_profile_service = UserProfileServiceImp(user_profile_dao)


@app.get("/")  # basic check for app running
def on():
    return "python is running"


# @app.get("/user/<user_id>")
# def get_user_by_id(user_id: int):
#     user_as_id =

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
        return user_profile_as_json
    except UserNotFound as e:
        exception_dictionary = {"message": str(e)}
        exception_json = jsonify(exception_dictionary)
        return exception_json
    except TooManyCharacters as e:
        exception_dictionary = {"message": str(e)}
        exception_json = jsonify(exception_dictionary)
        return exception_json
    except BirthDateIsNull as e:
        exception_dictionary = {"message": str(e)}
        exception_json = jsonify(exception_dictionary)
        return exception_json


app.run()
