from flask import Blueprint, jsonify, request
from ..service.levels_service import LevelsService

levels_bp = Blueprint("levels_api", __name__)

@levels_bp.route("/update_level", methods=['POST'])
def update_level():
    data = request.get_json()
    result = LevelsService.update_level(data)
    if "error" in result:
        return jsonify(result), 404
    return jsonify(result), 200

@levels_bp.route("/id")
def get_by_id():
    level_id = request.args.get('id')
    return jsonify(LevelsService.get_by_id(level_id)) , 200

@levels_bp.route("/user_id")
def get_by_user_id():
    user_id = request.args.get('user_id')
    return jsonify(LevelsService.get_by_user_id(user_id)) , 200
