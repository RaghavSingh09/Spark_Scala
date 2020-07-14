package com.rockyourjvm.begineer.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,5,6))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 until 10 //1-9
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  /*lists
    A LinearSeq immutable linked list
     1. head, tail, isEmpty methods are fast O(1)
     2. most operations are O(n): length, reverse
    Sealed - has two subtypes
      1. object Nil (empty)
      2. class ::
   */

  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 89 //+: add at the head :+ add at the tail of the list. : will always be side of the list
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  /*arrays
     The equivalent of simple Java arrays
     1. Can be manually constructed with predefined lengths
     2. Can be mutated (update in place)
     3. Are interoperable with Java's T[] arrays
     4. Indexing is fast
  */
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[String](3)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0  // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers  // implicit conversion
  println(numbersSeq)

  /*vectors => final class Vector[+A]
  The default implementation for immutable sequences
    1. Effectively constant indexed read and write : O(log32(n))
    2. Fast element addition : append/prepend
    3. Implemented as s fixed-branched trie (branch factor 32)
    4. Good performance for large sizes
   */
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating an element in the middle takes long
  println(getWriteTime(numbersList))
  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

}
