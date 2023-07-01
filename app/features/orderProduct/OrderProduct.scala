package features.orderProduct

import play.api.libs.json.{Format, Json}

case class OrderProduct(ean: Long, quantity: Int)
object OrderProduct {
  implicit val format: Format[OrderProduct] = Json.format
}
