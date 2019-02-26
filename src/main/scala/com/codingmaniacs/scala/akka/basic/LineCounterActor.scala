package com.codingmaniacs.scala.akka.basic

import akka.actor.{Actor, ActorLogging, ActorRef}

case class ProcessStringMsg(string: String)
case class StringProcessedMsg(words: Integer)

class LineCounterActor(listener: Option[ActorRef] = None) extends Actor with ActorLogging {
  override def receive : Receive = {
    case ProcessStringMsg(string) => {
      val wordsInLine = string.split(" ").length
      sender ! StringProcessedMsg(wordsInLine)
    }
    case _ => log.error("Error: message not recognized")
  }
}
