package features.user.user_repository

import features.common.Repository
import features.user.{User, UserTable}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserRepositoryImpl(db: Database) extends Repository[User] with UserRepository {
  val users = UserTable.UserTableQuery

  override def findAll: Future[Either[String, Seq[User]]] =
    db.run(users.result)
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }

  override def findById(id: Long): Future[Either[String, Option[User]]] =
    db.run(users.filter(_.id === id).result.headOption)
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }

  override def create(entity: User): Future[Either[String, Int]] =
    db.run(users += entity)
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }

  override def update(id: Long, entity: User): Future[Either[String, Int]] =
    db.run(users.filter(_.id === id).update(entity))
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }

  override def delete(id: Long): Future[Either[String, Int]] =
    db.run(users.filter(_.id === id).delete)
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }

  override def findByUsername(name: String): Future[Either[String, Option[User]]] =
    db.run(users.filter(_.name === name).result.headOption)
      .map(Right(_))
      .recover { case ex => Left(ex.getMessage) }
}
