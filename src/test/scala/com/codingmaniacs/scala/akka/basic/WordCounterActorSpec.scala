package com.codingmaniacs.scala.akka.basic

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import akka.util.Timeout
import org.specs2.matcher.Matchers
import org.specs2.mutable.SpecificationLike
import org.specs2.specification.AfterAll

import scala.concurrent.duration._

class WordCounterActorSpec extends TestKit(ActorSystem("WordCounterActorSpec")) with ImplicitSender
  with SpecificationLike with Matchers with AfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "A Word Counter Actor" should {

    val actorProps = Props(new WordCounterActor("src/test/resources/fake-file.txt"))
    val actor = system.actorOf(actorProps, "actor-to-test")

    "count the number of words on the given text file" in {
      implicit val timeout: Timeout = Timeout(25 seconds)
      actor ! StartProcessFileMsg()
      expectMsg(10)
      success
    }
  }
}
