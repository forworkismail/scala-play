package features.user

import features.role.Role
import play.api.libs.json.{Format, Json}

case class User(id: Long, name: String, roles: Seq[Role])

object User {
  implicit val format: Format[User] = Json.format
}
