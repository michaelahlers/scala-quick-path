package ahlers.tree.path.operator.scalacheck

import ahlers.tree.path.operator.Operator.Anchor
import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import ahlers.tree.path.operator.Operator.DeepScan
import ahlers.tree.path.operator.Operator.DotNotatedChild
import ahlers.tree.path.term.Name
import ahlers.tree.path.term.scalacheck.instances._
import magnolify.scalacheck.semiauto._
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen

object instances {

  implicit val arbCurrentNode: Arbitrary[CurrentNode.type] = Arbitrary(Gen.const(CurrentNode))
  implicit val arbRootElement: Arbitrary[RootElement.type] = Arbitrary(Gen.const(RootElement))
  implicit val arbAnchor: Arbitrary[Anchor]                = ArbitraryDerivation[Anchor]

  implicit val arbDeepScan: Arbitrary[DeepScan.type] = Arbitrary(Gen.const(DeepScan))

  implicit val arbDotNotatedChildMatchingName: Arbitrary[DotNotatedChild.MatchingName]              = Arbitrary(arbitrary[Name].map(DotNotatedChild.MatchingName))
  implicit val arbDotNotatedChildMatchingWildcard: Arbitrary[DotNotatedChild.MatchingWildcard.type] = ArbitraryDerivation[DotNotatedChild.MatchingWildcard.type]
  implicit val arbDotNotatedChild: Arbitrary[DotNotatedChild]                                       = ArbitraryDerivation[DotNotatedChild]

}
