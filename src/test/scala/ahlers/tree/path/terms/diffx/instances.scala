package ahlers.tree.path.terms.diffx

import ahlers.tree.path.terms.Index
import ahlers.tree.path.terms.Name
import com.softwaremill.diffx.Diff

object instances {

  implicit val diffIndex: Diff[Index] = Diff.derived
  implicit val diffName: Diff[Name]   = Diff.derived

}
