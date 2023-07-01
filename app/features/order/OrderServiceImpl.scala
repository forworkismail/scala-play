package features.order

import features.product.ProductService

class OrderServiceImpl(productService: ProductService) extends OrderService {
  private val orders = Seq(
    Order(1010255079763L,
      Map(productService.find(5010255079763L).get -> 10, productService.find(5018206244666L).get -> 20))
  )

  override def list(): Seq[Order] = orders

  override def find(id: Long): Option[Order] = orders.find(_.id == id)

  override def place(order: Order): Either[String, Long] = ???

}
