package ahlers.tree.path.filterOperators

private object algebra {

  sealed abstract class IsFilterOperator private (val toFilterOperator: FilterOperator)
  object IsFilterOperator {
    case object OfIsEqual                                               extends IsFilterOperator(IsEqual)
    case object OfIsNotEqual                                            extends IsFilterOperator(IsNotEqual)
    case class OfUnknown(override val toFilterOperator: FilterOperator) extends IsFilterOperator(toFilterOperator)

    def apply(operator: FilterOperator): IsFilterOperator = operator match {
      case IsEqual    => OfIsEqual
      case IsNotEqual => OfIsNotEqual
      case operator   => OfUnknown(operator)
    }
  }

}
