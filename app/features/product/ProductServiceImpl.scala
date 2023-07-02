package features.product

import features.common.{Money, USD}

import javax.inject.Singleton

@Singleton
class ProductServiceImpl extends ProductService {

  private var products = Seq(
    Product(5010255079763L, "Paperclips Large", "Large Plain Pack of 1000", Money(5.0, USD), 100),
    Product(5018206244666L, "Giant Paperclips", "Giant Plain 51mm 100 pack", Money(10.0, USD), 200)
  )

  override def list(): Seq[Product] = products

  override def find(ean: Long): Option[Product] = products.find(_.ean == ean)

  override def create(product: Product): Either[String, Long] =
    if (products.exists(_.ean == product.ean)) {
      Left("Product with this EAN already exists")
    } else {
      val newProduct = product.copy(ean = products.map(_.ean).max + 1)
      products = products :+ newProduct
      Right(newProduct.ean)
    }

  override def update(product: Product): Either[String, Unit] =
    products.indexWhere(_.ean == product.ean) match {
      case -1 => Left("No such product")
      case idx =>
        products = products.updated(idx, product)
        Right(())
    }
}
