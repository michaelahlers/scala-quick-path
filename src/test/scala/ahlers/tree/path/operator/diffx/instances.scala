package ahlers.tree.path.operator.diffx

import ahlers.tree.path.operator.Operator.Anchor
import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import ahlers.tree.path.operator.Operator.DeepScan
import com.softwaremill.diffx.Diff

object instances {

  implicit val diffCurrentNode: Diff[CurrentNode.type] = Diff.derived
  implicit val diffDeepScan: Diff[DeepScan.type]       = Diff.derived
  implicit val diffRootElement: Diff[RootElement.type] = Diff.derived
  implicit val diffAnchor: Diff[Anchor]                = Diff.derived

}
