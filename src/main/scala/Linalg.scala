import scala.quoted.Expr
import dotty.tools.dotc.quoted.Runners._

// Ported example from "The Design and Implementation of BER MetaOCaml" paper http://okmij.org/ftp/meta-programming/tutorial/loop_motion.ml

object Linalg {
  trait Linalg {
    type tdom     // scalars
    type tdim     // vector dimensions
    type tind     // the index
    type tunit    // unit type
    type tmatrix  // matrix

    def scalar_mul(x: tdom, y: tdom): tdom
    def mat_dim(a: tmatrix): (tdim, tdim)
    def mat_get(a: tmatrix, i: tind, j: tind): tdom
    def mat_inc(a: tmatrix, i: tind, j: tind, el: tdom): tunit
    def loop(n: tdim, body: (tind => tunit)) : tunit

    def mmul(a: tmatrix, b: tmatrix, c: tmatrix): tunit = {
      loop(mat_dim(a)._1, (i: tind) => {
        loop(mat_dim(b)._1, (k: tind) => {
          loop(mat_dim(b)._2, (j: tind) => {
            mat_inc(c, i, j, scalar_mul(mat_get(a, i, k), mat_get(b, k, j)))
          })
        })
      })
    }
  }

  trait LinalgInt extends Linalg {

    type tdom = Expr[Int]
    type tdim = Expr[Int]
    type tind = Expr[Int]
    type tunit = Expr[Unit]
    type tmatrix = Expr[Array[Array[Int]]]

    def scalar_mul(x: tdom, y: tdom): tdom = '{ ~x + ~y }
    
    def mat_get(a: tmatrix, i: tind, j: tind): tdom = '{(~a)(~i)(~j)}
    
    def loop(n: tdim, body: (tind => tunit)) : tunit = '{
      var i = 0
      val i_val = i
      while (i_val < ~n) {
        ~body('{i_val})
        i += 1
      }
    }

    def mat_inc(a: tmatrix, i: tind, j: tind, v: tdom): tunit = '{      
      val aa: Int = ~(mat_get(a, i, j))
      val bb: Int = ~v
      
      (~a)(~i)(~j) = ~(aa + bb)
    }

    def mat_dim(a: tmatrix): (tdim, tdim) = ('{(~a).length}, '{(~a)(0).length}) 
  }
}
