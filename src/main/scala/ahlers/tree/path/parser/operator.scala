package ahlers.tree.path.parser

import ahlers.tree.path.operator.Operator.Anchor
import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import ahlers.tree.path.operator.Operator.DeepScan
import ahlers.tree.path.operator.Operator.DotNotatedChild
import ahlers.tree.path.parser.term.name
import ahlers.tree.path.parser.term.wildcard
import parsley.Parsley
import parsley.character.char
import parsley.character.string

object operator {

  val currentNode: Parsley[CurrentNode.type] = char('@').as(CurrentNode)
  val rootElement: Parsley[RootElement.type] = char('$').as(RootElement)
  val anchor: Parsley[Anchor]                = currentNode | rootElement

  val deepScan: Parsley[DeepScan.type] = string("..").as(DeepScan)

  val dotNotatedChildMatchingName: Parsley[DotNotatedChild.MatchingName]              = (char('.') *> name).map(DotNotatedChild.MatchingName)
  val dotNotatedChildMatchingWildcard: Parsley[DotNotatedChild.MatchingWildcard.type] = (char('.') *> wildcard).as(DotNotatedChild.MatchingWildcard)
  val dotNotatedChild: Parsley[DotNotatedChild]                                       = char('.') *> (name.map(DotNotatedChild.MatchingName) | wildcard.as(DotNotatedChild.MatchingWildcard))

}
