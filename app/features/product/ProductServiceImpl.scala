package features.product

import features.Common.{Money, USD}

class ProductServiceImpl extends ProductService {

  private val products = Seq(
    Product(5010255079763L, "Paperclips Large", "Large Plain Pack of 1000", Money(5.0, USD), 100),
    Product(5018206244666L, "Giant Paperclips", "Giant Plain 51mm 100 pack", Money(10.0, USD), 200),
    Product(5018306332812L, "Paperclip Giant Plain", "Giant Plain Pack of 10000", Money(15.0, USD), 500),
    Product(5018306312913L, "No Tear Paper Clip", "No Tear Extra Large Pack of 1000", Money(8.0, USD), 150),
    Product(5018206244611L, "Zebra Paperclips", "Zebra Length 28mm Assorted 150 Pack", Money(12.0, USD), 250)
  )

  override def list(): Seq[Product] = products

  override def find(ean: Long): Option[Product] = products.find(_.ean == ean)

  override def update(product: Product): Either[String, Unit] = {
    products.indexWhere(_.ean == product.ean) match {
      case -1 => Left("Product not found")
      case i =>
       Right(products.updated(i, product))
    }
  }
}
