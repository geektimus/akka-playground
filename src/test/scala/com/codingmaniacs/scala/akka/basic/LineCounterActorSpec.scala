package com.codingmaniacs.scala.akka.basic

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.specs2.matcher.Matchers
import org.specs2.mutable.SpecificationLike
import org.specs2.specification.AfterAll

class LineCounterActorSpec extends TestKit(ActorSystem("LineCounterActorSpec")) with ImplicitSender
  with SpecificationLike with Matchers with AfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "A Line Counter Actor" should {

    val actorProps = Props(new LineCounterActor())
    val actor = system.actorOf(actorProps, "actor-to-test")

    val firstLine = ProcessStringMsg("Yo, I am the first line on the test file")
    val secondLine = ProcessStringMsg("Hey, I am the second line on the test file")

    "count the number of words on the given line of text" in {
      actor ! firstLine
      expectMsg(StringProcessedMsg(10))
      actor ! secondLine
      expectMsg(StringProcessedMsg(10))
      success
    }
  }
}
