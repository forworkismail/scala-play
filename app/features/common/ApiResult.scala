package features.common

case class ApiResult[T](status: String, data: Option[T])
