from email.policy import default
from sqlalchemy import event
from app import db, ma
from ..model.levels_model import Levels


class Users(db.Model):
    __tablename__ = "users"

    id = db.Column("UserID", db.Integer, primary_key=True, autoincrement=True)
    nick_name = db.Column("NickName", db.String(40), unique=True, nullable=False)
    first_name = db.Column("UserName", db.String(40), nullable=False)
    last_name = db.Column("LastName", db.String(40), nullable=False)
    email = db.Column("Email", db.String(120), unique=True, nullable=False)
    password = db.Column("PasswordHash", db.String(255), nullable=False)
    win_count = db.Column("WinCount", db.Integer, nullable=False, default=0)
    match_count = db.Column("MatchCount", db.Integer, nullable=False, default=0)
    is_active = db.Column("IsActive", db.Boolean, nullable=False, default=True)
    created_at = db.Column("CreatedAt", db.DateTime, default=db.func.now(), nullable=False)

    # Friends ilişkileri (iki yönlü)
    friends_initiated = db.relationship(
        'Friends',
        foreign_keys='Friends.user_id',
        backref='initiator',
        cascade='all, delete-orphan',
        passive_deletes=True
    )

    friends_received = db.relationship(
        'Friends',
        foreign_keys='Friends.friend_id',
        backref='receiver',
        cascade='all, delete-orphan',
        passive_deletes=True
    )

    # Levels ilişkisi
    user_levels = db.relationship(
        'Levels',
        backref='user',
        cascade='all, delete-orphan',
        passive_deletes=True
    )


class UsersSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Users
        include_fk = True




