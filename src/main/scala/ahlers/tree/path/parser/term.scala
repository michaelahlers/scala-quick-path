package ahlers.tree.path.parser

import ahlers.tree.path.term.Wildcard
import parsley.Parsley
import parsley.character.char

object term {
  val wildcard: Parsley[Wildcard.type] = char('*').as(Wildcard)
}
