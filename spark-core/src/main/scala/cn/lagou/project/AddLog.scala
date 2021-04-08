package cn.lagou.project
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object AddLog {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("project3").setMaster("local")
    val sc = new SparkContext(conf)
    sc.setLogLevel("warn")
    // 读文件
    val clickLog = sc.textFile("data/click.log")
    val impLog = sc.textFile("data/imp.log")


    val clkRDD = clickLog.map { line =>
      val arr = line.split("\\s+")
      val adid = arr(3).substring(arr(3).lastIndexOf("=") + 1)
      (adid, (1, 0))
    }

    clkRDD.take(5).foreach(println)
    val impRDD = impLog.map { line =>
      val arr = line.split("\\s+")
      val adid = arr(3).substring(arr(3).lastIndexOf("=") + 1)
      (adid, (0, 1))
    }

    // join
    val RDD: RDD[(String, (Int, Int))] = clkRDD.union(impRDD)
      .reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
    RDD.collect.foreach(println)





    sc.stop()
  }


}
