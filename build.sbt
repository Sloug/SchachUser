name := "SchachUser"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"
libraryDependencies += "com.google.inject" % "guice" % "4.1.0"
libraryDependencies += "com.typesafe.akka" %% "akka-http"   % "10.1.12"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.5"
libraryDependencies += "de.heikoseeberger" %% "akka-http-play-json" % "1.32.0"

libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.1"
libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.26"
libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % "3.3.1"
libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.6"