package features.warehouse

import features.order.Order
import features.product.Product

trait WarehouseService {
  def list(): Seq[Warehouse]
  def find(id: Long): Option[Warehouse]
  def restock(warehouse: Warehouse, product: Product, quantity: Int): Either[String, Unit]
  def dispatch(warehouse: Warehouse, order: Order): Either[String, Unit]
}
