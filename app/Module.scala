import com.google.inject.AbstractModule
import features.order.{OrderService, OrderServiceImpl}
import features.product._
import features.role.{RoleService, RoleServiceImpl}
import features.user.user_repository.{UserRepository, UserRepositoryImpl}
import features.user.user_service.{UserService, UserServiceImpl}
import features.warehouse.{WarehouseService, WarehouseServiceImpl}
import startup.{DatabaseInitializer, StartupInitializer}

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ProductService]).to(classOf[ProductServiceImpl])
    bind(classOf[OrderService]).to(classOf[OrderServiceImpl])
    bind(classOf[WarehouseService]).to(classOf[WarehouseServiceImpl])
    bind(classOf[RoleService]).to(classOf[RoleServiceImpl])
    bind(classOf[UserService]).to(classOf[UserServiceImpl])
    bind(classOf[UserRepository]).to(classOf[UserRepositoryImpl])
    bind(classOf[DatabaseInitializer]).asEagerSingleton()
    bind(classOf[StartupInitializer]).asEagerSingleton()
  }
}
