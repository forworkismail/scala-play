package features.user

import features.role.Role

case class User(id: Long, name: String, roles: Seq[Role])
