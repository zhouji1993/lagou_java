import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}



object ScalaStreamDemo {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val data: DataStream[String] = env.socketTextStream("linux121", 9999)

    val result: DataStream[(String, Int)] = data.flatMap(_.split(" ")).map((_, 1)).keyBy(_._1).sum(1)

    result.print()
    env.execute()
  }
}
