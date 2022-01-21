from flask import Flask, request, jsonify
#from flask_cors import CORS


# Setup logging
import logging

from data_access_layer.implementation_classes.like_post_dao_imp import LikePostDaoImp


#logging.basicConfig(filename="records.log", level=logging.DEBUG,
                    #format="[%(levelname)s] - %(asctime)s - %(name)s - : %(message)s in %(pathname)s:%(lineno)d")

# Setup flask
from service_layer.implementation_classes.like_post_service_imp import LikePostServiceImp

app: Flask = Flask(__name__)
#CORS(app)

like_post_dao=LikePostDaoImp()
like_post_service=LikePostServiceImp(like_post_dao)




@app.post("/postfeed")
def add_likes_to_post():
    data = request.get_json()
    postid = data["postid"],
    return jsonify(like_post_service.service_like_post(postid))
    
    
    
    
    
    
    
    
    """post_likes = like_post_service.like_post_service(likes)
    reimbursements_as_dictionaries= []
    for reimbursement in employee_reimbursements:
        dictionary_reimbursement =reimbursement.make_reimbursement_dictionary()
        reimbursements_as_dictionaries.append(dictionary_reimbursement)
    return jsonify(reimbursements_as_dictionaries), 200"""









@app.get("/")  # basic check for app running
def on():
    return "python is running"


app.run()
