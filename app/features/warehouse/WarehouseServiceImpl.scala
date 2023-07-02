package features.warehouse

import features.common.{Money, USD}
import features.order.Order
import features.product.Product

import javax.inject.Singleton

@Singleton
class WarehouseServiceImpl extends WarehouseService {
  private var warehouses = Seq(
    Warehouse(
      1L,
      "New York",
      Map(
        Product(5010255079763L, "Paperclips Large", "Large Plain Pack of 1000", Money(5.0, USD), 100) -> 500,
        Product(5018206244666L, "Giant Paperclips", "Giant Plain 51mm 100 pack", Money(10.0, USD), 200) -> 300
      )
    ),
    Warehouse(
      2L,
      "San Francisco",
      Map(
        Product(5018306332812L, "Paperclip Giant Plain", "Giant Plain Pack of 10000", Money(15.0, USD), 150) -> 200,
        Product(5018306312913L, "No Tear Paper Clip", "No Tear Extra Large Pack of 1000", Money(20.0, USD), 50) -> 150
      )
    )
  )

  override def list(): Seq[Warehouse] = warehouses

  override def find(id: Long): Option[Warehouse] = warehouses.find(_.id == id)

  override def restock(warehouse: Warehouse, product: Product, quantity: Int): Either[String, Unit] = {
    val index = warehouses.indexOf(warehouse)
    if (index == -1) {
      Left("Warehouse does not exist")
    } else {
      val oldStock = warehouse.stock.getOrElse(product, 0)
      val updatedWarehouse = warehouse.copy(stock = warehouse.stock.updated(product, oldStock + quantity))
      warehouses = warehouses.updated(index, updatedWarehouse)
      Right(())
    }
  }

  override def dispatch(warehouse: Warehouse, order: Order): Either[String, Unit] = {
    val index = warehouses.indexOf(warehouse)
    if (index == -1) {
      Left("Warehouse does not exist")
    } else {
      order.lines.foreach { orderLine =>
        val stock = warehouse.stock.getOrElse(orderLine.product, 0)
        if (stock < orderLine.quantity) {
          return Left(s"Not enough stock for product ${orderLine.product.ean}")
        }
      }
      val updatedStock = order.lines.foldLeft(warehouse.stock) { (stock, orderLine) =>
        stock.updated(orderLine.product, stock(orderLine.product) - orderLine.quantity)
      }
      val updatedWarehouse = warehouse.copy(stock = updatedStock)
      warehouses = warehouses.updated(index, updatedWarehouse)
      Right(())
    }
  }

}
