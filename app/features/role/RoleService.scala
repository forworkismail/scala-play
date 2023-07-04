package features.role

import scala.concurrent.Future

trait RoleService {
  def list(): Future[Seq[Role]]
  def find(id: Long): Future[Option[Role]]
}
