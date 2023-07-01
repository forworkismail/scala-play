package features.order

import features.orderProduct.OrderProduct
import features.product.ProductService

import javax.inject.Inject

class OrderServiceImpl @Inject()(productService: ProductService) extends OrderService {
  private var orders = Seq(
    Order(1010255079763L,
      Seq(
        OrderProduct(5010255079763L, 10),
        OrderProduct(5018206244666L, 20)
      ))
  )

  override def list(): Seq[Order] = orders

  override def find(id: Long): Option[Order] = orders.find(_.id == id)

  override def place(order: Order): Either[String, Long] = {
    val allProductsExist = order.products.forall(orderProduct => productService.find(orderProduct.ean).isDefined)
    if (!allProductsExist) {
      Left("Some products in the order do not exist")
    } else {
      val newId = orders.maxBy(_.id).id + 1
      val newOrder = order.copy(id = newId)
      orders = orders :+ newOrder
      Right(newId)
    }
  }
}
