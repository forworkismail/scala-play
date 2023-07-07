package features.order_line

import features.product.Product
import play.api.libs.json.{Format, Json}

case class OrderLine(product: Product, quantity: Int)
object OrderLine {
  implicit val format: Format[OrderLine] = Json.format
}
