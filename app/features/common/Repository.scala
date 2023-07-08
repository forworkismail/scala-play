package features.common

import scala.concurrent.Future

trait Repository[T] {
  def findAll: Future[Either[String, Seq[T]]]
  def findById(id: Long): Future[Either[String, Option[T]]]
  def create(entity: T): Future[Either[String, Int]]
  def update(id: Long, entity: T): Future[Either[String, Int]]
  def delete(id: Long): Future[Either[String, Int]]
}
