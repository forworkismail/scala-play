package features.product

import play.api.libs.json._
import play.api.mvc._

import javax.inject.Inject

class ProductController @Inject()(val controllerComponents: ControllerComponents, productService: ProductService)
  extends BaseController {

  def list(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(productService.list()))
  }
}
