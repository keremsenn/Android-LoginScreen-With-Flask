from email.policy import default

from app import db, ma

class Friends(db.Model):
    __tablename__ = "friends"

    id = db.Column("Id", db.Integer, primary_key=True, autoincrement=True)
    user_id = db.Column(
        "UsersId",
        db.Integer,
        db.ForeignKey("users.UserID", ondelete="CASCADE"),
        nullable=False
    )
    friend_id = db.Column(
        "FriendsId",
        db.Integer,
        db.ForeignKey("users.UserID", ondelete="CASCADE"),
        nullable=False
    )
    status = db.Column("Status", db.Boolean, nullable=False, default=False)
    created_at = db.Column("CreatedAt", db.DateTime, default=db.func.now(), nullable=False)

class FriendsSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Friends
        include_fk = True