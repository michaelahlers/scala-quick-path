package ahlers.tree.path.operator

import ahlers.tree.path.term.Index
import ahlers.tree.path.term.Name

sealed trait Operator
object Operator {

  case object RootElement extends Operator {
    val toText: String = "$"
  }

  case object CurrentNode extends Operator {
    val toText: String = "@"
  }

  case object Wildcard {
    val toText: String = "*"
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

  case class BracketNotatedChildren(toNames: Seq[BracketNotatedChildren.Child]) {
    val toText: String = toNames.map(_.toText).mkString("[", ",", "]")
  }
  object BracketNotatedChildren {
    sealed trait Child {
      def toText: String
    }
    object Child {
      case class MatchingName(toName: Name) extends Child {
        override val toText: String = s"'${toName.toText}'"
      }

      case object MatchingWildcard extends Child {
        val toWildcard: Wildcard.type = Wildcard
        override val toText: String   = s"${toWildcard.toText}"
      }
    }
  }

  case class ArrayIndexes(toIndexes: Seq[Index]) {
    val toText: String = toIndexes.map(_.toText).mkString("[", ",", "]")
  }

  sealed trait ArraySlice extends Operator {
    def toText: String
  }
  object ArraySlice {
    case class LeftBounded(start: Index) extends ArraySlice {
      override val toText: String = s"[${start.toText}:]"
    }
    case class RightBounded(end: Index) extends ArraySlice {
      override val toText: String = s"[:${end.toText}]"
    }
    case class Bounded(start: Index, end: Index) extends ArraySlice {
      override val toText: String = s"[${start.toText}:${end.toText}]"
    }
  }

}
