package project4

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import scala.concurrent.duration.DurationInt


case object Fist
case object Foot
case object Start

class AActor(name: String, opponent: ActorRef) extends Actor {
  var count = 0

  override def receive: Receive = {
    case Start =>
      println("AActor 出招了……\nstart, ok!")
      opponent ! Foot
    case Fist =>
      count = count + 1
      println(s"AActor($name)：厉害！看我佛山无影脚……第${count}脚")
      Thread.sleep(1000)
      opponent ! Foot
  }
}

class BActor(name: String) extends Actor {
  var count = 0

  override def receive: Receive = {
    case Foot =>
      count = count + 1
      println(s"BActor($name)：挺猛！看我降龙十八掌……第${count}掌")
      Thread.sleep(1000)
      sender() ! Fist
  }
}






object kongFu {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("kongfu")
    val bactor = system.actorOf(Props(classOf[BActor],"乔峰"),"bactor")
    val aactor = system.actorOf(Props(classOf[AActor],"黄飞鸿",bactor),"aactor")
    import system.dispatcher
    system.scheduler.scheduleOnce(500 millis) {
      aactor ! Start
    }
  }
}
