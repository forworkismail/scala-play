package features.common

import play.api.libs.json._

sealed trait Currency

case object USD extends Currency

case object EUR extends Currency

case object GBP extends Currency

object Currency {
  implicit val format: Format[Currency] = new Format[Currency] {
    def writes(currency: Currency): JsValue = JsString(currency.toString)

    def reads(json: JsValue): JsResult[Currency] = json match {
      case JsString(s) =>
        s match {
          case "USD" => JsSuccess(USD)
          case "EUR" => JsSuccess(EUR)
          case "GBP" => JsSuccess(GBP)
          case _ => JsError("Unknown currency")
        }
      case _ => JsError("Expected a JSON string")
    }
  }
}
