package cn.lagou.sparkcore

import org.apache.spark.{SparkConf, SparkContext}

import scala.math.random

object SparkPi {
  def main(args: Array[String]): Unit = {
    // 1、创建SparkContext
    val conf = new SparkConf().setAppName(this.getClass.getCanonicalName.init).setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    val slices = if (args.length > 0) args(0).toInt else 100
    val N = 100000000
    // 2、生成、转换RDD
    val n= sc.makeRDD(1 to N, slices)
        .map(idx => {
          val (x, y) = (random, random)
          if (x*x + y*y <= 1) 1 else 0
        }).sum()

    // 3、结果输出
    val pi = 4.0 * n / N
    println(s"pi = $pi")

    // 5、关闭SparkContext
    sc.stop()
  }
}
