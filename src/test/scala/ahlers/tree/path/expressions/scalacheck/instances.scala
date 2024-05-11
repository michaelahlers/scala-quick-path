package ahlers.tree.path.expressions.scalacheck

import ahlers.tree.path.expressions.Expression
import ahlers.tree.path.expressions.Logical
import ahlers.tree.path.expressions.Selector
import ahlers.tree.path.operators.Operator
import ahlers.tree.path.operators.scalacheck.instances._
import magnolify.scalacheck.semiauto._
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen

object instances {

  implicit val arbSelector: Arbitrary[Selector] = Arbitrary(for {
    head <- arbitrary[Operator]
    tail <- arbitrary[Seq[Operator]]
  } yield Selector(head +: tail))

  implicit val arbLogical: Arbitrary[Logical] = ArbitraryDerivation[Logical]

  implicit val arbExpression: Arbitrary[Expression] = Arbitrary(Gen.oneOf(
    arbitrary[Selector],
    arbitrary[Selector],
    // arbitrary[Logical],
  ))

}
