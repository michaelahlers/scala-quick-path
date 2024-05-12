package ahlers.tree.path.filterOperators.scalacheck

import ahlers.tree.path.filterOperators._
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen

object instances {

  implicit val arbIsEqual: Arbitrary[IsEqualTo.type]                         = Arbitrary(Gen.const(IsEqualTo))
  implicit val arbIsNotEqual: Arbitrary[IsNotEqualTo.type]                   = Arbitrary(Gen.const(IsNotEqualTo))
  implicit val arbIsLessThan: Arbitrary[IsLessThan.type]                     = Arbitrary(Gen.const(IsLessThan))
  implicit val arbIsLessThanOrEqualTo: Arbitrary[IsLessThanOrEqualTo.type]       = Arbitrary(Gen.const(IsLessThanOrEqualTo))
  implicit val arbIsGreaterThan: Arbitrary[IsGreaterThan.type]               = Arbitrary(Gen.const(IsGreaterThan))
  implicit val arbIsGreaterThanOrEqualTo: Arbitrary[IsGreaterThanOrEqualTo.type] = Arbitrary(Gen.const(IsGreaterThanOrEqualTo))
  implicit val arbIsMatchOf: Arbitrary[IsMatchOf.type]                       = Arbitrary(Gen.const(IsMatchOf))
  implicit val arbIsIn: Arbitrary[IsIn.type]                                 = Arbitrary(Gen.const(IsIn))
  implicit val arbIsNotIn: Arbitrary[IsNotIn.type]                           = Arbitrary(Gen.const(IsNotIn))
  implicit val arbIsSubsetOf: Arbitrary[IsSubsetOf.type]                     = Arbitrary(Gen.const(IsSubsetOf))
  implicit val arbIsAnyOf: Arbitrary[IsAnyOf.type]                           = Arbitrary(Gen.const(IsAnyOf))
  implicit val arbIsNoneOf: Arbitrary[IsNoneOf.type]                         = Arbitrary(Gen.const(IsNoneOf))
  implicit val arbHasSize: Arbitrary[HasSize.type]                           = Arbitrary(Gen.const(HasSize))
  implicit val arbIsEmpty: Arbitrary[IsEmpty.type]                           = Arbitrary(Gen.const(IsEmpty))

  implicit val arbFilterOperator: Arbitrary[FilterOperator] = Arbitrary(Gen.oneOf(
    arbitrary[IsEqualTo.type],
    arbitrary[IsNotEqualTo.type],
    arbitrary[IsLessThan.type],
    arbitrary[IsLessThanOrEqualTo.type],
    arbitrary[IsGreaterThan.type],
    arbitrary[IsGreaterThanOrEqualTo.type],
    arbitrary[IsMatchOf.type],
    arbitrary[IsIn.type],
    arbitrary[IsNotIn.type],
    arbitrary[IsSubsetOf.type],
    arbitrary[IsAnyOf.type],
    arbitrary[IsNoneOf.type],
    arbitrary[HasSize.type],
    arbitrary[IsEmpty.type],
  ))

}
