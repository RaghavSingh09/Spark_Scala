package com.raghav.sparkscala
import org.apache.spark._
import org.apache.log4j.{Level, Logger}

object MaxSalByCity extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)

  // Create a SparkContext using every core of the local machine
  val sc = new SparkContext("local[*]", "MaxSalByCity")
  val rdd2=sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\EmpInfo.txt").map{ x => x.split(" ") }
    .map{ x =>((x(2)),(x(1),x(4).toDouble))}.groupByKey
  rdd2.foreach(println)
  for(i<-rdd2)
  {
    println(i._1,i._2.filter(x=>x._2==i._2.map(x=>x._2).max))
  }
}
