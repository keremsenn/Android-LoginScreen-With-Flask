from werkzeug.exceptions import NotFound
from ..model.levels_model import  Levels,LevelsSchema
from app import db

class LevelsService:

    @staticmethod
    def update_level(data):
        print(data)
        level_user = Levels.query.filter_by(
            id=data.get('LevelsId'),
            user_id = data.get('UserId')
        ).first()

        if not level_user:
            return {"error": f"Users not found"}
        new_level = data.get('Level')
        level_user.level = new_level
        db.session.commit()

        return {"message": f"new level: {new_level}"}

    @staticmethod
    def get_by_id(level_id):
        level_user_query = Levels.query.filter_by(id=level_id).first()
        schema = LevelsSchema()
        level_user = schema.dump(level_user_query)
        if not level_user:
            raise NotFound(f"Levels with id {level_id} not found")
        return level_user

    @staticmethod
    def get_by_user_id(user_id):
        level_user_query = Levels.query.filter_by(user_id=user_id).first()
        schema = LevelsSchema()
        level_user = schema.dump(level_user_query)
        if not level_user:
            raise NotFound(f"levels with user id {user_id} not found")
        return level_user


