package features.user_role

import features.role.RoleTable
import features.user.UserTable
import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

class UserRoleTable(tag: Tag) extends Table[UserRole](tag, "user_roles") {
  def * : ProvenShape[UserRole] = (userId, roleId) <> (UserRole.tupled, UserRole.unapply)

  def userId: Rep[Long] = column[Long]("USER_ID")

  def user = foreignKey("USER_FK", userId, UserTable.UserTableQuery)(_.id)

  def role = foreignKey("ROLE_FK", roleId, RoleTable.RoleTableQuery)(_.id)

  def roleId: Rep[Long] = column[Long]("ROLE_ID")
}

object UserRoleTable {
  val UserRoleTableQuery: TableQuery[UserRoleTable] = TableQuery[UserRoleTable]

  def tupled: ((Long, Long)) => UserRole = (UserRole.apply _).tupled
}
