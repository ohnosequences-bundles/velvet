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
	"ohnosequences"          %% "statika"               % "2.0.0-SNAPSHOT",
  "ohnosequencesBundles"   %% "cdevel"                % "0.1.0-SNAPSHOT",
  "ohnosequencesBundles"   %% "compressinglibs"       % "0.1.0",
  "org.scalatest"          %% "scalatest"             % "2.2.5"           % Test
)

dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "1.0.4"
