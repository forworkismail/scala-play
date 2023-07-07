package features.role

import play.api.libs.json.{Format, Json}

case class Role(id: Long, name: String)

object Role {
  implicit val format: Format[Role] = Json.format

  val tupled: ((Long, String)) => Role = (Role.apply _).tupled
}
