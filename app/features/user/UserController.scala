package features.user

import play.api.Logger
import play.api.libs.json._
import play.api.mvc._

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global

class UserController @Inject()(val controllerComponents: ControllerComponents, userService: UserService)
  extends BaseController {

  val logger: Logger = Logger(this.getClass)

  def list(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    logger.info("Listing users...")
    userService.list().map { users =>
      Ok(Json.toJson(users))
    }
  }

  def get(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    userService.find(id).map {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound
    }
  }
}
