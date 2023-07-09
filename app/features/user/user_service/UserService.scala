package features.user.user_service

import features.common.Service
import features.role.Role
import features.user.User

import scala.concurrent.Future

trait UserService extends Service[User] {
  def findUserWithRoles(id: Long): Future[Either[String, (User, Seq[Role])]]
}
