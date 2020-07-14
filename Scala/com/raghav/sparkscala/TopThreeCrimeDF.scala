package com.raghav.sparkscala

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object TopThreeCrimeDF extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)

  // Create a SparkContext using every core of the local machine
  val sc = new SparkContext("local[*]", "TopThreeCrime")
  val sqlContext= new org.apache.spark.sql.SQLContext(sc)
  import sqlContext.implicits._
  val crimeData = sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\Crimes_2001_to_present.csv")
  val header = crimeData.first()
  val crimeDataWithoutHeader = crimeData.filter(data => data!=header)
  val crimeDataDF = crimeDataWithoutHeader.map(rec =>
    {val r = rec.split(",")//rec.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)",-1)
      (r(7), r(5))}).toDF("Loc_Desc","Crime_Type")

  crimeDataDF.createOrReplaceGlobalTempView("Crime_Data")
  //sqlContext.setConf("spark.sql.shuffle.partitions",2)
  sqlContext.sql("Select Crime_Type,count(1) AS Crime_Count From global_temp.Crime_Data " +
    "Where Loc_Desc = 'RESIDENCE' Group by Crime_Type order by Crime_Count desc limit 3").
    write.json("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\TopThreeCrimeSql")

  //Preview Output Data
  val outputdata = sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\TopThreeCrimeSql")
  outputdata.foreach(println)
}
