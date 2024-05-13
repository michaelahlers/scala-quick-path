package ahlers.tree.path.operators

import ahlers.tree.path.terms.Index

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
