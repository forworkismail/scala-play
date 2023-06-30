package features.money

sealed trait Currency
case object USD extends Currency
case object EUR extends Currency
case object GBP extends Currency
