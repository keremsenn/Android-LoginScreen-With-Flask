from app import db, ma

class Users(db.Model):
    __tablename__ = "users"

    id = db.Column("UserID", db.Integer, primary_key=True, autoincrement=True)
    nick_name = db.Column("NickName", db.String(40),unique=True, nullable=False)
    first_name = db.Column("UserName", db.String(40), nullable=False)
    last_name = db.Column("LastName", db.String(40), nullable=False)
    email = db.Column("Email", db.String(120),unique=True, nullable=False)
    password = db.Column("PasswordHash", db.String(70), nullable=False)
    is_active = db.Column("IsApproved", db.Boolean, nullable=False, default=True)
    created_at = db.Column("CreatedAt", db.DateTime, default=db.func.now(), nullable=False)

class UsersSchema(ma.SQLAlchemyAutoSchema):
    class Meta:
        model = Users
        include_fk = True