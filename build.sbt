ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "JsonPlaceHolderDownloader"
  )

val requestsVersion = "0.7.1"
val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "requests" % requestsVersion,
)

libraryDependencies += "org.scalamock" %% "scalamock" % "5.2.0" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % Test

libraryDependencies += "org.json4s" %% "json4s-jackson" % "4.0.5"

