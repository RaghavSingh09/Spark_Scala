package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.max


/** Find the maximum precipitation by weather station */
object MaxPrecipitation {
  
  def parseLine(line:String)= {
    val fields = line.split(",")
    val prcpDate = fields(1)
    val entryType = fields(2)
    val prcpvalue = fields(3).toInt
    (prcpDate, entryType, prcpvalue)
  }
    /** Our main function where the action happens */
  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    
    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "MaxPrecipitation")
    
    // Read each line of input data
    val lines = sc.textFile("../1800.csv")
    
    // Convert to (PrcpDate, entryType, prcpValue) tuples
    val parsedLines = lines.map(parseLine)
    
    // Filter out all but PRCP entries
    val maxPrcp = parsedLines.filter(x => x._2 == "PRCP")
    
    // Convert to (Date, PRCP_Values)
    val maxPrcpDate = maxPrcp.map(x => (x._1, x._3))
    
    //Getting Max value in RDD
    //Method 1
    val maxPrcpVal = maxPrcpDate.max()(new Ordering[Tuple2[String, Int]](){
                                       override def compare(x: (String, Int), y: (String, Int)): Int = 
                                       Ordering[Int].compare(x._2, y._2)
                                  })
    //Method 2
    val maxPrcpValDate = maxPrcpDate.takeOrdered(1)(Ordering[Int].reverse.on(_._2))
    
    println(maxPrcpValDate.foreach(print))
    println(maxPrcpVal.toString())
    
    //Method 3
    val flipped = maxPrcpDate.map(x => (x._2, x._1)).max()
    println(flipped)
    
    //Getting Max in Array
    val a = Array(("a",1), ("b",2), ("c",1), ("d",3))
    val maxKey = a.maxBy(_._2)
    println(maxKey.toString())
    
  }
  
}