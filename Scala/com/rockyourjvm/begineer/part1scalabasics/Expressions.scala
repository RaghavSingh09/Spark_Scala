package com.rockyourjvm.begineer.part1scalabasics

object Expressions extends App{
  val x = 1 + 2 //Expression
  println(x)
  println(1+2*3)
  //+ _ * / & | << >> >>> (right shift with zero extension)
  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 //also works with -= *= /= .... and called as side effects
  println(aVariable)

  // Instructions (DO) vs Expression (VALUES)
  //IF Expression
  val aCondition = true
  val aCoditionValue = if(aCondition) 5 else 3
  println(aCoditionValue)
  println(if(aCondition) 5 else 3)

  //EVERYTHING IN SCALA IS AN EXPRESSION
  val aWeirdValue = (aVariable = 4) //Unit === void
  println(aWeirdValue)

  //Side effects: println(), whiles, reassigning

  //Code blocks (They are also expressions) (last line if the code block is its value)

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if(z > 2) "Hello" else "GoodBye"
    //if(y > z) "Hello" else "GoodBye"
  }
  println(aCodeBlock)

  //Expression vs instructions
  //Instructions are executed(think Java), expressions are evaluated (Scala)
  //In scala we'll think in terms of expressions



}
