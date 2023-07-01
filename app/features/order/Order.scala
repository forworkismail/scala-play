package features.order

import play.api.libs.json.{Format, Json}

case class Order(id: Int, products: Map[Product, Int])
object Order {
  implicit val format: Format[Order] = Json.format
}