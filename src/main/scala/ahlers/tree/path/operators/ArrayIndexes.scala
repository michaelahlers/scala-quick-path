package ahlers.tree.path.operators

import ahlers.tree.path.terms.Index

case class ArrayIndexes(toIndexes: Seq[Index]) extends Operator {
  override val toText: String = toIndexes.map(_.toText).mkString("[", ",", "]")
}
