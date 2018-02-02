
object UseLinalg {
  import Linalg._
  import dotty.tools.dotc.quoted.Runners._

  def main(args: Array[String]): Unit = {
    println(sgib(4, 1, 1).run)
  }
}