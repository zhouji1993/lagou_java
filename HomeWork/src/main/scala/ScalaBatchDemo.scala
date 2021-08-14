import org.apache.flink.api.scala.{AggregateDataSet, DataSet, ExecutionEnvironment, createTypeInformation}


object ScalaBatchDemo {
  def main(args: Array[String]): Unit = {
    //读取数据
    val inputPath = "word.txt"
    val outputPath = "wordCountOut"
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val data: DataSet[String] = env.readTextFile(inputPath)

    //处理数据
    val result: AggregateDataSet[(String, Int)] = data.flatMap(_.split(" ")).map((_, 1)).groupBy(0).sum(1)
    result.writeAsCsv(outputPath)
    result.print()
    //    env.execute()
  }
}