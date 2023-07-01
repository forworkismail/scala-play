package features.product

trait ProductService {
  def list(): Seq[Product]
  def find(ean: Long): Option[Product]
  def update(product: Product): Either[String, Unit]
}