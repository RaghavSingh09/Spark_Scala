package com.raghav.sparkscala
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import scala.io.Source

object InactiveCustDF extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)

  // Create a SparkContext using every core of the local machine, named InactiveCust
  val sc = new SparkContext("local[*]", "InactiveCustDF")
  val sqlContext= new org.apache.spark.sql.SQLContext(sc)
  import sqlContext.implicits._

  val ordersInput = Source.fromFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\data-master\\retail_db\\orders\\part-00000").getLines().toList
  val ordersRaw = sc.parallelize(ordersInput)
  val custInput = Source.fromFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\data-master\\retail_db\\customers\\part-00000").getLines().toList
  val custRaw = sc.parallelize(custInput)

  val ordersData = ordersRaw.map(rec => rec.split(",")(2).toInt).toDF("Order_Cust_Id")
  val custData = custRaw.map(rec => (rec.split(",")(0).toInt,rec.split(",")(2),rec.split(",")(1))).toDF("Cust_Id","Cust_LN","Cust_FN")
  ordersData.createOrReplaceGlobalTempView("Orders")
  custData.createOrReplaceGlobalTempView("Customers")

  //sqlContext.sql("Select * from global_temp.Orders limit 10").show()
  //sqlContext.sql("Select * from global_temp.Customers").show(10)

  val inactiveCust = sqlContext.sql("Select concat(concat(c.Cust_LN,', '),c.Cust_FN) as Inactive_Customers " +
    "from global_temp.Customers as c left outer join global_temp.Orders as o on c.Cust_Id = o.Order_Cust_Id " +
    "where o.Order_Cust_Id is null order by c.Cust_LN, c.Cust_FN")

  inactiveCust.rdd.map(rec=> (rec.mkString(", "))).coalesce(1).saveAsTextFile("C:\\Users\\singh\\Desktop\\ScalaProjectFiles\\InactiveCustSparkSql")
}
