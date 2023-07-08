package features.common

import scala.concurrent.Future

trait Service[T] {
  def list(): Future[Either[String, Seq[T]]]
  def find(id: Long): Future[Either[String, Option[T]]]
  def create(entity: T): Future[Either[String, T]]
  def update(entity: T): Future[Either[String, T]]
  def delete(id: Long): Future[Either[String, Unit]]
}
