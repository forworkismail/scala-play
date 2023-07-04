package features.user

import scala.concurrent.Future

trait UserService {
  def list(): Future[Seq[User]]

  def find(id: Long): Future[Option[User]]

  def create(user: User): Future[Either[String, User]]

  def update(user: User): Future[Either[String, User]]

  def delete(id: Long): Future[Either[String, Unit]]
}
