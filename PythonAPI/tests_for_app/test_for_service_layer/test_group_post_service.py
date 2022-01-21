from PythonAPI.data_access_layer.implementation_classes.group_post_dao import GroupPostDAO
from PythonAPI.service_layer.implementation_classes.group_post_service import GroupPostService

post_dao = GroupPostDAO()
post_service = GroupPostService(post_dao)

# ------------------------------ TEST CREATE POST ------------------------------
# TODO: Finish the DAO Layer first
