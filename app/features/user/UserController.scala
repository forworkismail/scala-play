package features.user

import features.user.user_service.UserService
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global

class UserController @Inject()(val controllerComponents: ControllerComponents, userService: UserService)
  extends BaseController {

  val logger: Logger = Logger(this.getClass)

  def list: Action[AnyContent] = Action.async { implicit request =>
    userService.list().map {
      case Right(users) => Ok(Json.toJson(users))
      case Left(error) => InternalServerError(error)
    }
  }
}
