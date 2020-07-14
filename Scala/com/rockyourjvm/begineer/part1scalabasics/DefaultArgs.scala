package com.rockyourjvm.begineer.part1scalabasics

object DefaultArgs extends App {

  def trFact(n:Int,acc:Int=1):Int={
    if(n<=1)
      acc
    else trFact(n-1,n*acc)
  }

  println(trFact(10))
  println(trFact(n=5,acc = 1))

  def greet(name:String = "Superman", age:Int=10):String=
    s"Hi, I'm $name and I'm $age years old"

  println(greet(age=5))
  println(greet(name="Sally",age=4))
  println(greet(age = 9, name = "Tom"))
  println(greet())
  /*
  1. Pass in every leading arguments
  2. Name the argument
  Now compiler will not confuse with the order of args to be used.
   */
}
