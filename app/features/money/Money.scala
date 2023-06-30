package features.money

case class Money(amount: BigDecimal, currency: Currency) {
  require(amount >= 0, "Amount of money cannot be negative")

  def +(other: Money): Either[String, Money] = {
    if (other.currency == currency) {
      Right(Money(amount + other.amount, currency))
    } else {
      Left("Cannot add money with different currencies")
    }
  }

  def -(other: Money): Either[String, Money] = {
    if (other.currency == currency) {
      if (amount >= other.amount) {
        Right(Money(amount - other.amount, currency))
      } else {
        Left("Result would be negative")
      }
    } else {
      Left("Cannot subtract money with different currencies")
    }
  }

  // Other arithmetic operations can be defined similarly
}
