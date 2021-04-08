package cn.lagou.project

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark
import org.apache.spark.broadcast.Broadcast

/**
 * @Description:
 * 需求:将http.log文件中的ip转换为地址。如将 122.228.96.111 转为温州，并统计各城市的总访问量
 * http.log数据格式：时间戳、IP地址、访问网址、访问数据、浏览器信息
 * http.log数据样例：20090121000132095572000|125.213.100.123|show.51.com|/shoplist.php?phpfile=shoplist2.php&style=1&sex=137|Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Mozilla/4.0(Compatible Mozilla/4.0(Compatible-EmbeddedWB 14.59 http://bsalsa.com/ EmbeddedWB- 14.59  from: http://bsalsa.com/ )|http://show.51.com/main.php|
 * @Author zj
 * @Time
 */
object ipAdress {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ipAdress").setMaster("local[4]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("warn")

    val httpLines: RDD[String] = sc.textFile("data/http.log")
    val ipLines: RDD[String] = sc.textFile("data/ip.dat")

    // 解析ip.dat数据：（开始ip,结束ip,网段所属城市）
    val ipAddrRDD = ipLines.map { line =>
      val fields = line.split("\\|")
      (fields(2), fields(3), fields(7))
    }
//    使用广播变量将ip信息加载到内存中
  val broadcast= sc.broadcast(ipAddrRDD)
    // 解析http.log数据：(ip, 一行数据)
    val httpRDD = httpLines.map { line =>
      val fields = line.split("\\|")
      (fields(1), line)
    }

    // ip去重
    val logIpRdd = httpRDD.map(_._1).distinct()


  }

}


case class Ip(startIp: Long, endIp: Long, address: String)