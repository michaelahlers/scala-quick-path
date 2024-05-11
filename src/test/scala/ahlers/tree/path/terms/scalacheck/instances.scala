package ahlers.tree.path.terms.scalacheck

import ahlers.tree.path.terms.Index
import ahlers.tree.path.terms.Name
import org.scalacheck.Arbitrary
import org.scalacheck.Gen

object instances {

  implicit val arbIndex: Arbitrary[Index] = Arbitrary(Gen.posNum[Int].map(Index))

  implicit val arbName: Arbitrary[Name] = Arbitrary(for {
    head <- Gen.alphaNumChar
    tail <- Gen.alphaNumStr
  } yield Name(s"$head$tail"))

}
