package com.rockyourjvm.begineer.part1scalabasics

object StringOps extends App {
  val str:String = "Hello, I am Iron man"
  println(str.charAt(7))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ","_"))
  println(str.toLowerCase)
  println(str.length)
  println(str.reverse)
  println(str.take(10))

  val aNumStr = "45"
  val aNum = aNumStr.toInt
  println('a' +: aNumStr:+ 'z')
  //+: append in front :+ append at last these are scala specific

  //Scala specific string interpolator
  //s-interpolator
  val name = "Raghav"
  val age = 28
  val greeting = s"Hello, my name is $name and I am $age years old."
  val anotherStr = s"Hello, my name is $name and I will be turning ${age + 1} years old."
  println(anotherStr)

  //f-interpolator
  val speed  = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  //raw-interpolator
  println(raw"This is a \n newline.")
  val escaped = "This is a \n newline."
  println(raw"$escaped")



}
