package ahlers.tree.path.term.diffx

import ahlers.tree.path.term.{Name, Wildcard}
import com.softwaremill.diffx.Diff

object instances {

  implicit val diffWildcard: Diff[Wildcard.type] = Diff.derived
  implicit val diffName: Diff[Name]              = Diff.derived

}
