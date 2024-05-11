package parsley.diffx

import com.softwaremill.diffx.Diff
import com.softwaremill.diffx.DiffResultValue
import parsley.Failure
import parsley.Result
import parsley.Success

object instances {

  implicit def diffSuccess[A: Diff]: Diff[Success[A]] = Diff.derived
  implicit def diffFailure[E: Diff]: Diff[Failure[E]] = Diff[E].contramap(_.msg)

  implicit def diffResult[E: Diff, A: Diff]: Diff[Result[E, A]] = {
    case (left: Success[A], right: Success[A], context) => Diff[Success[A]].apply(left, right, context)
    case (left: Failure[E], right: Failure[E], context) => Diff[Failure[E]].apply(left, right, context)
    case (left, right, _)                               => DiffResultValue(left, right)
  }

}
