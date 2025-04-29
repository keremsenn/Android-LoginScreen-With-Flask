from flask import Blueprint, jsonify, request
from ..service.friends_service import FriendsService

friends_bp = Blueprint("friends_api", __name__)

@friends_bp.route("/send_friendship",methods=['POST'])
def send_friendship():
    data = request.get_json()
    result = FriendsService.send_friend_request(data)
    if "error" in result:
        return jsonify(result), 404
    return jsonify(result), 200

@friends_bp.route("/update_status", methods=['POST'])
def update_status():
    data = request.get_json()
    result = FriendsService.update_is_status(data)
    if "error" in result:
        return jsonify(result), 404
    return jsonify(result), 200

@friends_bp.route("/delete",methods=['DELETE','GET'])
def delete_by_id():
    id = request.args.get('id')
    return jsonify(FriendsService.delete_by_id(id)) , 200



