import dotty.tools.dotc.quoted.Runners._
import scala.quoted._
import org.junit.Test
import org.junit.Assert._

class TestLinalg {
  import Linalg._

  class Ex extends Linalg with LinalgInt {
    val x : Expr[Array[Array[Int]]] = '{ Array(Array(1, 2), Array(3, 4), Array(5, 6)) }
    val y : Expr[Array[Array[Int]]] = '{ Array(Array(5, 10), Array(10, 15), Array(15, 20)) }
    val z : Expr[Array[Array[Int]]] = '{ Array.ofDim[Int](2, 3) }
  }
 
  @Test def t1(): Unit = {
    val test = new Ex()
    println(test.mmul(test.x, test.y, test.z).show)
  }
}