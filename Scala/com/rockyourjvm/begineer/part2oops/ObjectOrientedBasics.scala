package com.rockyourjvm.begineer.part2oops

object objectOrientedBasics extends App{
  //Person class testing
  val person = new Person("Raghav",28)
  println(person.x)
  person.greet("Nilesh")
  person.greet()

  //Writer and Novel class testing
  val author = new Writer("Tarun","Singh",1821)
  val novel = new Novel("I am the best",1861,author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  //println(novel.copy(1990))

  //Counter class testing
  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print

}
class Person(name:String, age:Int) {//Constructor, Class parameters are not fields
  //body
  val x = 2 //Fields
  println(1+3)
  def greet(name:String):Unit = println(s"${this.name} says Hi, $name")
  //this.name will refer to the constructor parameter value not the value passed to the function.

  //method overloading
  def greet():Unit = println(s"Hi, I am $name")
}

class Writer(fn:String,sn:String,val year:Int){ //year is a field(getter)
  def fullName:String = fn+" "+sn
}
class Novel(name:String, year:Int, author:Writer){
  def authorAge = year - author.year
  def isWrittenBy(author:Writer) = author == this.author
  def copy(newYear:Int):Novel = new Novel(name,newYear,author)
}

class Counter(val n:Int=0){
  def inc = {
    println("Incrementing")
    new Counter(n + 1)  //Immutability
  }
  def dec = {
    println("Decrementing")
    new Counter(n - 1)  //Immutability
  }

  def inc(count:Int):Counter = {
    if(count<=0)this
    else inc.inc(count-1)
  }
  def dec(count:Int) :Counter = {
    if(count<=0)this
    else dec.dec(count-1)
  }
  def print = println(n)
}