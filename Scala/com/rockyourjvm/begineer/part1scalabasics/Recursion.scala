package com.rockyourjvm.begineer.part1scalabasics

import scala.annotation.tailrec

object Recursion extends App {


  def factorial(fact:Int):Int={
    if(fact<=1 )
      1
    else {
      println("Computing factorial of "+ fact+" - I need factorial of "+(fact-1))
      val res = fact * factorial(fact - 1)
      println("Computing factorial of "+ fact)
      res
    }
  }
  //println(factorial(10))

  def anotherFactorial(n:Int):BigInt={
    @tailrec
    def factHelper(x:Int,accumulator:BigInt):BigInt=
      if(x<=1) accumulator
      else factHelper(x-1,x*accumulator) //Tail Recursion  = use recursive call as the last expression

    factHelper(n,1)
  }
  println(anotherFactorial(10  ))
  /*
  anotherFactorial(10) = factHelper(10,1)
  = factHelper(9,10*1)
  = factHelper(8,9*10*1)
  = factHelper(7,8*9*10*1)
  = ...
  = factHelper(2,3*4*...*10*1)
  = factHelper(1,1*2*3*...*9*10)
  = 1*2*3*...*9*10
   */

  //WHEN YOU NEED LOOPS, USE TAIL RECURSION
  @tailrec
  def concatStringTailRec(aString:String, n:Int, accumulator:String):String={
    if(n<=0) accumulator
    else concatStringTailRec(aString,n-1,aString+" "+accumulator)
  }
  println(concatStringTailRec("Hi",5,""))

  def isPrime(num:Int):Boolean={
    @tailrec
    def isPrimeTailrec(t:Int,isStillPrime:Boolean):Boolean=
      if(!isStillPrime)false
      else if(t<=1)true
      else isPrimeTailrec(t-1,num % t != 0 && isStillPrime)

    isPrimeTailrec(num/2,true)
  }
  println(isPrime(17))

  def fibonacci(n:Int):Int={
    @tailrec
    def fiboTailRec(i:Int, last:Int,nextLast:Int):Int =
      if(i>=n)last
      else fiboTailRec(i+1,last+nextLast,last)

    if(n<=2) 1
    else fiboTailRec(2,1,1)
  }

  println(fibonacci(9))



}
