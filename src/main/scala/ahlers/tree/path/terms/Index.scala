package ahlers.tree.path.terms

case class Index(toInt: Int) extends Term {
  override val toText: String = toInt.toString
}
