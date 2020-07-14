package com.rockyourjvm.begineer.part2oops

object Objects extends App {
  // Scala doest not have class-level functionality ("static")
  object Person{ //type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly:Boolean = false

    //factory method
    def apply(mother:Person,father:Person):Person = new Person("Radha")
  }

  class Person(val name:String = "Raghav"){
    // Instance-level functionality

  }
  //Companions (class and object with same name and reside in the same scope)

  println(Person.N_EYES)
  println(Person.canFly)

  //Scala object = Singleton Instance
  val mary = Person
  val john = Person
  println(mary == john)//true due to singleton object

  val mary1 = new Person
  val john1 = new Person
  println(mary1 == john1)//false due to class property

  val bobbie = Person(mary1,john1)//using the apply method

  //Scala Application
  //def main(args:Array[String]):Unit or extends App which already has def main method

  //Scala doesn't have "static" values/methods
  /*Scala objects
    1. are in their own class
    2. are the only instance and are equal
    3. singleton patterns in one line
  */
  /*Scala Companion
    1. Can access each other's private members
    2. Scala is more OO than Java!
    3. Object and Class with same name can reside in same scope
   */

}
