package ahlers.tree.path.parser

import ahlers.tree.path.operator.Operator.Anchor
import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import ahlers.tree.path.operator.Operator.DeepScan
import parsley.Parsley
import parsley.character.char
import parsley.character.string

object operator {

  val currentNode: Parsley[CurrentNode.type] = char('@').as(CurrentNode)
  val rootElement: Parsley[RootElement.type] = char('$').as(RootElement)
  val anchor: Parsley[Anchor]                = currentNode | rootElement

  val deepScan: Parsley[DeepScan.type] = string("..").as(DeepScan)

}
