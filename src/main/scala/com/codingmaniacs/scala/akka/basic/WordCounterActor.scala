package com.codingmaniacs.scala.akka.basic

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

case class StartProcessFileMsg()

class WordCounterActor(filename: String) extends Actor with ActorLogging {

  private var running = false
  private var totalLines = 0
  private var linesProcessed = 0
  private var result = 0
  private var fileSender: Option[ActorRef] = None

  def receive: Receive = {
    case StartProcessFileMsg() => {
      if (running) {
        log.warning("Warning: duplicate start message received")
      } else {
        running = true
        fileSender = Some(sender) // save reference to process invoker
        import scala.io.Source._
        fromFile(filename).getLines.foreach { line =>
          context.actorOf(Props[LineCounterActor]) ! ProcessStringMsg(line)
          totalLines += 1
        }
      }
    }
    case StringProcessedMsg(words) => {
      result += words
      linesProcessed += 1
      if (linesProcessed == totalLines) {
        fileSender.foreach(_ ! result) // provide result to process invoker
      }
    }
    case _ => log.error("message not recognized!")
  }
}