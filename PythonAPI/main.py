from flask import Flask, request, jsonify
from flask_cors import CORS

# Setup logging
import logging
from custom_exceptions.group_exceptions import NullValues, InputTooShort, InputTooLong, GroupNameTaken
from data_access_layer.implementation_classes.group_dao import GroupDAOImp
from data_access_layer.implementation_classes.group_view_postgres_dao import GroupViewPostgresDao
from entities.group import Group
from service_layer.implementation.group_service_imp.group_postgres_service import GroupPostgresService
from service_layer.implementation_classes.group_service import GroupPostgreService

logging.basicConfig(filename="records.log", level=logging.DEBUG,
                    format="[%(levelname)s] - %(asctime)s - %(name)s - : %(message)s in %(pathname)s:%(lineno)d")

# Setup flask
app: Flask = Flask(__name__)
CORS(app)

group_view_dao = GroupViewPostgresDao()
group_service = GroupPostgresService(group_view_dao)

# Create Group/Join Group
group_dao = GroupDAOImp()
group_service_2 = GroupPostgreService(group_dao, group_view_dao)


@app.get("/")  # basic check for app running
def on():
    return "python is running"


# -----------------------------------------------------------------------------------------------------

# CREATE GROUP
@app.post("/group")
def create_group():
    try:
        group_data = request.get_json()
        new_group = Group(
            group_data["groupId"],
            int(group_data["userId"]),
            group_data["groupName"],
            group_data["groupAbout"],
            group_data["imageFormat"]
        )
        group_created: Group = group_service_2.service_create_group(new_group)
        group_dictionary = group_created.make_dictionary()
        group_json = jsonify(group_dictionary)
        return group_json, 201
    except NullValues as e:
        exception_dictionary = {"message": str(e)}
        return jsonify(exception_dictionary), 400
    except InputTooShort as e:
        exception_dictionary = {"message": str(e)}
        return jsonify(exception_dictionary), 400
    except InputTooLong as e:
        exception_dictionary = {"message": str(e)}
        return jsonify(exception_dictionary), 400
    except GroupNameTaken as e:
        exception_dictionary = {"message": str(e)}
        return jsonify(exception_dictionary), 400


# JOIN GROUP
@app.post("/group/join/<group_id>/<user_id>")
def join_group(group_id: str, user_id: str):
    group_joined = group_service_2.service_join_group(int(group_id), int(user_id))
    group_joined_dictionary = {
        "groupId": group_joined[0],
        "userId": group_joined[1]
    }
    return jsonify(group_joined_dictionary), 200


# -----------------------------------------------------------------------------------------------------


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


app.run()
