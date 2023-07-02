package features.product

import features.common.Money
import play.api.libs.json.{Format, Json}

case class Product(ean: Long, name: String, description: String, price: Money, stock: Int)

object Product {
  implicit val format: Format[Product] = Json.format
}
