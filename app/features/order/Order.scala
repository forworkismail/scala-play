package features.order

case class Order(id: Int, products: Map[Product, Int])