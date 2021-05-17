package cn.lagou.homework.object5

import cn.lagou.homework.object5.BuseInfo
import org.apache.spark.sql._


/*
*
CREATE TABLE car_gps(
id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
deployNum VARCHAR(255) COMMENT '调度编号',
plateNum VARCHAR(255) COMMENT '车牌号',
timeStr VARCHAR(255) COMMENT '时间戳',
lng VARCHAR(255) COMMENT '经度',
lat VARCHAR(255) COMMENT '纬度',
createTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='位置信息表'
;
* */

object homework {


  def main(args: Array[String]): Unit = {
    //1 获取sparksession
    val spark: SparkSession = SparkSession.builder()
      .master("local[*]")
      .appName(RealTimeProcess.getClass.getName)
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    import spark.implicits._
    //2 定义读取kafka数据源
    val kafkaDf: DataFrame = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "hadoop2:9092,hadoop3:9092")
      .option("subscribe", "lg_bus_info")
      .load()

    //3 处理数据
    val kafkaValDf: DataFrame = kafkaDf.selectExpr("CAST(value AS STRING)")
    //转为ds
    val kafkaDs: Dataset[String] = kafkaValDf.as[String]
    //封装为一个case class方便后续获取指定字段的数据
    val busInfoDs: Dataset[BuseInfo] = kafkaDs.map(BuseInfo(_)).filter(_ != null)

    //4 将数据写入MySQL表
    busInfoDs.writeStream
      .foreach(new JdbcWriter)
      .outputMode("append")
      .start()
      .awaitTermination()
  }
}