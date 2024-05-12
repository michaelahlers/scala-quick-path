package ahlers.tree.path.filterOperators.scalacheck

import ahlers.tree.path.filterOperators.FilterOperator
import ahlers.tree.path.filterOperators.IsEqual
import ahlers.tree.path.filterOperators.algebra.IsFilterOperator
import ahlers.tree.path.terms.Index
import ahlers.tree.path.terms.Name
import ahlers.tree.path.terms.scalacheck.instances._
import magnolify.scalacheck.semiauto._
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen

object instances {

  implicit val arbEqual: Arbitrary[IsEqual.type] = Arbitrary(Gen.const(IsEqual))

  implicit val arbFilterOperator: Arbitrary[FilterOperator] = Arbitrary(Gen.oneOf(
    arbitrary[IsEqual.type],
    arbitrary[IsEqual.type],
  ))

}
