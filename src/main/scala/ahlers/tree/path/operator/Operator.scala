package ahlers.tree.path.operator

import ahlers.tree.path.term.Name
import ahlers.tree.path.term.Wildcard

sealed trait Operator
object Operator {

  sealed trait Anchor extends Operator
  object Anchor {
    case object RootElement extends Anchor {
      val toText: String = "$"
    }

    case object CurrentNode extends Anchor {
      val toText: String = "@"
    }
  }

  case object DeepScan extends Operator {
    val toText: String = ".."
  }

  sealed trait DotNotatedChild extends Operator {
    def toText: String
  }
  object DotNotatedChild {
    case class MatchingName(toName: Name) extends DotNotatedChild {
      override val toText: String = s".${toName.toText}"
    }

    case object MatchingWildcard extends DotNotatedChild {
      val toWildcard: Wildcard.type = Wildcard
      override val toText: String   = s".${toWildcard.toText}"
    }
  }

}
