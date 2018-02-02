import scala.quoted.Expr
import dotty.tools.dotc.quoted.Runners._

object Gib {

  inline def gib(inline n: Int, x: Int, y: Int) = ~sgib(n, '(x), '(y))

  def sgib(n: Int, x: Expr[Int], y: Expr[Int]): Expr[Int] = {
    n match {
      case 0 => x
      case 1 => y
      case n => '{
        val z = ~x + ~y
        ~(sgib(n-1, y, '{z}))
      }
    }
  }
}