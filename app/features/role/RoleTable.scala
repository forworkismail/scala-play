package features.role

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

class RoleTable(tag: Tag) extends Table[Role](tag, "roles") {

  import Role.tupled

  def * : ProvenShape[Role] = (id, name) <> (tupled, Role.unapply)

  def id: Rep[Long] = column[Long]("ID", O.PrimaryKey, O.AutoInc)

  def name: Rep[String] = column[String]("NAME")
}

object RoleTable {
  val RoleTableQuery: TableQuery[RoleTable] = TableQuery[RoleTable]
}
