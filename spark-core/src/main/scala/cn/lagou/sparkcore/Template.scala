package cn.lagou.sparkcore

import org.apache.spark.{SparkConf, SparkContext}

object Template {
  def main(args: Array[String]): Unit = {
    // 创建SparkContext
    val conf = new SparkConf().setAppName(this.getClass.getCanonicalName.init).setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")

    // 业务逻辑

    // 关闭SparkContext
    sc.stop()
  }
}
