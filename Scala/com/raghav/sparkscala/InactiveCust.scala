package com.raghav.sparkscala
import org.apache.spark._
import scala.io.Source
import org.apache.log4j._

object InactiveCust extends App{
  // Set the log level to only print errors
  Logger.getLogger("org").setLevel(Level.ERROR)

  // Create a SparkContext using every core of the local machine, named InactiveCust
  val sc = new SparkContext("local[*]", "InactiveCust")

  //Getting data from HDFS to spark session
  /*
  val ordersRaw = sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\data-master\\retail_db\\orders\\part-00000")
  val ordersHeader = ordersRaw.first()
  val custRaw = sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\data-master\\retail_db\\customers\\part-00000")
  val custHeader = custRaw.first()

  println(ordersHeader)
  println(custHeader)
  */

  //Getting data from local file system to spark session
  val ordersInput = Source.fromFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\data-master\\retail_db\\orders\\part-00000").getLines().toList
  val ordersRaw = sc.parallelize(ordersInput)
  val custInput = Source.fromFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\data-master\\retail_db\\customers\\part-00000").getLines().toList
  val custRaw = sc.parallelize(custInput)

  val ordersData = ordersRaw.map(rec => (rec.split(",")(2).toInt,1))
  //ordersData.take(10).foreach(println)

  val custData = custRaw.map(rec => (rec.split(",")(0).toInt,(rec.split(",")(2),rec.split(",")(1))))
  //custData.take(10).foreach(println)

  val joinOrdersAndCustData = custData.leftOuterJoin(ordersData)
  val inactiveCust = joinOrdersAndCustData.filter(rec => (rec._2._2 == None)).map(rec => (rec._2._1)).sortByKey()
  val formattedInactiveCust = inactiveCust.map(rec => (rec._1 +", "+rec._2))
  formattedInactiveCust.take(10).foreach(println)

  formattedInactiveCust.coalesce(1).saveAsTextFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\InactiveCustomers")
}
