package com.rockyourjvm.begineer.part2oops

object CaseClasses extends App {
  /*
    equals, hashcode, toString
  */

  case class Person(name:String, age:Int)

  //1. Class params are fields
  val jim = new Person("Jim",34)
  println(jim.name)

  //2. Sensible toString
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim.toString)

  //3. equals and hashCode implements OOTB
  val jim2 = new Person("Jim",34)
  println(jim == jim2)

  //4. CCs have copy method
  val jim3 = jim.copy(age=45)
  println(jim3)

  //5. CCs have companion objects already implemented
  val thePerson = Person
  val mary = Person("Mary",23)

  //6. CCs are serializable
  //Akka

  //7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  //Case objects can't have companion class or methods
  case object UnitedKingdom{
    def name:String = "The UK of GB"
  }

  //Quick lightweight data structures with little boilerplate
}
