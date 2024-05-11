package ahlers.tree.path.parser

import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import parsley.Parsley
import parsley.character.char

object operator {

  val currentNode: Parsley[CurrentNode.type] = char('@').as(CurrentNode)
  val rootElement: Parsley[RootElement.type] = char('$').as(RootElement)

}
