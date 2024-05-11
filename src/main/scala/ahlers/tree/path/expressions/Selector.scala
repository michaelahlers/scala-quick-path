package ahlers.tree.path.expressions

import ahlers.tree.path.operators.Operator

case class Selector(toOperators: Seq[Operator]) extends Expression {
  val toText: String = toOperators.map(_.toText).mkString
}
