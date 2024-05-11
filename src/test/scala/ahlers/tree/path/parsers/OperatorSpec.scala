package ahlers.tree.path.parsers

import ahlers.tree.path.operators.Operator._
import ahlers.tree.path.operators.diffx.instances._
import ahlers.tree.path.operators.scalacheck.instances._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher.convertToAnyShouldMatcher
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks._
import parsley.Failure
import parsley.Success
import parsley.diffx.instances._

class OperatorSpec extends AnyWordSpec {

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

  "Wildcard" should {
    val parser  = operator.wildcard
    val pattern = "^\\*$".r

    s"""accept $pattern""" in {
      parser.parse(Wildcard.toText).shouldMatchTo(Success(Wildcard))
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
      forAll { dotNotatedChild: DotNotatedChild.MatchingName =>
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

  "BracketNotatedChild" should {
    val parser  = operator.bracketNotatedChildren
    val pattern = "^\\[('[a-zA-Z0-9]+'|\\*)\\w*(,\\w*'[a-zA-Z0-9]+'|\\*)\\]$".r

    s"""accept $pattern""" in {
      forAll { bracketNotatedChildren: BracketNotatedChildren =>
        parser.parse(bracketNotatedChildren.toText).shouldMatchTo(Success(bracketNotatedChildren))
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

  "ArrayIndexes" should {
    val parser  = operator.arrayIndexes
    val pattern = "^\\[[0-9]+\\w*(,\\w*[0-9]+)\\]$".r

    s"""accept $pattern""" in {
      forAll { arrayIndexes: ArrayIndexes =>
        parser.parse(arrayIndexes.toText).shouldMatchTo(Success(arrayIndexes))
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

  "ArraySlice.LeftBounded" should {
    val parser  = operator.arraySliceLeftBounded
    val pattern = "^\\[\\d+:\\]$".r

    s"""accept $pattern""" in {
      forAll { arraySlice: ArraySlice.LeftBounded =>
        parser.parse(arraySlice.toText).shouldMatchTo(Success(arraySlice))
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

  "ArraySlice.RightBounded" should {
    val parser  = operator.arraySliceRightBounded
    val pattern = "^\\[:\\d+\\]$".r

    s"""accept $pattern""" in {
      forAll { arraySlice: ArraySlice.RightBounded =>
        parser.parse(arraySlice.toText).shouldMatchTo(Success(arraySlice))
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

  "ArraySlice.Bounded" should {
    val parser  = operator.arraySliceBounded
    val pattern = "^\\[\\d+:\\d+\\]$".r

    s"""accept $pattern""" in {
      forAll { arraySlice: ArraySlice.Bounded =>
        parser.parse(arraySlice.toText).shouldMatchTo(Success(arraySlice))
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

  "ArraySlice" should {
    val parser  = operator.arraySlice
    val pattern = "^(\\[\\d+:\\]|\\[:\\d+\\]|\\[\\d+:\\d+\\])$".r

    s"""accept $pattern""" in {
      forAll { arraySlice: ArraySlice =>
        parser.parse(arraySlice.toText).shouldMatchTo(Success(arraySlice))
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
