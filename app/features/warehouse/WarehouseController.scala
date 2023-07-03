package features.warehouse

import play.api.libs.json._
import play.api.mvc._

import javax.inject.Inject

class WarehouseController @Inject()(val controllerComponents: ControllerComponents, warehouseService: WarehouseService)
    extends BaseController {

  def list(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(warehouseService.list()))
  }
}
