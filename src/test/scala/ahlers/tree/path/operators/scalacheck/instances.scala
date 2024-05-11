package ahlers.tree.path.operators.scalacheck

import ahlers.tree.path.operators.ArrayIndexes
import ahlers.tree.path.operators.ArraySlice
import ahlers.tree.path.operators.BracketNotatedChildren
import ahlers.tree.path.operators.CurrentNode
import ahlers.tree.path.operators.DeepScan
import ahlers.tree.path.operators.DotNotatedChild
import ahlers.tree.path.operators.Operator
import ahlers.tree.path.operators.RootElement
import ahlers.tree.path.operators.Wildcard
import ahlers.tree.path.operators.algebra.IsOperator
import ahlers.tree.path.terms.Name
import ahlers.tree.path.terms.scalacheck.instances._
import magnolify.scalacheck.semiauto._
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen

object instances {

  implicit val arbCurrentNode: Arbitrary[CurrentNode.type] = Arbitrary(Gen.const(CurrentNode))

  implicit val arbRootElement: Arbitrary[RootElement.type] = Arbitrary(Gen.const(RootElement))

  implicit val arbWildcard: Arbitrary[Wildcard.type] = Arbitrary(Gen.const(Wildcard))

  implicit val arbDeepScan: Arbitrary[DeepScan.type] = Arbitrary(Gen.const(DeepScan))

  implicit val arbDotNotatedChildMatchingName: Arbitrary[DotNotatedChild.MatchingName]              = Arbitrary(arbitrary[Name].map(DotNotatedChild.MatchingName))
  implicit val arbDotNotatedChildMatchingWildcard: Arbitrary[DotNotatedChild.MatchingWildcard.type] = ArbitraryDerivation[DotNotatedChild.MatchingWildcard.type]
  implicit val arbDotNotatedChild: Arbitrary[DotNotatedChild]                                       = ArbitraryDerivation[DotNotatedChild]

  implicit val arbBracketNotatedChildren: Arbitrary[BracketNotatedChildren] = {
    import BracketNotatedChildren.Child
    implicit val arbChild: Arbitrary[Child] = ArbitraryDerivation[Child]
    ArbitraryDerivation[BracketNotatedChildren]
  }

  implicit val arbArrayIndexes: Arbitrary[ArrayIndexes] = ArbitraryDerivation[ArrayIndexes]

  implicit val arbArraySliceLeftBounded: Arbitrary[ArraySlice.LeftBounded]   = ArbitraryDerivation[ArraySlice.LeftBounded]
  implicit val arbArraySliceRightBounded: Arbitrary[ArraySlice.RightBounded] = ArbitraryDerivation[ArraySlice.RightBounded]
  implicit val arbArraySliceBounded: Arbitrary[ArraySlice.Bounded]           = ArbitraryDerivation[ArraySlice.Bounded]
  implicit val arbArraySlice: Arbitrary[ArraySlice]                          = ArbitraryDerivation[ArraySlice]

  implicit val arbOperator: Arbitrary[Operator] = Arbitrary(Gen.oneOf(
    arbitrary[CurrentNode.type],
    arbitrary[RootElement.type],
    arbitrary[Wildcard.type],
    arbitrary[DeepScan.type],
    arbitrary[DotNotatedChild],
    arbitrary[BracketNotatedChildren],
    arbitrary[ArrayIndexes],
    arbitrary[ArraySlice],
  ))

}
