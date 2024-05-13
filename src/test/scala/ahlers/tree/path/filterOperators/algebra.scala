package ahlers.tree.path.filterOperators

private object algebra {

  sealed abstract class IsFilterOperator private (val toFilterOperator: FilterOperator)
  object IsFilterOperator {
    case object OfIsEqual                                               extends IsFilterOperator(IsEqualTo)
    case object OfIsNotEqual                                            extends IsFilterOperator(IsNotEqualTo)
    case object OfIsLessThan                                            extends IsFilterOperator(IsLessThan)
    case object OfIsLessThanOrEqualTo                                   extends IsFilterOperator(IsLessThanOrEqualTo)
    case object OfIsGreaterThan                                         extends IsFilterOperator(IsGreaterThan)
    case object OfIsGreaterThanOrEqualTo                                extends IsFilterOperator(IsGreaterThanOrEqualTo)
    case object OfIsMatchOf                                             extends IsFilterOperator(IsMatchOf)
    case object OfIsIn                                                  extends IsFilterOperator(IsIn)
    case object OfIsNotIn                                               extends IsFilterOperator(IsNotIn)
    case object OfIsSubsetOf                                            extends IsFilterOperator(IsSubsetOf)
    case object OfIsAnyOf                                               extends IsFilterOperator(IsAnyOf)
    case object OfIsNoneOf                                              extends IsFilterOperator(IsNoneOf)
    case object OfHasSize                                               extends IsFilterOperator(HasSize)
    case object OfIsEmpty                                               extends IsFilterOperator(IsEmpty)
    case class OfUnknown(override val toFilterOperator: FilterOperator) extends IsFilterOperator(toFilterOperator)

    def apply(operator: FilterOperator): IsFilterOperator = operator match {
      case IsEqualTo              => OfIsEqual
      case IsNotEqualTo           => OfIsNotEqual
      case IsLessThan             => OfIsLessThan
      case IsLessThanOrEqualTo    => OfIsLessThanOrEqualTo
      case IsGreaterThan          => OfIsGreaterThan
      case IsGreaterThanOrEqualTo => OfIsGreaterThanOrEqualTo
      case IsMatchOf              => OfIsMatchOf
      case IsIn                   => OfIsIn
      case IsNotIn                => OfIsNotIn
      case IsSubsetOf             => OfIsSubsetOf
      case IsAnyOf                => OfIsAnyOf
      case IsNoneOf               => OfIsNoneOf
      case HasSize                => OfHasSize
      case IsEmpty                => OfIsEmpty
      case operator               => OfUnknown(operator)
    }
  }

}
