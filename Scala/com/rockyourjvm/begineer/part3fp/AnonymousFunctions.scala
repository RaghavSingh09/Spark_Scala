package com.rockyourjvm.begineer.part3fp


object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val doubler: Int => Int = (x: Int) => x * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // call, lambda must be called with ()

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b, data type must be there so that the compiler will understand which _ belongs to which type.

  /*
    1.  MyList: replace all FunctionX calls with lambdas
    2.  Rewrite the "special" adder as an anonymous function
  */

  val superAdd = (x: Int) => (y: Int) => x + y //lambda version of curried functions
  println(superAdd(3)(4))

  /*
    Instead of passing anonymous FunctionX instances every time we can use lambda
    (x, y) => x + y
   */
}
