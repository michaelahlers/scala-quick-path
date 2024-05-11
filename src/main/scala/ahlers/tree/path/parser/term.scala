package ahlers.tree.path.parser

import ahlers.tree.path.term.Name
import ahlers.tree.path.term.Wildcard
import parsley.Parsley
import parsley.character.char
import parsley.character.satisfy
import parsley.character.stringOfSome

object term {
  val wildcard: Parsley[Wildcard.type] = char('*').as(Wildcard)
  val name: Parsley[Name]              = stringOfSome(satisfy(_.isLetterOrDigit)).map(Name)
}
