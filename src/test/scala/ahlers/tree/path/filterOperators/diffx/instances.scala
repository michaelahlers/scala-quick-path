package ahlers.tree.path.filterOperators.diffx

import ahlers.tree.path.filterOperators._
import ahlers.tree.path.filterOperators.algebra.IsFilterOperator
import com.softwaremill.diffx.Diff

object instances {

  implicit val diffIsEqual: Diff[IsEqualTo.type]                         = Diff.derived
  implicit val diffIsNotEqual: Diff[IsNotEqualTo.type]                   = Diff.derived
  implicit val diffIsLessThan: Diff[IsLessThan.type]                     = Diff.derived
  implicit val diffIsLessThanOrEqual: Diff[IsLessThanOrEqual.type]       = Diff.derived
  implicit val diffIsGreaterThan: Diff[IsGreaterThan.type]               = Diff.derived
  implicit val diffIsGreaterThanOrEqual: Diff[IsGreaterThanOrEqual.type] = Diff.derived
  implicit val diffIsMatchOf: Diff[IsMatchOf.type]                       = Diff.derived
  implicit val diffIsIn: Diff[IsIn.type]                                 = Diff.derived
  implicit val diffIsNotIn: Diff[IsNotIn.type]                           = Diff.derived
  implicit val diffIsSubsetOf: Diff[IsSubsetOf.type]                     = Diff.derived
  implicit val diffIsAnyOf: Diff[IsAnyOf.type]                           = Diff.derived
  implicit val diffIsNoneOf: Diff[IsNoneOf.type]                         = Diff.derived
  implicit val diffHasSize: Diff[HasSize.type]                           = Diff.derived
  implicit val diffIsEmpty: Diff[IsEmpty.type]                           = Diff.derived

  implicit val diffFilterOperator: Diff[FilterOperator] = {
    implicit val diffIsFilterOperator: Diff[IsFilterOperator] = {
      import IsFilterOperator.OfUnknown
      implicit val diffOfUnknown: Diff[OfUnknown] = Diff.useEquals
      Diff.derived
    }

    Diff[IsFilterOperator].contramap(IsFilterOperator(_))
  }

}
