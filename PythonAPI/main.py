from flask import Flask, request, jsonify
from flask_cors import CORS

# Setup logging
import logging

from data_access_layer.implementation_classes.group_view_postgres_dao import GroupViewPostgresDao
from service_layer.implementation.group_service_imp.group_postgres_service import GroupPostgresService

logging.basicConfig(filename="records.log", level=logging.DEBUG,
                    format="[%(levelname)s] - %(asctime)s - %(name)s - : %(message)s in %(pathname)s:%(lineno)d")

# Setup flask
app: Flask = Flask(__name__)
CORS(app)

group_view_dao = GroupViewPostgresDao()
group_service = GroupPostgresService(group_view_dao)


@app.get("/")  # basic check for app running
def on():
    return "python is running"


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
