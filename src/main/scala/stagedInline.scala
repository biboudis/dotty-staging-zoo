import scala.quoted._

object Test {

  def plus1(i : Expr[Int]) = '(~i + 1)

  def times2(i : Expr[Int]) = '(~i * 2)

  def chain(f1: Expr[Int] => Expr[Int], f2: Expr[Int]  => Expr[Int]): Expr[Int => Int] = '{(i: Int) =>
    val tmp = ~(f2('(i)))
    ~(f1('(tmp)))
  }

  val output = (chain(plus1,times2)).run

  output(1)
}
