package ahlers.tree.path.parsers

import ahlers.tree.path.filterOperators.HasSize
import ahlers.tree.path.filterOperators.IsAnyOf
import ahlers.tree.path.filterOperators.IsEmpty
import ahlers.tree.path.filterOperators.IsEqualTo
import ahlers.tree.path.filterOperators.IsGreaterThan
import ahlers.tree.path.filterOperators.IsGreaterThanOrEqualTo
import ahlers.tree.path.filterOperators.IsIn
import ahlers.tree.path.filterOperators.IsLessThan
import ahlers.tree.path.filterOperators.IsLessThanOrEqualTo
import ahlers.tree.path.filterOperators.IsMatchOf
import ahlers.tree.path.filterOperators.IsNoneOf
import ahlers.tree.path.filterOperators.IsNotEqualTo
import ahlers.tree.path.filterOperators.IsNotIn
import ahlers.tree.path.filterOperators.IsSubsetOf
import ahlers.tree.path.filterOperators.diffx.instances._
import ahlers.tree.path.filterOperators.scalacheck.instances._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher._
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks._
import parsley.Failure
import parsley.Success
import parsley.diffx.instances._

class FilterOperatorSpec extends AnyWordSpec {

  "IsEqualTo" should {
    val parser  = filterOperator.isEqualTo
    val pattern = "^==$".r

    s"""accept $pattern""" in {
      val filterOperator = IsEqualTo
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsNotEqualTo" should {
    val parser  = filterOperator.isNotEqualTo
    val pattern = "^!=$".r

    s"""accept $pattern""" in {
      val filterOperator = IsNotEqualTo
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsLessThan" should {
    val parser  = filterOperator.isLessThan
    val pattern = "^<$".r

    s"""accept $pattern""" in {
      val filterOperator = IsLessThan
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsLessThanOrEqualTo" should {
    val parser  = filterOperator.isLessThanOrEqualTo
    val pattern = "^<=$".r

    s"""accept $pattern""" in {
      val filterOperator = IsLessThanOrEqualTo
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsGreaterThan" should {
    val parser  = filterOperator.isGreaterThan
    val pattern = "^>$".r

    s"""accept $pattern""" in {
      val filterOperator = IsGreaterThan
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsGreaterThanOrEqualTo" should {
    val parser  = filterOperator.isGreaterThanOrEqualTo
    val pattern = "^>=$".r

    s"""accept $pattern""" in {
      val filterOperator = IsGreaterThanOrEqualTo
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsMatchOf" should {
    val parser  = filterOperator.isMatchOf
    val pattern = "^~=$".r

    s"""accept $pattern""" in {
      val filterOperator = IsMatchOf
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsIn" should {
    val parser  = filterOperator.isIn
    val pattern = "^in$".r

    s"""accept $pattern""" in {
      val filterOperator = IsIn
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsNotIn" should {
    val parser  = filterOperator.isNotIn
    val pattern = "^nin$".r

    s"""accept $pattern""" in {
      val filterOperator = IsNotIn
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsSubsetOf" should {
    val parser  = filterOperator.isSubsetOf
    val pattern = "^subsetof$".r

    s"""accept $pattern""" in {
      val filterOperator = IsSubsetOf
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsAnyOf" should {
    val parser  = filterOperator.isAnyOf
    val pattern = "^anyof$".r

    s"""accept $pattern""" in {
      val filterOperator = IsAnyOf
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsNoneOf" should {
    val parser  = filterOperator.isNoneOf
    val pattern = "^noneof$".r

    s"""accept $pattern""" in {
      val filterOperator = IsNoneOf
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "HasSize" should {
    val parser  = filterOperator.hasSize
    val pattern = "^size$".r

    s"""accept $pattern""" in {
      val filterOperator = HasSize
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
    }

    "reject else" in {
      forAll { input: String =>
        whenever(!pattern.matches(input)) {
          parser.parse(input).shouldBe(a[Failure[_]])
        }
      }
    }
  }

  "IsEmpty" should {
    val parser  = filterOperator.isEmpty
    val pattern = "^empty$".r

    s"""accept $pattern""" in {
      val filterOperator = IsEmpty
      parser.parse(filterOperator.toText).shouldMatchTo(Success(filterOperator))
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
