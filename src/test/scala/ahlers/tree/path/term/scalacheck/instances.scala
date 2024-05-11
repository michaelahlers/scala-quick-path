package ahlers.tree.path.term.scalacheck

import ahlers.tree.path.term.Name
import ahlers.tree.path.term.Wildcard
import org.scalacheck.Arbitrary
import org.scalacheck.Gen

object instances {

  implicit val arbWildcard: Arbitrary[Wildcard.type] = Arbitrary(Gen.const(Wildcard))

  implicit val arbName: Arbitrary[Name] = Arbitrary(for {
    head <- Gen.alphaNumChar
    tail <- Gen.alphaNumStr
  } yield Name(s"$head$tail"))

}
