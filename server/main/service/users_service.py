from sqlalchemy import event
from werkzeug.exceptions import NotFound

from ..model.friends_model import Friends
from ..model.levels_model import Levels
from ..model.users_model import  Users, UsersSchema
from app import db
from passlib.hash import argon2

class UsersService:
    @staticmethod
    def register(data):
        if Users.query.filter_by(email=data["email"]).first():
            return {"error": "Bu e-posta adresi zaten kayıtlı başka bir e-posta deneyin."}, 409

        if Users.query.filter_by(nick_name=data['nickName']).first():
            return {"error": "Bu kullanıcı adı zaten kullanılıyor başka bir kullanıcı adı deneyin."}, 403

        password = data.get('password')
        if len(password) < 7:
            return {"error": "Şifreniz en az 7 karakter olmalı!"},401
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
            user_id=users.id,
            nick_name=users.nick_name,
            level=0
        )
        db.session.add(new_level)
        db.session.commit()

        return {"message": "Kayıt Başarılı."},201



    @staticmethod
    def login(data):
        users_schema = UsersSchema()
        email = data.get('email')
        password = data.get('password')
        user = Users.query.filter_by(email=email).first()

        if not user:
            return {"error": "User not found by email"},400

        if argon2.verify(password, user.password):
            return users_schema.dump(user),200
        else:
            return {"error": "Invalid email or password"},409

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
    def get_all():
        users_query = Users.query.all()
        schema = UsersSchema(many=True)
        users = schema.dump(users_query)
        return users

    @staticmethod
    def get_by_nick_name(nick_name):
        users_query = Users.query.filter_by(nick_name=nick_name).first()
        schema = UsersSchema()
        users = schema.dump(users_query)
        if not users:
            raise NotFound(f"Users with nick_name {nick_name} not found")
        return users

    @staticmethod
    def delete_by_user_id(user_id):
        user = Users.query.filter_by(id=user_id).first()
        if not user:
            return {"error": f"User not found by id: {user_id}"}

        try:
            db.session.delete(user)
            db.session.commit()
            return {"message": "User and all related records deleted successfully"}
        except Exception as e:
            db.session.rollback()
            return {"error": f"Deletion failed: {str(e)}"}

    @staticmethod
    def update_account(data):
        # Check required fields
        if not all(k in data for k in ['user_id','new_email','new_nick_name', 'current_password','new_password']):
            return {"error": "Missing information: user_id,new_email,new_nick_name, current_password and new_password are required"},400

        if len(data['new_password']) < 7:
            return {"error": "New password must be at least 7 characters long"},403

        user = Users.query.filter_by(id=data['user_id']).first()
        if not user:
            return {"error": "User not found"},404

        # Verify current password
        if not argon2.verify(data['current_password'], user.password):
            return {"error": "Current password is incorrect"},401

        # Hash and save the new password
        user.password = argon2.hash(data['new_password'])
        user.email = data['new_email']
        user.nick_name = data['new_nick_name']
        level_record = Levels.query.filter_by(user_id=data['user_id']).first()
        if level_record:
            level_record.nick_name = data['new_nick_name']
        db.session.commit()

        return {"message": "Data s changed successfully"},200

