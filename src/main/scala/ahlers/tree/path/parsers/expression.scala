package ahlers.tree.path.parsers

import ahlers.tree.path.expressions.Expression
import ahlers.tree.path.expressions.Selector
import parsley.Parsley
import parsley.Parsley.some

object expression {

  val selector: Parsley[Selector] = some(operator.any).map(Selector)

  val any: Parsley[Expression] = selector

}
