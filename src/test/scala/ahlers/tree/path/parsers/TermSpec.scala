package ahlers.tree.path.parsers

import ahlers.tree.path.terms.Index
import ahlers.tree.path.terms.Name
import ahlers.tree.path.terms.diffx.instances._
import ahlers.tree.path.terms.scalacheck.instances._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher.convertToAnyShouldMatcher
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks._
import parsley.Failure
import parsley.Success
import parsley.diffx.instances._

class TermSpec extends AnyWordSpec {

  "Index" should {
    val parser  = term.index
    val pattern = "^[0-9]+$".r

    s"""accept $pattern""" in {
      forAll { index: Index =>
        parser.parse(index.toText).shouldMatchTo(Success(index))
      }
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "Name" should {
    val parser  = term.name
    val pattern = "^[a-zA-Z0-9]+$".r

    s"""accept $pattern""" in {
      forAll { name: Name =>
        parser.parse(name.toText).shouldMatchTo(Success(name))
      }
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }
}
