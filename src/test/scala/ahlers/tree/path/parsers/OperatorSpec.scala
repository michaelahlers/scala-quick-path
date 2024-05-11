package ahlers.tree.path.parsers

import ahlers.tree.path.operators._
import ahlers.tree.path.operators.diffx.instances._
import ahlers.tree.path.operators.scalacheck.instances._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher._
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
      val operator = RootElement
      parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      val operator = CurrentNode
      parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      val operator = Wildcard
      parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      val operator = DeepScan
      parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      forAll { operator: DotNotatedChild.MatchingName =>
        parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      val operator = DotNotatedChild.MatchingWildcard
      parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      forAll { operator: DotNotatedChild =>
        parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      forAll { operator: BracketNotatedChildren =>
        parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      forAll { operator: ArrayIndexes =>
        parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      forAll { operator: ArraySlice.LeftBounded =>
        parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      forAll { operator: ArraySlice.RightBounded =>
        parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      forAll { operator: ArraySlice.Bounded =>
        parser.parse(operator.toText).shouldMatchTo(Success(operator))
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
      forAll { operator: ArraySlice =>
        parser.parse(operator.toText).shouldMatchTo(Success(operator))
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

  "Operator" should {
    val parser = operator.any

    """accept any operator""" in {
      forAll { operator: Operator =>
        parser.parse(operator.toText).shouldMatchTo(Success(operator))
      }
    }
  }

}
