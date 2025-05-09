"""migration

Revision ID: b0a07fd0bfa3
Revises: 2efadc238562
Create Date: 2025-04-29 12:04:52.069075

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = 'b0a07fd0bfa3'
down_revision = '2efadc238562'
branch_labels = None
depends_on = None


def upgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    with op.batch_alter_table('friends', schema=None) as batch_op:
        batch_op.drop_constraint('friends_ibfk_2', type_='foreignkey')
        batch_op.drop_constraint('friends_ibfk_1', type_='foreignkey')
        batch_op.create_foreign_key(None, 'users', ['UsersId'], ['UserID'], ondelete='CASCADE')
        batch_op.create_foreign_key(None, 'users', ['FriendsId'], ['UserID'], ondelete='CASCADE')

    with op.batch_alter_table('levels', schema=None) as batch_op:
        batch_op.drop_constraint('levels_ibfk_1', type_='foreignkey')
        batch_op.create_foreign_key(None, 'users', ['UsersId'], ['UserID'], ondelete='CASCADE')

    # ### end Alembic commands ###


def downgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    with op.batch_alter_table('levels', schema=None) as batch_op:
        batch_op.drop_constraint(None, type_='foreignkey')
        batch_op.create_foreign_key('levels_ibfk_1', 'users', ['UsersId'], ['UserID'])

    with op.batch_alter_table('friends', schema=None) as batch_op:
        batch_op.drop_constraint(None, type_='foreignkey')
        batch_op.drop_constraint(None, type_='foreignkey')
        batch_op.create_foreign_key('friends_ibfk_1', 'users', ['FriendsId'], ['UserID'])
        batch_op.create_foreign_key('friends_ibfk_2', 'users', ['UsersId'], ['UserID'])

    # ### end Alembic commands ###
