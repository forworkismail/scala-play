package features.role

sealed trait Role
case object Customer         extends Role
case object WarehouseManager extends Role
case object Admin            extends Role
