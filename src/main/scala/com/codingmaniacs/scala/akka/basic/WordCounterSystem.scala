package com.codingmaniacs.scala.akka.basic

import akka.actor.{ActorSystem, Props}
import akka.dispatch.ExecutionContexts._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._
import scala.language.postfixOps


object WordCounterSystem {

  implicit val executionContext: ExecutionContextExecutor = global

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("counterSystem")
    val actor = system.actorOf(Props(new WordCounterActor(args(0))))
    implicit val timeout: Timeout = Timeout(25 seconds)
    val future = actor ? StartProcessFileMsg()
    future.map { result =>
      println("Total number of words " + result)
      system.terminate
    }
  }
}
