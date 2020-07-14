package com.raghav.sparkscala
import com.sun.org.apache.xpath.internal.functions.FuncSubstring
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object CrimeDataAnalysis {
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine, named RatingsCounter
    val sc = new SparkContext("local[*]", "CrimeDataAnalysis")

    // Load up each line of the ratings data into an RDD
    val crimeData = sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\Crimes_2001_to_present.csv")
    val header = crimeData.first()
    //println(header)
    val crimeDataWithoutHeader = crimeData.filter(data => data!=header)
    //crimeDataWithoutHeader.take(10).foreach(println)
    val rec = crimeDataWithoutHeader.first()
    val distinctDates = crimeDataWithoutHeader.map(cr => cr.split(",")(2).split(" ")(0)).distinct.collect().sorted
    //distinctDates.foreach(println)

    //val yearMonth = distinctDates.map(ym => ym.takeRight(4)+""+ym.substring(0,2))
    val crimeRecPerYM =crimeDataWithoutHeader.map(rec=>
    {
      val t = rec.split(",")
      val d = t(2).split(" ")(0)
      val ym = d.split("/")(2)+d.split("/")(0)
      ((ym.toInt,t(6)),1)
    })

    crimeRecPerYM.take(10).foreach(println)

    val crimeCountPerTypeByYM = crimeRecPerYM.reduceByKey((t,v)=>t+v)

    //crimeCountPerTypeByYM.take(10).foreach(println)

    val crimeCountPerTypeByYMSorted = crimeCountPerTypeByYM.map(rec => ((rec._1._1,-rec._2), rec._1._1+"\t"+rec._2+"\t"+rec._1._2)).sortByKey().map(rec=>rec._2)

    //crimeCountPerTypeByYMSorted.take(10).foreach(println)

    crimeCountPerTypeByYMSorted.coalesce(1).saveAsTextFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\CrimeDataSpark")

    //(GZip) Zipped Format File Saving
    //crimeCountPerTypeByYMSorted.saveAsTextFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\crimeCountPerTypeByYMSorted.txt",classOf[org.apache.hadoop.io.compress.GzipCodec])

    //Preview Output Data
    val outputdata = sc.textFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\CrimeDataSpark")
    println("Output File Rec Count: "+outputdata.count())
  }
}
