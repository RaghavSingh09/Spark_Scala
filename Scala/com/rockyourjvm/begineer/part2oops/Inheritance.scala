package com.rockyourjvm.begineer.part2oops

object Inheritance extends App {

  //Single class Inheritance
  class Animal {
    //protected def eat = println("NomNomNom")
    def eat = println("NomNomNom")
  }
  class Cat extends Animal{
    def crunch =
      {
        println("Crunch Crunch")
        eat
      }
  }

  val eat = new Cat
  //eat.eat give error due to protected access modifier, protected methods or fields are only accessible inside the child class not through the object of the child class
  eat.crunch

  //Constructors
  class Person(name:String,age:Int){
    def this(name:String)=this(name,0)//Auxiliary constructors
  }
  class Adult(name:String,age:Int,idCard:String) extends Person(name) //If the parent class has parameters then we need to provide the same param list while extending that class

  //Overriding
  class Dog extends Animal{
    override def eat = {
      super.eat//Animal class eat method will be called as it is the super class of Dog
      println("Dog Method")
    }
  }

  val dog = new Dog
  dog.eat
  //Methods, fields and constructor params can be overwritten in the child classes

  //type substitution (broad : polymorphism)
  val unknownAnimal: Animal = new Dog()
  unknownAnimal.eat //Derived implementation of eat method will be called which is in the Dog class

  /*Super
  To call the super class method or fields in the derived classes
  */

  /* Preventing Overriding
    1 - Use final on member
    2 - Use final on the entire class
    3 - Seal the class = extend classes in the current file but not in the other file.
   */

}
