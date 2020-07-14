package com.rockyourjvm.begineer.part1scalabasics

object VarAndVal extends App {
  println("Ram")

  //VALS are immutable, values can't be reassigned
  //Data types of VALS are optional, compiler can infer types

  val x:Int = 42 //val x = 42 will also work
  println(x)

  val aString:String = "Hello"
  val anotherString = "Goodbye"
  val aBoolean:Boolean = false
  val aChar:Char = 'A'
  val anInt:Int = x
  val aShort:Short = 4612
  val aLong:Long  = 5364667568586997897L
  val aFloat:Float = 2.8f
  val aDouble:Double = 3.5

  //Variables
  //Variables are mutable, values can be changed
  //Changing a variable is called as side effect
  var aVariable:Int = 4
  aVariable = x
  println("Variable O/P => "+aVariable)

}
