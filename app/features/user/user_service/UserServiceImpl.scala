package features.user.user_service

import features.role.Role
import features.user.User
import features.user.user_repository.UserRepository

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserServiceImpl @Inject()(userRepository: UserRepository)(
    implicit ec: ExecutionContext
) extends UserService {

  override def findUserWithRoles(id: Long): Future[Either[String, (User, Seq[Role])]] =
    Future.successful(Left("findUserWithRoles method not implemented"))

  override def list(): Future[Either[String, Seq[User]]] = userRepository.findAll

  override def find(id: Long): Future[Either[String, Option[User]]] = userRepository.findById(id)

  override def create(entity: User): Future[Either[String, User]] =
    userRepository.create(entity).map(_.map(_ => entity))

  override def update(entity: User): Future[Either[String, User]] =
    userRepository.update(entity.id, entity).map(_.map(_ => entity))

  override def delete(id: Long): Future[Either[String, Unit]] =
    userRepository.delete(id).map(_.map(_ => ()))
}
