package ahlers.tree.path.filterOperators

/**
 * @see [[https://github.com/json-path/JsonPath/blob/45333e0a310af70ad48d34d306da30af1e8e6314/README.md#filter-operators]]
 */
trait FilterOperator {
  def toText: String
}
