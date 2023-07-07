package features.warehouse

import features.product_stock.ProductStock
import play.api.libs.json.{Format, Json}

case class Warehouse(id: Long, location: String, stock: Seq[ProductStock])

object Warehouse {
  implicit val format: Format[Warehouse] = Json.format
}
