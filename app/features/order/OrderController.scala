package features.order

import play.api.libs.json.Json
import play.api.mvc._

import javax.inject.Inject

class OrderController @Inject()(val controllerComponents: ControllerComponents, orderService: OrderService)
    extends BaseController {

  def list(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(orderService.list()))
  }
}
