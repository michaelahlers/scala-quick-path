package ahlers.tree.path.parser

import ahlers.tree.path.operator.Operator.Anchor
import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import ahlers.tree.path.operator.Operator.DeepScan
import ahlers.tree.path.operator.diffx.instances._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher.convertToAnyShouldMatcher
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks._
import parsley.Failure
import parsley.Success
import parsley.diffx.instances._

class OperatorSpec extends AnyWordSpec {

  "Anchor" should {
    val parser = operator.anchor

    """accept "$"""" in {
      parser.parse("$").shouldMatchTo(Success(RootElement: Anchor))
    }

    """accept "@"""" in {
      parser.parse("@").shouldMatchTo(Success(CurrentNode: Anchor))
    }
  }

  "CurrentNode" should {
    val parser = operator.currentNode

    """accept "$"""" in {
      parser.parse("@").shouldMatchTo(Success(CurrentNode))
    }

    "reject else" in {
      forAll { input: String =>
        whenever("@" != input) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "RootElement" should {
    val parser = operator.rootElement

    """accept "$"""" in {
      parser.parse("$").shouldMatchTo(Success(RootElement))
    }

    "reject else" in {
      forAll { input: String =>
        whenever("$" != input) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "DeepScan" should {
    val parser = operator.deepScan

    """accept ".."""" in {
      parser.parse("..").shouldMatchTo(Success(DeepScan))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(".." != input) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

}
