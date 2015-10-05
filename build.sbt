Nice.scalaProject

name := "velvet"
organization := "ohnosequences-bundles"
description := "A bundle for velvet tool"

publishBucketSuffix := "era7.com"

resolvers := Seq(
  "Era7 public maven releases"  at s3("releases.era7.com").toHttps(s3region.value.toString),
  "Era7 public maven snapshots" at s3("snapshots.era7.com").toHttps(s3region.value.toString)
)

libraryDependencies ++= Seq(
	"ohnosequences"           %% "statika"               % "2.0.0-new-instructions-SNAPSHOT",
  "ohnosequences-bundles"   %% "cdevel"                % "0.3.0-SNAPSHOT",
  "ohnosequences-bundles"   %% "compressinglibs"       % "0.3.0-SNAPSHOT",
  "org.scalatest"           %% "scalatest"             % "2.2.5"           % Test
)

dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
