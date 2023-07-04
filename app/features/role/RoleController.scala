package features.role

import play.api.libs.json._
import play.api.mvc._

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global

class RoleController @Inject()(val controllerComponents: ControllerComponents, roleService: RoleService)
  extends BaseController {

  def list(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    roleService.list().map { roles =>
      Ok(Json.toJson(roles))
    }
  }

  def get(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    roleService.find(id).map {
      case Some(role) => Ok(Json.toJson(role))
      case None => NotFound
    }
  }
}
