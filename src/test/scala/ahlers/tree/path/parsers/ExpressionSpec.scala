package ahlers.tree.path.parsers

import ahlers.tree.path.expressions.Expression
import ahlers.tree.path.expressions.Selector
import ahlers.tree.path.expressions.diffx.instances._
import ahlers.tree.path.expressions.scalacheck.instances._
import com.softwaremill.diffx.scalatest.DiffShouldMatcher._
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks._
import parsley.Success
import parsley.diffx.instances._

class ExpressionSpec extends AnyWordSpec {

  "Selector" should {
    val parser = expression.selector

    s"""accept sequence of operators""" in {
      forAll { expression: Selector =>
        parser.parse(expression.toText).shouldMatchTo(Success(expression))
      }
    }
  }

  "Expression" should {
    val parser = expression.any
    s"""accept any expression""" in {
      forAll { expression: Expression =>
        parser.parse(expression.toText).shouldMatchTo(Success(expression))
      }
    }
  }

}
