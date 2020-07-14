package com.rockyourjvm.begineer.part2oops

object Exceptions extends App {
  val x:String = null
  //println(x.length)
  // This ^^ will crash with NPE

  //1. Throwing exceptions
  //val aWeiredValue:String = throw new NullPointerException
  //Resturn type of a thrown exception is Nothing

  //Throwable classes extend the Throwable class.
  //Exception and Error are the major Throwable subtypes

  //2. Catching exceptions
  def getInt(withExceptions:Boolean):Int =
    if(withExceptions) throw new RuntimeException("No Int For You!")
    else 42

  try{
    //Code that might throw exception
    getInt(true)
  }catch{
    case e:RuntimeException=>println("Caught a runtime exception")
  }finally {
    //Code that will get executed NO MATTER WHAT
    //Optional
    //Does not influence the return type of the expression
    //Use finally only for side effects
    println("Finally")
  }

  //3. How to define your own exceptions
  class MyException extends Exception
  val exception =  new MyException
  //throw exception

  //OOM Exception
  //val array = Array.ofDim(Int.MaxValue)// OutofMemoryError Exception

  //SO StackOverflow Exception
  //def infinite:Int = 1 + infinite
  //val noLimit = infinite

  class OverFlowException extends RuntimeException
  class UnderFlowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division By 0")

  object PocketCalculator{
    def add(x:Int,y:Int)={
      val result = x+y
      if(x>0 && y >0 && result<0 ) throw new OverFlowException
      else if (x<0 && y <0 && result >0 ) throw new UnderFlowException
      else result
    }

    def sub(x:Int,y:Int)={
      val result = x-y
      if(x>0 && y <0 && result<0 ) throw new OverFlowException
      else if (x<0 && y >0 && result >0 ) throw new UnderFlowException
      else result
    }

    def mul(x:Int,y:Int)={
      val result = x*y
      if(x>0 && y >0 && result<0 ) throw new OverFlowException
      else if(x<0 && y <0 && result<0 ) throw new OverFlowException
      else if (x>0 && y <0 && result >0 ) throw new UnderFlowException
      else if (x<0 && y >0 && result >0 ) throw new UnderFlowException
      else result
    }

    def div(x:Int, y:Int)={
      if(y==0) throw new MathCalculationException
      else x/y
    }
  }

  //println(PocketCalculator.add(1,Int.MaxValue))
  //println(PocketCalculator.div(2,0))
}
