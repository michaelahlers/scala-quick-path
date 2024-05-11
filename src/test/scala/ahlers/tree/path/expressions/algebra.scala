package ahlers.tree.path.expressions

private object algebra {

  sealed abstract class IsExpression private (val toExpression: Expression)
  object IsExpression {
    case class OfSelector(override val toExpression: Selector)  extends IsExpression(toExpression)
    case class OfLogical(override val toExpression: Logical)    extends IsExpression(toExpression)
    case class OfUnknown(override val toExpression: Expression) extends IsExpression(toExpression)

    def apply(expression: Expression): IsExpression = expression match {
      case expression: Selector => OfSelector(expression)
      case expression: Logical  => OfLogical(expression)
      case expression           => OfUnknown(expression)
    }
  }

}
