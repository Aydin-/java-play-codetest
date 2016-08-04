lazy val root = project.in(file(".")).enablePlugins(PlayJava)

name := "aydin-qudini-coding-test"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.7"

// https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-joda
libraryDependencies += "com.fasterxml.jackson.datatype" % "jackson-datatype-joda" % "2.8.1"
libraryDependencies ++= Seq(
  javaWs
)
// https://mvnrepository.com/artifact/org.mockito/mockito-all
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19"

libraryDependencies += "org.easytesting" % "fest-assert" % "1.4"