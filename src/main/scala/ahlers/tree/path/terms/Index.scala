package ahlers.tree.path.terms

case class Index(toInt: Int) {
  val toText: String = toInt.toString
}
