from flask import Flask, request, jsonify
from flask_cors import CORS


# Setup logging
import logging
logging.basicConfig(filename="records.log", level=logging.DEBUG,
                    format="[%(levelname)s] - %(asctime)s - %(name)s - : %(message)s in %(pathname)s:%(lineno)d")

# Setup flask
app: Flask = Flask(__name__)
CORS(app)


app.run()
