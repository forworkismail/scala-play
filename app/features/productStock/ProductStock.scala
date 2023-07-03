package features.productStock

import play.api.libs.json.{Format, Json}

case class ProductStock(product: Product, quantity: Int)

object ProductStock {
  implicit val format: Format[ProductStock] = Json.format
}
