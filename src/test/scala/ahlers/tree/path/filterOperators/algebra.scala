package ahlers.tree.path.filterOperators

private object algebra {

  sealed abstract class IsFilterOperator private (val toFilterOperator: FilterOperator)
  object IsFilterOperator {
    case object OfEqual                                                 extends IsFilterOperator(IsEqual)
    case class OfUnknown(override val toFilterOperator: FilterOperator) extends IsFilterOperator(toFilterOperator)

    def apply(operator: FilterOperator): IsFilterOperator = operator match {
      case IsEqual  => OfEqual
      case operator => OfUnknown(operator)
    }
  }

}
