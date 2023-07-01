package features.order

trait OrderService {
  def list(): Seq[Order]

  def find(id: Long): Option[Order]

  def place(order: Order): Either[String, Long]
}
