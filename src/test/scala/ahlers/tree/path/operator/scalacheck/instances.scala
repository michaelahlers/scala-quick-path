package ahlers.tree.path.operator.scalacheck

import ahlers.tree.path.operator.Operator.Anchor
import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import ahlers.tree.path.operator.Operator.DeepScan
import magnolify.scalacheck.semiauto._
import org.scalacheck.Arbitrary
import org.scalacheck.Gen

object instances {

  implicit val arbCurrentNode: Arbitrary[CurrentNode.type] = Arbitrary(Gen.const(CurrentNode))
  implicit val arbDeepScan: Arbitrary[DeepScan.type]       = Arbitrary(Gen.const(DeepScan))
  implicit val arbRootElement: Arbitrary[RootElement.type] = Arbitrary(Gen.const(RootElement))
  implicit val arbAnchor: Arbitrary[Anchor]                = ArbitraryDerivation[Anchor]

}
