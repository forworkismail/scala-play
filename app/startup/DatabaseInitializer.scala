package startup

import features.role.RoleTable
import features.user.UserTable
import features.user_role.UserRoleTable
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DatabaseInitializer @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  def createTables(): Future[Unit] = {
    val setupAction: DBIO[Unit] = DBIO.seq(
      UserTable.UserTableQuery.schema.createIfNotExists,
      RoleTable.RoleTableQuery.schema.createIfNotExists,
      UserRoleTable.UserRoleTableQuery.schema.createIfNotExists
    )

    db.run(setupAction)
  }
}
