package ahlers.tree.path.operators

import ahlers.tree.path.terms.Name

sealed trait DotNotatedChild extends Operator

object DotNotatedChild {
  case class MatchingName(toName: Name) extends DotNotatedChild {
    val toText: String = s".${toName.toText}"
  }

  case object MatchingWildcard extends DotNotatedChild {
    val toWildcard: Wildcard.type = Wildcard
    val toText: String            = s".${toWildcard.toText}"
  }
}
