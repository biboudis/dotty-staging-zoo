import org.junit.Test
import org.junit.Assert._

import dotty.tools.dotc.quoted.Runners._

class TestGib {

  import Gib._
  @Test def t1(): Unit = {
    println(sgib(4, 1, 1).show)
  }

}