package features.user

import play.api.libs.json.{Format, Json}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

case class User(id: Long, name: String)

object User {
  implicit val format: Format[User] = Json.format
  val UserTableQuery: TableQuery[UserTable] = TableQuery[UserTable]

  def tupled: ((Long, String)) => User = {
    case (id, name) => User(id, name)
  }

  class UserTable(tag: Tag) extends Table[User](tag, "USER") {
    def * : ProvenShape[User] = (id, name) <> (tupled, User.unapply)

    def id: Rep[Long] = column[Long]("ID", O.PrimaryKey, O.AutoInc)

    def name: Rep[String] = column[String]("NAME")
  }
}
