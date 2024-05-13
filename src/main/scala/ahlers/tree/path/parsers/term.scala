package ahlers.tree.path.parsers

import ahlers.tree.path.terms.Index
import ahlers.tree.path.terms.Name
import parsley.Parsley
import parsley.character.satisfy
import parsley.character.stringOfSome

object term {
  val index: Parsley[Index] = stringOfSome(_.isDigit).map(_.toInt).map(Index)

  val name: Parsley[Name] = {
    val isValid: Set[Char] = ('a' to 'z').toSet ++ ('A' to 'Z').toSet ++ ('0' to '9').toSet
    stringOfSome(satisfy(isValid)).map(Name)
  }
}
