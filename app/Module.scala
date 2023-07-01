import com.google.inject.AbstractModule
import features.order.{OrderService, OrderServiceImpl}
import features.product._

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ProductService]).to(classOf[ProductServiceImpl])
    bind(classOf[OrderService]).to(classOf[OrderServiceImpl])
  }
}
