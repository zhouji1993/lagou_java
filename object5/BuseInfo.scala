package cn.lagou.homework.object5
case class BuseInfo(
                    deployNum: String,
                    simNum: String,
                    transportNum: String,
                    plateNum: String,
                    lglat: String,
                    speed: String,
                    direction: String,
                    mileage: String,
                    timeStr: String,
                    oilRemain: String,
                    weights: String,
                    acc: String,
                    locate: String,
                    oilWay: String,
                    electric: String
                  )

object BuseInfo {

  def apply(msg: String): BuseInfo = {
    val arr: Array[String] = msg.split(",")
    if (arr.length == 15) {
      BuseInfo(
        arr(0),
        arr(1),
        arr(2),
        arr(3),
        arr(4),
        arr(5),
        arr(6),
        arr(7),
        arr(8),
        arr(9),
        arr(10),
        arr(11),
        arr(12),
        arr(13),
        arr(14)
      )
    }else{
      null
    }



}