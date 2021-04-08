package cn.lagou.project

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object CdnProject {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("CDNTest")
    val sc = new SparkContext(conf)

    val input = sc.textFile("data/cdn.txt").cache()
    // 统计独立IP访问量前10位
    ipStatics(input)
    // 统计每个视频独立IP数
    vedioIpStatics(input)
//统计一天中每个小时的流量
    HoursCountByDay(input)


    sc.stop()

  }

  // 统计独立IP访问量前10位
  def ipStatics(data: RDD[String]):Unit={


    val ipCounts = data.map(x => {
      val f = x.split(" ")
      f(0)
    }).map(x => (x,1)).reduceByKey(_ + _).sortBy(_._2,false)
    println("共有独立IP数量："+ipCounts.count())
    ipCounts.take(10).foreach(println)


  }
  // 统计每个视频独立IP数
  def vedioIpStatics(data: RDD[String]):Unit={
//    初始化数据
    val r1: RDD[(String,String)] = data.map(x => {
      val f = x.split(" ")
      ( f(6), f(0))
    })
//    r1.collect.take(10).foreach(println)(http://cdn.v.abc.com.cn/141011.mp4,115.192.186.231)可以知晓实际的带mp4的url的为实际的url的次级目录，可以进行两次切片
    r1.filter(_._1.endsWith(".mp4")).map(x=>{
      val a = x._1.split("//")(1).split("/")(1)
      val ip = x._2
      (a,ip)
    })
      .groupByKey()
      .mapValues(x => {x.toList.distinct.size})
      .sortBy(_._2 ,false)
      .take(10).foreach(println)


  }

  def HoursCountByDay(data: RDD[String]):Unit = {
    data.map(x =>{
      val lists=x.split(" ")
      val time = lists(3).split(":")(1)
      (time,lists(9).toLong)})
      .reduceByKey(_+_)
      .mapValues(x=>x/1024/1024/1024+"G")
      .sortBy(_._1)
      .foreach(println)

  }

}
