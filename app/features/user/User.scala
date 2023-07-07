package features.user

import play.api.libs.json.{Format, Json}

case class User(id: Long, name: String)

object User {
  implicit val format: Format[User] = Json.format

  def tupled: ((Long, String)) => User = (User.apply _).tupled
}
