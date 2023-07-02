package features.warehouse

import play.api.libs.json.{Format, Json}

case class Warehouse(id: Long, location: String, stock: Map[Product, Int])

object Warehouse {
  implicit val format: Format[Warehouse] = Json.format
}
