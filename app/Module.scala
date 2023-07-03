import com.google.inject.AbstractModule
import features.order.{OrderService, OrderServiceImpl}
import features.product._
import features.warehouse.{WarehouseService, WarehouseServiceImpl}

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ProductService]).to(classOf[ProductServiceImpl])
    bind(classOf[OrderService]).to(classOf[OrderServiceImpl])
    bind(classOf[WarehouseService]).to(classOf[WarehouseServiceImpl])
  }
}
