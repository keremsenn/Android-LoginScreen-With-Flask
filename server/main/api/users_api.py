from flask import Blueprint, jsonify, request
from ..service.users_service import UsersService

users_bp = Blueprint("users_api", __name__)


@users_bp.route("/register", methods=['POST'])
def register():
    data = request.get_json()
    result, status_code = UsersService.register(data)  # Tuple unpacking
    return jsonify(result), status_code


@users_bp.route("/login",methods=['POST','GET'])
def login():
    data = request.get_json()
    result, status_code = UsersService.login(data)
    return jsonify(result),status_code

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

@users_bp.route("/all")
def get_all():
    return jsonify(UsersService.get_all()), 200

@users_bp.route("/nick_name")
def get_by_nick_name():
    nick_name = request.args.get('nick_name')
    return jsonify(UsersService.get_by_nick_name(nick_name)) , 200

@users_bp.route("/delete"  ,methods=['DELETE', 'GET'])
def delete():
    user_id = request.args.get('id')
    return jsonify(UsersService.delete_by_user_id(user_id)) , 200

@users_bp.route("/update_account", methods=['PATCH'])
def update_account():
    data = request.get_json()
    result ,status_code= UsersService.update_account(data)
    return jsonify(result), status_code

@users_bp.route("/email_change", methods=['POST'])
def email_change():
    data = request.get_json()
    result = UsersService.email_change(data)
    if "hata" in result:
        return jsonify(result), 400
    return jsonify(result), 200

@users_bp.route("/nick_name_change", methods=['POST'])
def nick_name_change():
    data = request.get_json()
    result = UsersService.nick_name_change(data)
    if "hata" in result:
        return jsonify(result), 400
    return jsonify(result), 200



