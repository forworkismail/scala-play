package features.role

import scala.concurrent.Future

class RoleServiceImpl extends RoleService {
  private val roles = Seq(
    Role(1, "Admin"),
    Role(2, "User"),
    Role(3, "Guest")
  )

  override def list(): Future[Seq[Role]] = Future.successful(roles)

  override def find(id: Long): Future[Option[Role]] = Future.successful(roles.find(_.id == id))
}
