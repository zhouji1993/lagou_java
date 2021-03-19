package project3

import scala.concurrent.duration.Duration

case class Log(name:String ,location:String ,startTime:Int ,var duration: Int){
  def display = s"$name,$location,$startTime,$duration"
}



class Analyzer(val lines: Array[String]) {

  def merge() = {
    val result = collection.mutable.Map[String, List[Log]]()

    lines.foreach(line => {
      val Array(name, location, startTime, duration) = line.split(",")
      val log = Log(name, location, startTime.toInt, duration.toInt)

      if(result.contains(name)) {
        val last = result(name).last

        // 在相同地点，且上一日志时间叠加等于当前开始时间
        if (last.location == log.location &&
          last.startTime + last.duration / 60 >= log.startTime) {
          last.duration = last.duration + log.duration
        } else {
          result(name) ::= log
        }
      } else {
        result(name) = List(log)
      }
    })

    result.values.flatten.toList
  }










}
