from flask import request, jsonify
from werkzeug.exceptions import NotFound
from ..model.friends_model import  Friends,FriendsSchema
from app import db


class FriendsService:
    @staticmethod
    def send_friend_request(data):
        users_id = data.get('users_id')
        friend_id = data.get('friend_id')

        # Zaten var mı kontrolü
        existing = Friends.query.filter(
            (db.func.lower(Friends.user_id ) == users_id) &
            (db.func.lower(Friends.friend_id) == friend_id)
        ).first()

        if existing:
            return {'error': 'Zaten bir arkadaşlık kaydı var.'}

        new_request = Friends(user_id=users_id, friend_id=friend_id, status=False)
        db.session.add(new_request)
        db.session.commit()

        return {'message': 'Arkadaşlık isteği gönderildi.', 'friendship_id': new_request.id}

    @staticmethod
    def update_is_status(data):
        friends_query = Friends.query.filter_by(
            user_id = data.get('users_id'),
            friend_id = data.get('friend_id')
        ).first()
        if not friends_query:
            return {"error": f"friendship request not found"}
        new_status = data.get('status')
        friends_query.status = new_status
        db.session.commit()

        return {"message": f"new friendship status: {new_status}"}

    @staticmethod
    def get_by_user_id(data):
        friends_query = Friends.query.filter_by(
            user_id=data.get('users_id'),
            status=data.get('status')
        ).all()
        schema = FriendsSchema(many =True)
        friends = schema.dump(friends_query)
        if not friends:
            raise NotFound(f"Users with id {data.get('users_id')} not found")
        return friends

    @staticmethod
    def delete_by_id( id ):
        friendship = Friends.query.filter_by(id=id).first()
        if not friendship:
            return {"error": f"User not found by id: {id}"}

        try:
            db.session.delete(friendship)
            db.session.commit()
            return {"message": "friendship deleted successfully"}
        except Exception as e:
            db.session.rollback()
            return {"error": f"Deletion failed: {str(e)}"}
