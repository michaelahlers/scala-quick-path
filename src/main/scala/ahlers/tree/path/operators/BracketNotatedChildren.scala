package ahlers.tree.path.operators

import ahlers.tree.path.terms.Name

case class BracketNotatedChildren(toNames: Seq[BracketNotatedChildren.Child]) extends Operator {
  override val toText: String = toNames.map(_.toText).mkString("[", ",", "]")
}

object BracketNotatedChildren {
  sealed trait Child {
    def toText: String
  }
  object Child {
    case class MatchingName(toName: Name) extends Child {
      val toText: String = s"'${toName.toText}'"
    }

    case object MatchingWildcard extends Child {
      val toWildcard: Wildcard.type = Wildcard
      val toText: String            = s"${toWildcard.toText}"
    }
  }
}
