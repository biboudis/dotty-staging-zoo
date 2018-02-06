import dotty.tools.dotc.quoted.Runners._
import scala.quoted._
import org.junit.Test
import org.junit.Assert._

class TestLinalg {
  import Linalg._

  class Ex extends Linalg with LinalgInt 
 
  @Test def t1(): Unit = {

    val smmul = '{ 
      val x : Array[Array[Int]] = Array(Array(1, 2), Array(3, 4)) 
      val y : Array[Array[Int]] = Array(Array(5, 6), Array(7, 8)) 
      val z : Array[Array[Int]] = Array.ofDim[Int](2, 2) 

      ~(new Ex().mmul('{x}, '{y}, '{z})) 
      
      z.map(_.mkString(" ")).mkString(" ")
    }
    
    assertEquals("""19 22 43 50""", smmul.run)
  }
}