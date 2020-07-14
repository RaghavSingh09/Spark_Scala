package com.rockyourjvm.begineer.part1scalabasics

object Functions extends App{
  def aParamLessFuction:Int = 42
  def aFunction(a:String,b:Int):String={
    a +' '+ b
  }
  println(aParamLessFuction)
  println(aFunction("Hello",5))

  def aRepeatedFun(aString:String, n:Int):String={
    if(n==1) aString
    else aString +" "+ aRepeatedFun(aString,n-1)
  }
  println(aRepeatedFun("Hi",5))

  //WHEN YOU NEED LOOPS, USE RECURSION.
  def aFunWithSideEffects(aString:String):Unit=println(aString)
  aFunWithSideEffects("Unit")

  //Function inside a function
  def aBigFun(n: Int):Int = {
    def aSmallFun(a:Int,b:Int):Int = a+b
    aSmallFun(n,n-1)
  }
  println(aBigFun(5))

  //Task
  def aNameAgeFun(name:String, age:Int):String={
    "Hi, my name is "+name+" and I am "+age+" years old."
  }
  println(aNameAgeFun("Raghav",28))

  def factorial(fact:Int):Int={
    if(fact<=1)
      1
    else
      fact * factorial(fact-1)
  }
  println(factorial(9))


  def fibonacci(fibo:Int):Int={
    if(fibo<=2)
      1
    else
      fibonacci(fibo-1) + fibonacci(fibo-2)
  }
  println(fibonacci(6))


  def countDown(n:Int):Unit={
    if(n>0){
      println(n)
      countDown(n-1)
    }
  }

  def countUp(n:Int):Unit={
    if(n>0){
      countUp(n-1)
      println(n)//stack calling
    }
  }

  countDown(5)
  countUp(5)
  //Learn about Tail Recursion and Tail annotation to improve performance of recursive functions.

  def isPrime(num:Int):Boolean={
    def isPrimeUntil(t:Int):Boolean=
      if(t<=1) true
      else num % t != 0 && isPrimeUntil(t-1)
    isPrimeUntil(num/2)
  }
  println(isPrime(5))
  println(isPrime(2))
  println(isPrime(6))
}
