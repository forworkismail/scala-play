package features.user.user_repository

import features.common.Repository
import features.user.User

import scala.concurrent.Future

trait UserRepository extends Repository[User] {
  def findByUsername(name: String): Future[Either[String, Option[User]]]
}
