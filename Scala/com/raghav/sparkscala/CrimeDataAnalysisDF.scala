package com.raghav.sparkscala

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext


object CrimeDataAnalysisDF extends App{
  Logger.getLogger("org").setLevel(Level.ERROR)

  // Create a SparkContext using every core of the local machine
  val sc = new SparkContext("local[*]", "CrimeDataAnalysisDF")
  val sqlContext= new org.apache.spark.sql.SQLContext(sc)
  import sqlContext.implicits._

  val crimeData = sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\Crimes_2001_to_present.csv")
  val header = crimeData.first()
  val crimeDataRec = crimeData.filter(rec => rec!=header)

  val crimeDataWithDateAndType_DF = crimeDataRec.map(rec=> (rec.split(",")(2),rec.split(",")(5))).toDF("Crime_Date","Crime_Type")
  crimeDataWithDateAndType_DF.registerTempTable("Crime_Data")

  val crimeSqlData = sqlContext.sql("Select cast(concat(right(substring(Crime_Date,0,10),4),left(Crime_Date,2)) as int) as Crime_YM," +
    "count(1) as Crime_Count,Crime_Type from " +
    "Crime_Data " +
    "group by Crime_YM,Crime_Type " +
    "order by Crime_YM,Crime_Count desc")

  //crimeSqlData.rdd.take(10).foreach(println)
  //crimeSqlData.rdd.map(rec => (rec.mkString("\t"))).take(10).foreach(println)

  //Directly Saving DF into file with overwrite mode.(Default save format is Parquet)
  //crimeSqlData.write.mode("overwrite").csv("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\CrimeDataSparkSql")

  //Converting DF into RDD with tab separated string then saving it into file.
  crimeSqlData.rdd.map(rec => (rec.mkString("\t"))).coalesce(1).saveAsTextFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\CrimeDataSparkSql")

  //Preview Output Data
  val outputdata = sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\CrimeDataSparkSql")
  println("Output File Rec Count: "+outputdata.count())
}
