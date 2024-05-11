package ahlers.tree.path.operators

import ahlers.tree.path.terms.Index
import ahlers.tree.path.terms.Name

trait Operator {
  def toText: String
}

case object RootElement extends Operator {
  override val toText: String = "$"
}

case object CurrentNode extends Operator {
  override val toText: String = "@"
}

case object Wildcard extends Operator {
  override val toText: String = "*"
}

case object DeepScan extends Operator {
  override val toText: String = ".."
}

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

case class ArrayIndexes(toIndexes: Seq[Index]) extends Operator {
  override val toText: String = toIndexes.map(_.toText).mkString("[", ",", "]")
}

sealed trait ArraySlice extends Operator
object ArraySlice {
  case class LeftBounded(start: Index) extends ArraySlice {
    val toText: String = s"[${start.toText}:]"
  }
  case class RightBounded(end: Index) extends ArraySlice {
    val toText: String = s"[:${end.toText}]"
  }
  case class Bounded(start: Index, end: Index) extends ArraySlice {
    val toText: String = s"[${start.toText}:${end.toText}]"
  }
}
