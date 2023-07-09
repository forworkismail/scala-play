package features.user.user_repository

import features.user.{User, UserTable}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserRepositoryImpl @Inject()(dbConfigProvider: DatabaseConfigProvider) extends UserRepository {
  val users = UserTable.UserTableQuery
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  override def findAll: Future[Either[String, Seq[User]]] =
    dbConfig.db
      .run(users.result)
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }

  override def findById(id: Long): Future[Either[String, Option[User]]] =
    dbConfig.db
      .run(users.filter(_.id === id).result.headOption)
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }

  override def create(entity: User): Future[Either[String, Int]] =
    dbConfig.db
      .run(users += entity)
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }

  override def update(id: Long, entity: User): Future[Either[String, Int]] =
    dbConfig.db
      .run(users.filter(_.id === id).update(entity))
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }

  override def delete(id: Long): Future[Either[String, Int]] =
    dbConfig.db
      .run(users.filter(_.id === id).delete)
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }

  override def findByUsername(name: String): Future[Either[String, Option[User]]] =
    dbConfig.db
      .run(users.filter(_.name === name).result.headOption)
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }
}
