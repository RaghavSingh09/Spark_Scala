package com.raghav.sparkscala

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object TopThreeCrime extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)

  // Create a SparkContext using every core of the local machine
  val sc = new SparkContext("local[*]", "TopThreeCrime")
  val sqlContext= new org.apache.spark.sql.SQLContext(sc)
  import sqlContext.implicits._
  val crimeData = sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\Crimes_2001_to_present.csv")
  val header = crimeData.first()
  val crimeDataWithoutHeader = crimeData.filter(data => data!=header)
  /*
  val crimeCountForResidence = sc.parallelize(crimeDataWithoutHeader.filter(rec => rec.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)",-1)(7)=="RESIDENCE").
    map(rec => (rec.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)",-1)(5),1)).reduceByKey((t,v) => (t+v)).map(rec => (rec._2,rec._1)).sortByKey(false).take(3))
  */
  val crimeCountForResidence = sc.parallelize(crimeDataWithoutHeader.filter(rec => rec.split(",")(7)=="RESIDENCE").
    map(rec => (rec.split(",")(5),1)).reduceByKey((t,v) => (t+v)).map(rec => (rec._2,rec._1)).sortByKey(false).take(3))
  //crimeCountForResidence.foreach(println)
  //crimeCountForResidence.toDF("Crime_Count","Crime_Type").show()
  crimeCountForResidence.map(rec => (rec._2,rec._1)).toDF("Crime_Type","Crime_Count").write.json("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\TopThreeCrime")
  sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\TopThreeCrime").collect().foreach(println)
}
