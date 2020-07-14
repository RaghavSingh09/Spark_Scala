package com.rockyourjvm.begineer.part1scalabasics

object CBNvsCBV extends App {
  def calledByValue(x:Long):Unit={
    println("by value: "+x)
    println("by value: "+x)
  }

  def calledByName(x: => Long):Unit={
    println("by name: "+x)
    println("by name: "+x)
  }

  calledByValue(System.nanoTime())//Value calculated before the call and passed to the function.
  calledByName(System.nanoTime())//expression is passed literally and each time value is begin calculated and passed to the function.

  def infinite():Int = 1+infinite()
  def printFirst(x:Int,y: => Int)=println(x)

  printFirst(5,infinite())//By name parameter delays the evaluation of the expression or function until it is begin used.
}
