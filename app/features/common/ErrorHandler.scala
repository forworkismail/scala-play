package features.common

import play.api.http.HttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc._

import javax.inject._
import scala.concurrent._

@Singleton
class ErrorHandler @Inject()(implicit ec: ExecutionContext) extends HttpErrorHandler {

  def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] =
    Future.successful(Status(statusCode)("A client error occurred: " + message))

  def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    println(s"An error occurred: ${exception.getMessage}")
    Future.successful(InternalServerError("An error occurred."))
  }
}
