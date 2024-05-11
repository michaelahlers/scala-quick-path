package ahlers.tree.path.parser

import ahlers.tree.path.operator.Operator.{ArrayIndexes, ArraySlice, BracketNotatedChildren, CurrentNode, DeepScan, DotNotatedChild, RootElement, Wildcard}
import ahlers.tree.path.parser.term.index
import ahlers.tree.path.parser.term.name
import parsley.Parsley
import parsley.Parsley.atomic
import parsley.character.char
import parsley.character.string
import parsley.character.whitespaces
import parsley.combinator.sepBy

object operator {

  val currentNode: Parsley[CurrentNode.type] = char('@').as(CurrentNode)

  val rootElement: Parsley[RootElement.type] = char('$').as(RootElement)

  val wildcard: Parsley[Wildcard.type] = char('*').as(Wildcard)

  val deepScan: Parsley[DeepScan.type] = string("..").as(DeepScan)

  val dotNotatedChildMatchingName: Parsley[DotNotatedChild.MatchingName]              = (char('.') *> name).map(DotNotatedChild.MatchingName)
  val dotNotatedChildMatchingWildcard: Parsley[DotNotatedChild.MatchingWildcard.type] = (char('.') *> wildcard).as(DotNotatedChild.MatchingWildcard)
  val dotNotatedChild: Parsley[DotNotatedChild]                                       = atomic(dotNotatedChildMatchingName) | atomic(dotNotatedChildMatchingWildcard)

  val bracketNotatedChildren: Parsley[BracketNotatedChildren] = {
    import BracketNotatedChildren.Child
    val childMatchingName: Parsley[Child.MatchingName]              = (char('\'') *> name <* char('\'')).map(Child.MatchingName)
    val childMatchingWildcard: Parsley[Child.MatchingWildcard.type] = wildcard.as(Child.MatchingWildcard)
    (char('[') *> sepBy(atomic(childMatchingName) | atomic(childMatchingWildcard), whitespaces *> char(',') <* whitespaces) <* char(']')).map(BracketNotatedChildren(_))
  }

  val arrayIndexes: Parsley[ArrayIndexes] = (char('[') *> sepBy(index, whitespaces *> char(',') <* whitespaces) <* char(']')).map(ArrayIndexes)

  val arraySliceLeftBounded: Parsley[ArraySlice.LeftBounded]   = (char('[') *> index <~ char(':') <* char(']')).map(ArraySlice.LeftBounded)
  val arraySliceRightBounded: Parsley[ArraySlice.RightBounded] = (char('[') *> char(':') ~> index <~ char(']')).map(ArraySlice.RightBounded)
  val arraySliceBounded: Parsley[ArraySlice.Bounded]           = (char('[') *> index <~> char(':') *> index <~ char(']')).map((ArraySlice.Bounded(_, _)).tupled)
  val arraySlice: Parsley[ArraySlice]                          = atomic(arraySliceLeftBounded) | atomic(arraySliceRightBounded) | atomic(arraySliceBounded)

}
