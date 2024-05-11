package ahlers.tree.path

import ahlers.tree.path.JsonPathParser.Name.Wildcard
import parsley.Parsley
import parsley.Parsley._
import parsley.character._
import parsley.combinator.sepBy

object JsonPathParser extends App {

  val leftParenthesis: Parsley[Char]  = char('(')
  val rightParenthesis: Parsley[Char] = char(')')
  val leftBracket: Parsley[Char]      = char('[')
  val rightBracket: Parsley[Char]     = char(']')
  val comma: Parsley[Char]            = char(',')
  val colon: Parsley[Char]            = char(':')
  val dot: Parsley[Char]              = char('.')
  val singleQuote: Parsley[Char]      = char('\'')

  sealed trait Name
  object Name {
    case object Wildcard extends Name {
      val parser: Parsley[Wildcard.type] = string("*").as(Wildcard)
    }

    case class Literal(toText: String) extends Name
    object Literal {
      val parser: Parsley[Literal] = stringOfSome(satisfy(_.isLetterOrDigit)).map(Literal(_))
    }

    val parser: Parsley[Name] = Wildcard.parser | Literal.parser
  }

  case class Index(toInt: Int)
  object Index {
    val parser: Parsley[Index] = satisfy(_.isDigit).map(_.toInt).map(Index(_))
  }

  sealed trait Operator
  object Operator {

    sealed trait Anchor extends Operator
    object Anchor {

      case object Root extends Anchor {
        val parser: Parsley[Root.type] = char('$').as(Root)
      }

      case object Node extends Anchor {
        val parser: Parsley[Node.type] = char('@').as(Node)
      }

      val parser: Parsley[Anchor] = Root.parser | Node.parser
    }

    case object DeepScan extends Operator {
      val parser: Parsley[DeepScan.type] = string("..").as(DeepScan)
    }

    case class Child(toName: Name) extends Operator
    object Child {
      val parser: Parsley[Child] = dot *> Name.parser.map(Child(_))
    }

    case class ChildSubscript(toNames: Seq[Name]) extends Operator
    object ChildSubscript {
      val parser: Parsley[Operator.ChildSubscript] = {
        val quotedParser: Parsley[Name.Literal] = singleQuote *> Name.Literal.parser <~ singleQuote
        (leftBracket *> sepBy(quotedParser <|> Wildcard.parser, whitespaces *> comma <* whitespaces) <* rightBracket).map(ChildSubscript(_))
      }
    }

    case class IndexSubscript(toIndexes: Seq[Index]) extends Operator
    object IndexSubscript {
      val parser: Parsley[Operator.IndexSubscript] = (leftBracket *> sepBy(Index.parser, comma) <* rightBracket).map(IndexSubscript(_))
    }

    sealed trait ArraySlice extends Operator
    object ArraySlice {
      case class LeftBound(start: Index) extends ArraySlice
      object LeftBound {
        val parser: Parsley[LeftBound] = (leftBracket *> Index.parser <~ colon <* rightBracket).map(LeftBound(_))
      }

      case class RightBound(end: Index) extends ArraySlice
      object RightBound {
        val parser: Parsley[RightBound] = (leftBracket *> colon ~> Index.parser <* rightBracket).map(RightBound(_))
      }

      case class BothBounds(start: Index, end: Index) extends ArraySlice
      object BothBounds {
        val parser: Parsley[BothBounds] = (leftBracket *> Index.parser <~> colon *> Index.parser <* rightBracket).map((BothBounds(_, _)).tupled)
      }

      val parser: Parsley[ArraySlice] = LeftBound.parser | RightBound.parser | BothBounds.parser
    }

    case class Select(toOperator: Seq[Operator]) extends Operator
    object Select {
      val parser: Parsley[Select] = many(Child.parser | ChildSubscript.parser | IndexSubscript.parser | ArraySlice.parser | DeepScan.parser).map(Select(_))
    }

    val operator: Parsley[Operator] = Anchor.parser *> Select.parser
  }
}
