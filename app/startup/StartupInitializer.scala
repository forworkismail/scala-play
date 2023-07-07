package startup

import play.api.Logging

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

@Singleton
class StartupInitializer @Inject()(databaseInitializer: DatabaseInitializer) extends Logging {

  databaseInitializer.createTables().onComplete {
    case Success(_) => logger.info("Database initialization successful.")
    case Failure(exception) => logger.error("Database initialization failed.", exception)
  }

  //  lifecycle.addStopHook { () =>
  //    logger.info("Application shutdown...")
  //    Future.successful(())
  //  }
}
