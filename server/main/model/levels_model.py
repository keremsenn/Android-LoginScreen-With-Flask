from email.policy import default

from app import db, ma

class Levels(db.Model):
    __tablename__ = "levels"

    id = db.Column("LevelsId", db.Integer, primary_key=True, autoincrement=True)
    user_id = db.Column("UsersId", db.Integer,db.ForeignKey("users.UserID"), nullable=False)
    nick_name = db.Column("NickName", db.String(40),unique=True, nullable=False)
    level = db.Column("Level", db.Integer, nullable=False, default=0)

class LevelsSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Levels
        include_fk = True