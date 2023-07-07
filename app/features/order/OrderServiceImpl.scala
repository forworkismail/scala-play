package features.order

import features.order_line.OrderLine
import features.product.ProductService

import javax.inject.Inject

class OrderServiceImpl @Inject()(productService: ProductService) extends OrderService {
  private var orders = Seq(
    Order(1010255079763L,
          Seq(
            OrderLine(productService.find(5010255079763L).get, 10),
            OrderLine(productService.find(5018206244666L).get, 20)
          ))
  )

  override def list(): Seq[Order] = orders

  override def find(id: Long): Option[Order] = orders.find(_.id == id)

  override def place(order: Order): Either[String, Long] = {
    val allProductsExist = order.lines.forall(orderLine => productService.find(orderLine.product.ean).isDefined)
    if (!allProductsExist) {
      Left("Some products in the order do not exist")
    } else {
      val newId    = orders.maxBy(_.id).id + 1
      val newOrder = order.copy(id = newId)
      orders = orders :+ newOrder
      Right(newId)
    }
  }
}
