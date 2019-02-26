name := "Akka Playground"

organization := "com.codingmaniacs.scala.akka"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.8"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.21"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.specs2" %% "specs2-core" % "4.0.3" % Test

logBuffered in Test := false

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

parallelExecution in ThisBuild := false