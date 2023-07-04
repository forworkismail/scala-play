package features.user

import features.role.Role

case class User(id: Long, name: String, role: Role)
