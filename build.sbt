ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "JsonPlaceHolderDownloader"
  )

val requestsVersion = "0.7.1"
val json4sVersion = "4.0.5"

val scalaTest = "3.2.12"
val scalaMock = "5.2.0"

libraryDependencies += "com.lihaoyi" %% "requests" % requestsVersion

libraryDependencies += "org.json4s" %% "json4s-jackson" % json4sVersion

libraryDependencies += "org.scalamock" %% "scalamock" % scalaMock % Test
libraryDependencies += "org.scalatest" %% "scalatest" % scalaTest % Test



