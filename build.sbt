val kompicsVersion = "1.2.1"
val kompicsScalaVersion = "2.0.0"


name := "id2203-exercises"

organization := "se.kth.edx"

scalaVersion := "2.13.1"

version := kompicsVersion

scalacOptions ++= Seq("-deprecation","-feature")

resolvers += Resolver.sonatypeRepo("releases")


libraryDependencies += "se.sics.kompics" % "kompics-core" % kompicsVersion
libraryDependencies += "se.sics.kompics" %% "kompics-scala" % kompicsScalaVersion
libraryDependencies += "se.sics.kompics" %% "kompics-scala-simulator" % kompicsScalaVersion
libraryDependencies += "se.sics.kompics.basic" % "kompics-component-java-timer" % kompicsVersion
libraryDependencies += "se.sics.kompics.basic" % "kompics-port-network" % kompicsVersion
libraryDependencies += "se.sics.kompics.basic" % "kompics-component-netty-network" % kompicsVersion
libraryDependencies += "com.michaelpollmeier" %% "scala-arm" % "2.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.0" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "0.9.28" % "test"
libraryDependencies ++= Seq("org.slf4j" % "slf4j-api" % "1.7.5", "org.slf4j" % "slf4j-simple" % "1.7.5")