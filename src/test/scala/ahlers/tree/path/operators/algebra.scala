package ahlers.tree.path.operators

private object algebra {

  sealed abstract class IsOperator private (val toOperator: Operator)
  object IsOperator {
    case object OfRootElement                                                     extends IsOperator(RootElement)
    case object OfCurrentNode                                                     extends IsOperator(CurrentNode)
    case object OfWildcard                                                        extends IsOperator(Wildcard)
    case object OfDeepScan                                                        extends IsOperator(DeepScan)
    case class OfDotNotation(override val toOperator: DotNotatedChild)            extends IsOperator(toOperator)
    case class OfBracketNotation(override val toOperator: BracketNotatedChildren) extends IsOperator(toOperator)
    case class OfArrayIndexes(override val toOperator: ArrayIndexes)              extends IsOperator(toOperator)
    case class OfArraySlice(override val toOperator: ArraySlice)                  extends IsOperator(toOperator)
    case class OfUnknown(override val toOperator: Operator)                       extends IsOperator(toOperator)

    def apply(operator: Operator): IsOperator = operator match {
      case RootElement                                    => OfRootElement
      case CurrentNode                                    => OfCurrentNode
      case Wildcard                                       => OfWildcard
      case DeepScan                                       => OfDeepScan
      case dotNotatedChild: DotNotatedChild               => OfDotNotation(dotNotatedChild)
      case bracketNotatedChildren: BracketNotatedChildren => OfBracketNotation(bracketNotatedChildren)
      case arrayIndexes: ArrayIndexes                     => OfArrayIndexes(arrayIndexes)
      case arraySlice: ArraySlice                         => OfArraySlice(arraySlice)
      case operator                                       => OfUnknown(operator)
    }
  }

}
