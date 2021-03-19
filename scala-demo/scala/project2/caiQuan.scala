package project2

import scala.io.StdIn

class caiQuan {



    private var bot: Bot = _
    private var count: Int = 0
    private var botScore, playerScore: Int = 0
    private val FINGERS = Array("剪刀", "石头", "布")

    def start() = {
      progressChoiceBot()
      progressGame()
      progressSummary()
    }

    private def progressChoiceBot() = {
      var flag = true
      while (flag) {
        println(s"请选择对战角色：${Bot.choicesList}")
        var message = StdIn.readLine()
        val result = choiceBot(message.toInt)
        if (result) {
          println("要开始吗？y/n")
          message = StdIn.readLine()
          if(message == "y") {
            flag = false
          }
        }
      }
    }

    private def progressGame(): Unit = {
      var flag = true
      while(flag) {
        println("请出拳！1.剪刀 2.石头 3.布")
        var message = StdIn.readLine()
        val playerFinger = message match {
          case x if x.toInt < 1 || x.toInt > 3 => {
            println("输入不符合规范，默认出布")
            "布"
          }
          case _=> FINGERS(message.toInt - 1)
        }

        println(s"你出拳: ${playerFinger}")
        val botFinger = FINGERS(bot.putFinger - 1)
        println(s"${bot.name}出拳: $botFinger")

        checkFinger(playerFinger, botFinger) match {
          case "win" => {
            println("恭喜，你赢了！")
            playerScore += 2
          }
          case "lose" => {
            println("哦呦，你输了！")
            botScore += 2
          }
          case _=> {
            println("平局，下次继续努力。")
            playerScore += 1
            botScore += 1
          }
        }
        count += 1

        println("是否开启下一轮? y/n")
        message = StdIn.readLine()
        if (message == "n") {
          flag = false
        }
      }
    }

    private def progressSummary() = {
      println("退出游戏！\n----------------------------")
      println(s"${bot.name} VS 游客")
      println(s"对战次数: ${count}次，${bot.name}得分${botScore}，你得分${playerScore}")
    }

    private def checkFinger(playerFinger: String, botFinger: String) = {
      (playerFinger, botFinger) match {
        case (x,y) if x == y => "tied"
        case ("剪刀", "石头") |
             ("石头", "布") |
             ("布", "剪刀") => "lose"
        case _ => "win"
      }
    }

    private def choiceBot(choice:Int): Boolean = {
      try {
        bot = Bot(choice)
      } catch {
        case _=> {
          println("输入无效")
          return false
        }
      }
      println(s"你选择了与${bot.name}对战")
      true
    }

  }






