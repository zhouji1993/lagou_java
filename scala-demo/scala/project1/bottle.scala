package project1

object bottle {

  def main(args: Array[String]): Unit = {
    // 第一次买共几瓶
    var one: Int = 100 / 2;
    println(s"总喝瓶数：${one + process(0, one, one)}")
  }

  /*
   * 总数，上次剩余瓶子，上次剩余瓶盖
   */
  def process(sum: Int, bottle: Int, cap: Int): Int = {
    if (bottle < 3 && cap < 5) return sum
    var a1 = bottle / 3
    var a2 = bottle % 3
    var b1 = cap / 5
    var b2 = cap % 5
    println(f"总数=${a1 + b1 + sum} 上次剩余瓶子=${a1 + b1 + a2}%02d 上次剩余瓶盖=${a1 + b1 + b2}%02d")
    return process(a1 + b1 + sum, a1 + b1 + a2, a1 + b1 + b2)
  }


}
