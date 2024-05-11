package ahlers.tree.path.term.diffx

import ahlers.tree.path.term.Wildcard
import com.softwaremill.diffx.Diff

object instances {

  implicit val diffWildcard: Diff[Wildcard.type] = Diff.derived

}
