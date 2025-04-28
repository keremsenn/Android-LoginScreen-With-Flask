from flask import Flask
from flask_migrate import Migrate
from flask_marshmallow import Marshmallow
from config import Config
from flask_sqlalchemy import SQLAlchemy



db = SQLAlchemy()
migrate = Migrate()
ma = Marshmallow()


app = Flask(__name__)

app.config.from_object(Config)

db.init_app(app)
ma.init_app(app)
migrate.init_app(app, db)
with app.app_context():
    from main.model import *

from main.api.users_api import users_bp
from main.api.levels_api import levels_bp
from main.api.friends_api import friends_bp

app.register_blueprint(users_bp, url_prefix="/users")
app.register_blueprint(levels_bp, url_prefix="/levels")
app.register_blueprint(friends_bp, url_prefix="/friends")




