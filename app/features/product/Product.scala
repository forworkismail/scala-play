package features.product

import features.Common.Money
import play.api.libs.json.{Format, Json}

case class Product(ean: Long, name: String, description: String, price: Money, quantity: Int)
object Product {
  implicit val format: Format[Product] = Json.format
}