package ahlers.tree.path.term

case class Index(toInt: Int) {
  val toText:String = toInt.toString
}
