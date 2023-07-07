package features.warehouse

import features.order.Order
import features.product.{Product, ProductService}
import features.product_stock.ProductStock

import javax.inject.{Inject, Singleton}

@Singleton
class WarehouseServiceImpl @Inject()(productService: ProductService) extends WarehouseService {
  private var warehouses = Seq(
    Warehouse(1L,
              "New York",
              Seq(
                ProductStock(productService.find(5010255079763L).get, 100),
                ProductStock(productService.find(5018206244666L).get, 200),
              )),
    Warehouse(2L,
              "San Francisco",
              Seq(
                ProductStock(productService.find(5010255079763L).get, 50),
                ProductStock(productService.find(5018206244666L).get, 150),
              ))
  )

  override def list(): Seq[Warehouse] = warehouses

  override def find(id: Long): Option[Warehouse] = warehouses.find(_.id == id)

  override def restock(warehouse: Warehouse, product: Product, quantity: Int): Either[String, Unit] = {
    val index = warehouses.indexOf(warehouse)
    if (index == -1) {
      Left("Warehouse does not exist")
    } else {
      val updatedStock = warehouse.stock.find(_.product == product) match {
        case Some(stockItem) => warehouse.stock.updated(index, stockItem.copy(quantity = stockItem.quantity + quantity))
        case None            => warehouse.stock :+ ProductStock(product, quantity)
      }
      val updatedWarehouse = warehouse.copy(stock = updatedStock)
      warehouses = warehouses.updated(index, updatedWarehouse)
      Right(())
    }
  }

  override def dispatch(warehouse: Warehouse, order: Order): Either[String, Unit] = {
    val index = warehouses.indexOf(warehouse)
    if (index == -1) {
      Left("Warehouse does not exist")
    } else {
      val updatedStockOrError: Either[String, Seq[ProductStock]] =
        order.lines.foldLeft[Either[String, Seq[ProductStock]]](Right(warehouse.stock)) {
          case (Left(errorMessage), _) => Left(errorMessage)
          case (Right(updatedStock), orderLine) =>
            updatedStock.find(_.product == orderLine.product) match {
              case Some(productStock) =>
                if (productStock.quantity < orderLine.quantity) {
                  Left(s"Not enough stock for product ${orderLine.product.ean}")
                } else {
                  val updatedProductStock = productStock.copy(quantity = productStock.quantity - orderLine.quantity)
                  Right(updatedStock.updated(updatedStock.indexOf(productStock), updatedProductStock))
                }
              case None => Left(s"Product ${orderLine.product.ean} not found in warehouse")
            }
        }

      updatedStockOrError match {
        case Left(errorMessage) => Left(errorMessage)
        case Right(updatedStock) =>
          val updatedWarehouse = warehouse.copy(stock = updatedStock)
          warehouses = warehouses.updated(index, updatedWarehouse)
          Right(())
      }
    }
  }

}
