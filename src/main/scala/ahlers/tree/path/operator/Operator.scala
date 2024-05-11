package ahlers.tree.path.operator

sealed trait Operator
object Operator {

  sealed trait Anchor
  object Anchor {
    case object RootElement extends Operator with Anchor {
      val toText: String = "$"
    }

    case object CurrentNode extends Operator with Anchor {
      val toText: String = "@"
    }
  }

  case object DeepScan extends Operator {
    val toText: String = ".."
  }

}
