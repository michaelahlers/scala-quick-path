package ahlers.tree.path.parsers

import ahlers.tree.path.filterOperators.IsEqualTo
import ahlers.tree.path.filterOperators.IsNotEqualTo
import parsley.Parsley
import parsley.character.string

object filterOperator {

  val isEqual: Parsley[IsEqualTo.type]       = string("==").as(IsEqualTo)
  val isNotEqual: Parsley[IsNotEqualTo.type] = string("!=").as(IsNotEqualTo)

}
