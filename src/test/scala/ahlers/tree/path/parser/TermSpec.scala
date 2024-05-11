package ahlers.tree.path.parser

import ahlers.tree.path.term.Wildcard
import ahlers.tree.path.term.diffx.instances._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher.convertToAnyShouldMatcher
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks._
import parsley.Failure
import parsley.Success
import parsley.diffx.instances._

class TermSpec extends AnyWordSpec {

  "Wildcard" should {
    """accept "*"""" in {
      term.wildcard.parse("*").shouldMatchTo(Success(Wildcard))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(input != "*") {
          term.wildcard.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

}
