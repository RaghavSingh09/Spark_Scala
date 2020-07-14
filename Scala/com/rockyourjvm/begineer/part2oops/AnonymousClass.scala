package com.rockyourjvm.begineer.part2oops

object AnonymousClass extends App {
  abstract class Animal{
    def eat:Unit
  }

  //Anonymous Class
  val funnyAnimal: Animal = new Animal{
    override def eat: Unit = println("hahahahaha")
  }
  /*
    Equivalent with
    class AnonymousClass$$anon$1 extends Animal{
      override def eat: Unit = println("hahahahaha")
    }
    val funnyAnimal: Animal = new AnonymousClass$$anon$1
   */

  println(funnyAnimal.getClass)

  class Person(name:String){
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help you.")
  }

  val jim = new Person("Jim"){
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service.")
  }

  /*
    We can instantiate types and override fields or methods on the spot.
    Anonymous class works for abstract and non-abstract class as-well (traits and classes).
    Rule:
      Pass in required constructor args if needed
      Implement all abstract fields/methods
   */
}
