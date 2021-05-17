package cn.lagou.homework.object5


import java.sql._
import java.util.Properties

import cn.lagou.homework.object5.BuseInfo
import org.apache.spark.sql.ForeachWriter


class JdbcWriter extends ForeachWriter[BuseInfo] {

  var conn: Connection = _
  var statement: PreparedStatement = _

  // 开启链接
  override def open(partitionId: Long, epochId: Long): Boolean = {
    if (conn == null) {
      conn = JdbcWriter.openConnection
    }
    true
  }

  // 关闭链接
  override def close(errorOrNull: Throwable): Unit = {
    if (null != conn) conn.close()
    if (null != statement) statement.close()
  }

  // 新增数据
  override def process(value: BuseInfo): Unit = {
    val arr: Array[String] = value.lglat.split("_")
    val sql = "insert into car_gps(deployNum,plateNum,timeStr,lng,lat) values(?,?,?,?,?)"
    statement = conn.prepareStatement(sql)
    statement.setString(1, value.deployNum)
    statement.setString(2, value.plateNum)
    statement.setString(3, value.timeStr)
    statement.setString(4, arr(0))
    statement.setString(5, arr(1))
    statement.executeUpdate()
  }


}

object JdbcWriter {
  // 初始化链接
  var conn: Connection = _
  val url = "jdbc:mysql://hadoop1:3306/lg_logstic?useUnicode=true&characterEncoding=utf8"
  val username = "root"
  val password = "123456"
  def openConnection: Connection = {
    if (null == conn || conn.isClosed) {
      val p = new Properties
      Class.forName("com.mysql.jdbc.Driver")
      conn = DriverManager.getConnection(url, username, password)
    }
    conn
  }
}
