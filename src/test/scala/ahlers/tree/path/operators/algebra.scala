package ahlers.tree.path.operators

private object algebra {

  sealed abstract class IsOperator private (val toOperator: Operator)
  object IsOperator {
    case object OfRootElement                                                            extends IsOperator(RootElement)
    case object OfCurrentNode                                                            extends IsOperator(CurrentNode)
    case object OfWildcard                                                               extends IsOperator(Wildcard)
    case object OfDeepScan                                                               extends IsOperator(DeepScan)
    case class OfDotNotatedChild(override val toOperator: DotNotatedChild)               extends IsOperator(toOperator)
    case class OfBracketNotatedChildren(override val toOperator: BracketNotatedChildren) extends IsOperator(toOperator)
    case class OfArrayIndexes(override val toOperator: ArrayIndexes)                     extends IsOperator(toOperator)
    case class OfArraySlice(override val toOperator: ArraySlice)                         extends IsOperator(toOperator)
    case class OfUnknown(override val toOperator: Operator)                              extends IsOperator(toOperator)

    def apply(operator: Operator): IsOperator = operator match {
      case RootElement                      => OfRootElement
      case CurrentNode                      => OfCurrentNode
      case Wildcard                         => OfWildcard
      case DeepScan                         => OfDeepScan
      case operator: DotNotatedChild        => OfDotNotatedChild(operator)
      case operator: BracketNotatedChildren => OfBracketNotatedChildren(operator)
      case operator: ArrayIndexes           => OfArrayIndexes(operator)
      case operator: ArraySlice             => OfArraySlice(operator)
      case operator                         => OfUnknown(operator)
    }
  }

}
