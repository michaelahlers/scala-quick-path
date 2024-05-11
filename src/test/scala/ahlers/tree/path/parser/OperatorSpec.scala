package ahlers.tree.path.parser

import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import ahlers.tree.path.operator.diffx.instances._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher.convertToAnyShouldMatcher
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks._
import parsley.Failure
import parsley.Success
import parsley.diffx.instances._

class OperatorSpec extends AnyWordSpec {

  "CurrentNode" should {
    """accept "$"""" in {
      operator.currentNode.parse("@").shouldMatchTo(Success(CurrentNode))
    }

    "reject else" in {
      forAll { input: String =>
        whenever("@" != input) {
          term.wildcard.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "RootElement" should {
    """accept "$"""" in {
      operator.rootElement.parse("$").shouldMatchTo(Success(RootElement))
    }

    "reject else" in {
      forAll { input: String =>
        whenever("$" != input) {
          term.wildcard.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

}
