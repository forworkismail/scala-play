package features.order

import features.order_line.OrderLine
import play.api.libs.json.{Format, Json}

case class Order(id: Long, lines: Seq[OrderLine])

object Order {
  implicit val format: Format[Order] = Json.format
}
