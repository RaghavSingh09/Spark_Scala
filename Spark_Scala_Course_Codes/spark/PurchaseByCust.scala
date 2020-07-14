package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object PurchaseByCust {
  
  def parseLine(line:String)= {
    val fields = line.split(",")
    val custId = fields(0)
    val amount = fields(2).toFloat
    (custId,amount)
  }
  /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
     // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "WordCount")   
    
    // Read each line of customer-orders.csv into an RDD
    val input = sc.textFile("../customer-orders.csv")
    
    //Convert input file data into desired format
    val custRec = input.map(parseLine)
    
    val sumAmtBycustId = custRec.reduceByKey( (x,y) => (x+y))
    
    //val result = sumAmtBycustId.collect()
    //println(sumAmtBycustId.foreach(println))
    //result.foreach(println)
    
    val purchaseSorted = sumAmtBycustId.map( x => (x._2, x._1) ).sortByKey().map( x => (x._2, x._1) ).collect()
    purchaseSorted.foreach(println)
    
  }
}