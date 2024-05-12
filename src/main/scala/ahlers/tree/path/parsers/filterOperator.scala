package ahlers.tree.path.parsers

import ahlers.tree.path.filterOperators.HasSize
import ahlers.tree.path.filterOperators.IsAnyOf
import ahlers.tree.path.filterOperators.IsEqualTo
import ahlers.tree.path.filterOperators.IsIn
import ahlers.tree.path.filterOperators.IsLessThan
import ahlers.tree.path.filterOperators.IsMatchOf
import ahlers.tree.path.filterOperators.IsNoneOf
import ahlers.tree.path.filterOperators.IsNotEqualTo
import ahlers.tree.path.filterOperators.IsNotIn
import ahlers.tree.path.filterOperators.IsSubsetOf
import parsley.Parsley
import parsley.character.char
import parsley.character.string

object filterOperator {

  val isEqual: Parsley[IsEqualTo.type]               = string("==").as(IsEqualTo)
  val isNotEqual: Parsley[IsNotEqualTo.type]         = string("!=").as(IsNotEqualTo)
  val isLessThan: Parsley[IsLessThan.type]           = char('<').as(IsLessThan)
  val isLessThanOrEqual: Parsley[IsLessThan.type]    = string("<=").as(IsLessThan)
  val isGreaterThan: Parsley[IsLessThan.type]        = char('>').as(IsLessThan)
  val isGreaterThanOrEqual: Parsley[IsLessThan.type] = string(">=").as(IsLessThan)
  val isMatchOf: Parsley[IsMatchOf.type]             = string("=~").as(IsMatchOf)
  val isIn: Parsley[IsIn.type]                       = string("in").as(IsIn)
  val isNotIn: Parsley[IsNotIn.type]                 = string("nin").as(IsNotIn)
  val isSubsetOf: Parsley[IsSubsetOf.type]           = string("subsetof").as(IsSubsetOf)
  val isAnyOf: Parsley[IsAnyOf.type]                 = string("anyof").as(IsAnyOf)
  val isNoneOf: Parsley[IsNoneOf.type]               = string("noneof").as(IsNoneOf)
  val hasSize: Parsley[HasSize.type]                 = string("size").as(HasSize)
  val isEmpty: Parsley[IsEqualTo.type]               = string("empty").as(IsEqualTo)

}
