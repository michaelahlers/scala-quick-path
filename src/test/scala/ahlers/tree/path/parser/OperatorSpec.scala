package ahlers.tree.path.parser

import ahlers.tree.path.operator.Operator.Anchor
import ahlers.tree.path.operator.Operator.Anchor.CurrentNode
import ahlers.tree.path.operator.Operator.Anchor.RootElement
import ahlers.tree.path.operator.Operator.DeepScan
import ahlers.tree.path.operator.Operator.DotNotatedChild
import ahlers.tree.path.operator.diffx.instances._
import ahlers.tree.path.operator.scalacheck.instances._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher.convertToAnyShouldMatcher
import org.scalatest.Checkpoints.Checkpoint
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks._
import parsley.Failure
import parsley.Success
import parsley.diffx.instances._

class OperatorSpec extends AnyWordSpec {

  "Anchor" should {
    val parser  = operator.anchor
    val pattern = "^[@\\$]$".r

    s"""accept $pattern""" in {
      val checkpoint = new Checkpoint()

      checkpoint(parser.parse(CurrentNode.toText).shouldMatchTo(Success(CurrentNode: Anchor)))
      checkpoint(parser.parse(RootElement.toText).shouldMatchTo(Success(RootElement: Anchor)))

      checkpoint.reportAll()
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "CurrentNode" should {
    val parser  = operator.currentNode
    val pattern = "^@$".r

    s"""accept $pattern""" in {
      parser.parse(CurrentNode.toText).shouldMatchTo(Success(CurrentNode))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "RootElement" should {
    val parser  = operator.rootElement
    val pattern = "^\\$$".r

    s"""accept $pattern""" in {
      parser.parse(RootElement.toText).shouldMatchTo(Success(RootElement))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "DeepScan" should {
    val parser  = operator.deepScan
    val pattern = "^\\.\\.$".r

    s"""accept $pattern""" in {
      parser.parse(DeepScan.toText).shouldMatchTo(Success(DeepScan))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "DotNotatedChild.MatchingName" should {
    val parser  = operator.dotNotatedChildMatchingName
    val pattern = "^\\.[a-zA-Z0-9]+$".r

    s"""accept $pattern""" in {
      forAll { matchingName: DotNotatedChild.MatchingName =>
        parser.parse(matchingName.toText).shouldMatchTo(Success(matchingName))
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

  "DotNotatedChild.MatchingWildcard" should {
    val parser  = operator.dotNotatedChildMatchingWildcard
    val pattern = "^\\.\\*$".r

    s"""accept $pattern""" in {
      parser.parse(DotNotatedChild.MatchingWildcard.toText).shouldMatchTo(Success(DotNotatedChild.MatchingWildcard))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "DotNotatedChild" should {
    val parser  = operator.dotNotatedChild
    val pattern = "^\\.([a-zA-Z0-9]+|\\*)$".r

    s"""accept $pattern""" in {
      forAll { dotNotatedChild: DotNotatedChild =>
        println(dotNotatedChild.toText)
        parser.parse(dotNotatedChild.toText).shouldMatchTo(Success(dotNotatedChild))
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
