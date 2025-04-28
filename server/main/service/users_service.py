from sqlalchemy import event
from werkzeug.exceptions import NotFound

from ..model.levels_model import Levels
from ..model.users_model import  Users, UsersSchema
from app import db
from passlib.hash import argon2

class UsersService:
    @staticmethod
    def register(data):
        password = data.get('password')
        if len(password) < 7:
            return {"error": "Password must be least 7 characters"}
        hashed_password = argon2.hash(password)

        users = Users(
            nick_name = data.get('nickName'),
            first_name = data.get('firstName'),
            last_name = data.get('lastName'),
            email = data.get('email'),
            password = hashed_password
        )
        db.session.add(users)
        db.session.flush()

        # Burada Levels oluşturma işlemi yapılır
        new_level = Levels(
            users_id=users.id,
            nick_name=users.nick_name,
            level=0
        )
        db.session.add(new_level)
        db.session.commit()

        return {"message": "Register successful"}



    @staticmethod
    def login(data):
        users_schema = UsersSchema()
        email = data.get('email')
        password = data.get('password')
        user = Users.query.filter_by(email=email).first()

        if not user:
            return {"error": "User not found by email"}

        if argon2.verify(password, user.password):
            return users_schema.dump(user)
        else:
            return {"error": "Invalid email or password"}

    @staticmethod
    def update_is_active(data):
        selection = Users.query.filter_by(
            id=data.get('UserID'),
            nick_name = data.get('NickName')
        ).first()
        if not selection:
            return {"error": f"Selection not found"}
        new_is_active = data.get('IsActive')
        selection.is_active = new_is_active
        db.session.commit()

        return {"message": f"Course approved has been set. New value: {new_is_active}"}

    @staticmethod
    def get_by_id(users_id):
        users_query = Users.query.filter_by(id=users_id).first()
        schema = UsersSchema()
        users = schema.dump(users_query)
        if not users:
            raise NotFound(f"Users with id {users_id} not found")
        return users

    @staticmethod
    def get_by_nick_name(nick_name):
        users_query = Users.query.filter_by(nick_name=nick_name).first()
        schema = UsersSchema()
        users = schema.dump(users_query)
        if not users:
            raise NotFound(f"Users with nick_name {nick_name} not found")
        return users