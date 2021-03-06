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
	"ohnosequences"           %% "statika"         % "2.0.0-M5",
  "ohnosequences-bundles"   %% "cdevel"          % "0.4.0",
  "ohnosequences-bundles"   %% "compressinglibs" % "0.4.0",
  "org.scalatest"           %% "scalatest"       % "2.2.5" % Test
)
