package ahlers.tree.path.expressions.diffx

import ahlers.tree.path.expressions.Expression
import ahlers.tree.path.expressions.Logical
import ahlers.tree.path.expressions.Selector
import ahlers.tree.path.expressions.algebra.IsExpression
import ahlers.tree.path.operators.diffx.instances._
import com.softwaremill.diffx.Diff

object instances {

  implicit val diffSelector: Diff[Selector] = Diff.derived

  implicit val diffLogical: Diff[Logical] = Diff.derived

  implicit val diffExpression: Diff[Expression] = {
    implicit val diffIsExpression: Diff[IsExpression] = {
      import IsExpression.OfUnknown
      implicit val diffOfUnknown: Diff[OfUnknown] = Diff.useEquals
      Diff.derived
    }

    Diff[IsExpression].contramap(IsExpression(_))
  }

}
