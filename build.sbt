import play.Project._

name := """reactive-java8-play"""

version := "1.0-SNAPSHOT"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.0",
  "org.webjars.bower" % "bootstrap" % "3.3.5",
  "org.projectlombok" % "lombok" % "1.16.2")

playJavaSettings

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}

//fork in run := true