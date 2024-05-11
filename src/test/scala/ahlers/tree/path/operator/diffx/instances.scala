package ahlers.tree.path.operator.diffx

import ahlers.tree.path.operator.Operator.Anchor
import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import ahlers.tree.path.operator.Operator.DeepScan
import ahlers.tree.path.operator.Operator.DotNotatedChild
import ahlers.tree.path.term.diffx.instances._
import com.softwaremill.diffx.Diff

object instances {

  implicit val diffCurrentNode: Diff[CurrentNode.type] = Diff.derived
  implicit val diffRootElement: Diff[RootElement.type] = Diff.derived
  implicit val diffAnchor: Diff[Anchor]                = Diff.derived

  implicit val diffDeepScan: Diff[DeepScan.type] = Diff.derived

  implicit val diffDotNotatedChildMatchingName: Diff[DotNotatedChild.MatchingName]              = Diff.derived
  implicit val diffDotNotatedChildMatchingWildcard: Diff[DotNotatedChild.MatchingWildcard.type] = Diff.derived
  implicit val diffDotNotatedChild: Diff[DotNotatedChild]                                       = Diff.derived

}
