package features.product_stock

import features.product.Product
import play.api.libs.json.{Format, Json}

case class ProductStock(product: Product, quantity: Int)

object ProductStock {
  implicit val format: Format[ProductStock] = Json.format
}
