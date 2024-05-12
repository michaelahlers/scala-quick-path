package ahlers.tree.path.parsers

import ahlers.tree.path.filterOperators.IsEqual
import parsley.Parsley
import parsley.character.string

object filterOperator {

  val isEqual: Parsley[IsEqual.type] = string("==").as(IsEqual)

}
