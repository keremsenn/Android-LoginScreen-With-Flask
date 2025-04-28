from flask import Blueprint, jsonify, request
from ..service.users_service import UsersService

users_bp = Blueprint("users_api", __name__)


@users_bp.route("/register",methods=['POST'])
def register():
    data = request.get_json()
    return jsonify(UsersService.register(data)), 200


@users_bp.route("/login",methods=['POST','GET'])
def login():
    data = request.get_json()
    return jsonify(UsersService.login(data)), 200

@users_bp.route("/update_is_active", methods=['POST'])
def update_is_active():
    data = request.get_json()
    result = UsersService.update_is_active(data)
    if "error" in result:
        return jsonify(result), 404
    return jsonify(result), 200

@users_bp.route("/id")
def get_by_id():
    users_id = request.args.get('id')
    return jsonify(UsersService.get_by_id(users_id)) , 200

@users_bp.route("/nick_name")
def get_by_nick_name():
    nick_name = request.args.get('nick_name')
    return jsonify(UsersService.get_by_nick_name(nick_name)) , 200


