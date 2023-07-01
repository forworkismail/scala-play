package features.order

import features.orderProduct.OrderProduct
import play.api.libs.json.{Format, Json}

case class Order(id: Long, products: Seq[OrderProduct])
object Order {
  implicit val format: Format[Order] = Json.format
}
