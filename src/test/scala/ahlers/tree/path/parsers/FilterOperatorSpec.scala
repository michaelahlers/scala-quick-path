package ahlers.tree.path.parsers

import ahlers.tree.path.filterOperators.IsEqualTo
import ahlers.tree.path.filterOperators.IsNotEqualTo
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

  "IsEqual" should {
    val parser  = filterOperator.isEqual
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

  "IsNotEqual" should {
    val parser  = filterOperator.isNotEqual
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

}
