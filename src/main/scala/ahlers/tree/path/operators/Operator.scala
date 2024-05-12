package ahlers.tree.path.operators

/**
 * @see [[https://github.com/json-path/JsonPath/blob/45333e0a310af70ad48d34d306da30af1e8e6314/README.md#operators]]
 */
trait Operator {
  def toText: String
}
