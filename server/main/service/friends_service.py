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
            ((Friends.users_id == users_id) & (Friends.friend_id == friend_id)) |
            ((Friends.users_id == friend_id) & (Friends.friend_id == users_id))
        ).first()

        if existing:
            return {'error': 'Zaten bir arkadaşlık kaydı var.'}

        new_request = Friends(users_id=users_id, friend_id=friend_id, status=False)
        db.session.add(new_request)
        db.session.commit()

        return {'message': 'Arkadaşlık isteği gönderildi.', 'friendship_id': new_request.id}

    @staticmethod
    def update_is_status(data):
        friends_query = Friends.query.filter_by(
            users_id = data.get('users_id'),
            friend_id = data.get('friend_id')
        ).first()
        if not friends_query:
            return {"error": f"friendship request not found"}
        new_status = data.get('status')
        friends_query.status = new_status
        db.session.commit()

        return {"message": f"new friendship status: {new_status}"}

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
