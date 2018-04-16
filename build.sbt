name := """play-scala-seed"""
organization := "com.scienaptic"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"

val circeVersion = "0.9.3"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

libraryDependencies += "com.dripower" %% "play-circe" % "2609.1"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.scienaptic.binders._"
