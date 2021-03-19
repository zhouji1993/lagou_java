package project2

import scala.util.Random

class Bot(val name: String) {
  private val random = new Random()

  def putFinger = Math.abs(random.nextInt()) % 3 + 1
}

object Bot {
  def apply(choice: Int): Bot = {
    if (choice < 1 ||  choice > 3) {
      throw new IndexOutOfBoundsException
    }
    val name = CHOICES(choice-1)
    new Bot(name)
  }

  val CHOICES = Array("刘备", "关羽", "张飞")

  def choicesList() = {
    (1 to 3).zip(Bot.CHOICES)
      .map(item => s"${item._1}. ${item._2}")
      .mkString("\t")
  }
}