package project3

object App {
  def main(args: Array[String]): Unit = {
    val testData = "UserA,LocationA,8,60\n" +
      "UserA,LocationA,9,60\n" +
      "UserB,LocationB,10,60\n" +
      "UserB,LocationB,11,80"

    val lines = testData.split("\n")

    val analyzer = new Analyzer(lines)
    val logs = analyzer.merge()
    logs.map(_.display).foreach(println)
  }
}
