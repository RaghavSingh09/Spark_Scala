import org.apache.spark.{SparkConf,SparkContext}
import org.apache.spark.storage.StorageLevel

object Model {
  def main(args: Array[String]) {

    val dp = new SparkConf().setAppName("model").setMaster("local[*]")
    val sc = new SparkContext(dp)

    val file = sc.textFile("C:\\Users\\singh\\Desktop\\Test_Cache.txt")

    val message = file.filter(_.startsWith("Error"))
    val rec = message.map(_.split("\t")).map(r => r(1))

    //  rec.persist(StorageLevel.MEMORY_ONLY) //for cache
    rec.cache()

    val c = message.count
    val s = rec.filter(_.contains("Scala")).count
    val p = rec.filter(_.contains("plsql")).count
    val m = rec.filter(_.contains("mysql")).count
    
    println("Printing all records : " + c, s, p, m)


  }
  }