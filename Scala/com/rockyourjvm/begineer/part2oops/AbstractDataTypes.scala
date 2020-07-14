package com.rockyourjvm.begineer.part2oops

object AbstractDataTypes extends App{

  /*Abstract
    Abstract classes or data types are created to be extended or implemented later in your program
  */
  abstract class Animal{
    val creatureType: String
    def eat:Unit
  }

  class Dog extends Animal{
    override val creatureType: String = "Rat"
    def eat: Unit = println("Bark Bark")
  }

  /*Traits
    These are the ultimate abstract data types in scala
    They can be inherited along the classes
  */
  trait Carnivore{
    def eat(animal: Animal):Unit
  }

  class Crock extends Animal with Carnivore{
    val creatureType: String = "Crock"
    def eat:Unit = println("CrockCrockCrock")
    def eat(animal: Animal): Unit = println(s"I am a Crock and I'm eating ${animal.creatureType}")
  }

  val dog1 = new Dog
  val croc = new Crock
  croc.eat(dog1)

  /* Traits vs Abstract Classes
    1. Both can have abstract or non-abstract methods or fields
    2. Traits don't have constructor params
    3. Multiple traits may be inherited by the same class
    4. Traits are behaviour, abstract class = "Thing"
  */


}
