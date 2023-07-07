package features.user

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

class UserTable(tag: Tag) extends Table[User](tag, "users") {

  def * : ProvenShape[User] = (id, name) <> (User.tupled, User.unapply)

  def id: Rep[Long] = column[Long]("ID", O.PrimaryKey, O.AutoInc)

  def name: Rep[String] = column[String]("NAME")
}

object UserTable {
  val UserTableQuery: TableQuery[UserTable] = TableQuery[UserTable]
}
