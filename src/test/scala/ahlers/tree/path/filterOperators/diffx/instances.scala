package ahlers.tree.path.filterOperators.diffx

import ahlers.tree.path.filterOperators.{FilterOperator, IsEqualTo, IsNotEqualTo}
import ahlers.tree.path.filterOperators.algebra.IsFilterOperator
import ahlers.tree.path.terms.diffx.instances._
import com.softwaremill.diffx.Diff

object instances {

  implicit val diffIsEqual: Diff[IsEqualTo.type] = Diff.derived

  implicit val diffIsNotEqual: Diff[IsNotEqualTo.type] = Diff.derived

  implicit val diffFilterOperator: Diff[FilterOperator] = {
    implicit val diffIsFilterOperator: Diff[IsFilterOperator] = {
      import IsFilterOperator.OfUnknown
      implicit val diffOfUnknown: Diff[OfUnknown] = Diff.useEquals
      Diff.derived
    }

    Diff[IsFilterOperator].contramap(IsFilterOperator(_))
  }

}
