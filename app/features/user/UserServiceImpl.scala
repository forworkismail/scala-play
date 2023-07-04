package features.user

import features.role.RoleService

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@Singleton
class UserServiceImpl @Inject()(roleService: RoleService) extends UserService {

  val user1Future: Future[Option[User]] =
    roleService.find(1L).map(roleOpt => roleOpt.map(role => User(1L, "John", Seq(role))))
  val user2Future: Future[Option[User]] =
    roleService.find(2L).map(roleOpt => roleOpt.map(role => User(2L, "Jane", Seq(role))))

  var users: Seq[User] = Seq(user1Future, user2Future).flatMap(futureUser => Await.result(futureUser, Duration.Inf))

  override def list(): Future[Seq[User]] = Future.successful(users)

  override def find(id: Long): Future[Option[User]] = Future.successful(users.find(_.id == id))

  override def create(user: User): Future[Either[String, User]] =
    users.find(_.id == user.id) match {
      case Some(_) => Future.successful(Left(s"User with id ${user.id} already exists"))
      case None =>
        users = users :+ user
        Future.successful(Right(user))
    }

  override def update(user: User): Future[Either[String, User]] =
    users.indexWhere(_.id == user.id) match {
      case -1 => Future.successful(Left(s"User with id ${user.id} does not exist"))
      case idx =>
        users = users.updated(idx, user)
        Future.successful(Right(user))
    }

  override def delete(id: Long): Future[Either[String, Unit]] =
    users.indexWhere(_.id == id) match {
      case -1 => Future.successful(Left(s"User with id $id does not exist"))
      case idx =>
        users = users.patch(idx, Nil, 1)
        Future.successful(Right(()))
    }
}
