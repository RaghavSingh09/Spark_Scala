package com.rockyourjvm.begineer.part2oops

object MethodNotations extends App {
  class Person(val name:String, favMovie:String, val age:Int =0){
    def likes(movie:String):Boolean = movie == favMovie
    def +(person:Person):String = s"${this.name} is hanging out with ${person.name}"
    def +(nickName:String):Person = new Person(s"$name {$nickName}",favMovie)
    def hangOutWith(person:Person):String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the hell"
    def unary_+ : Person = new Person(name,favMovie,age+1)
    def isAlive : Boolean = true

    def apply():String = s"Hi, my name is $name and I like $favMovie"
    def apply(n:Int):String = s"$name watched $favMovie $n times!"

    def learns(thing:String)= s"$name is learning $thing"
    def learnScala = this learns "Scala"
  }

  val mary = new Person("Marry","Avatar")
  println(mary.likes("Avatar"))
  println(mary likes "Avatar" ) //equivalent
  // infix notation = operator notation (syntactic sugar)
  // object.method with single parameter can be replaced with the expression object method parameter.

  // "operators in Scala"
  val tom  = new Person("Tom","Avengers")
  println(mary hangOutWith tom)//hangOutWith acts like an operator

  //All operators are methods in scala
  println(1 + 2)
  println(1.+(2))

  //prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !
  println(x+" "+y)

  println(!mary)
  println(mary.unary_!)

  // postfix notation, it is only available with method with no parameter
  println(mary.isAlive)
  println(mary isAlive)

  // apply, if an object is called as a method() then it will search for apply method in the class and implements it in the o/p
  println(mary.apply())
  println(mary())

  println((mary + "The Rock Star")())
  println((mary + "The Rock Star").apply())

  println((+mary).age)
  println(mary learnScala)

  println(mary(10))
}
