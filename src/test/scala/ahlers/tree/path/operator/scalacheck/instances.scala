package ahlers.tree.path.operator.scalacheck

import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import org.scalacheck.Arbitrary
import org.scalacheck.Gen

object instances {

  implicit val arbCurrentNode: Arbitrary[CurrentNode.type] = Arbitrary(Gen.const(CurrentNode))
  implicit val arbRootElement: Arbitrary[RootElement.type] = Arbitrary(Gen.const(RootElement))

}
