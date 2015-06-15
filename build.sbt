Nice.scalaProject

name := "velvet"
organization := "ohnosequencesBundles"
description := "A bundle for velvet tool"

publishBucketSuffix := "era7.com"

resolvers := Seq(
  "Era7 public maven releases"  at s3("releases.era7.com").toHttps(s3region.value.toString),
  "Era7 public maven snapshots" at s3("snapshots.era7.com").toHttps(s3region.value.toString)
)

libraryDependencies += "ohnosequences" %% "statika" % "2.0.0-SNAPSHOT"
