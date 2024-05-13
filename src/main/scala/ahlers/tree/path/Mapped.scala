package ahlers.tree.path

case class Mapped()
object Mapped {

  case class Path(toSegments: Seq[Path.Segment])

  object Path {
    sealed trait Segment

    object Segment {
      case class Key(toKey: String)  extends Segment
      case class Index(toIndex: Int) extends Segment
    }
  }

  sealed trait Value

  object Value {
    case class Text(toText: String)              extends Value
    case class Integer(toBigInt: BigInt)         extends Value
    case class Decimal(toBigDecimal: BigDecimal) extends Value
    case class Boolean(toBoolean: Boolean)       extends Value
  }

}
