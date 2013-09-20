name := "velvet"

description := "A bundle for velvet tool"

homepage := Some(url("https://github.com/ohnosequences/velvet"))

organization := "ohnosequences"

organizationHomepage := Some(url("http://ohnosequences.com"))

licenses := Seq("AGPLv3" -> url("http://www.gnu.org/licenses/agpl-3.0.txt"))

bundleObjects := Seq("ohnosequences.statika.Velvet")

libraryDependencies ++= Seq( 
  "ohnosequences" %% "git" % "0.7.0"
, "ohnosequences" %% "gcc" % "0.2.0"
, "ohnosequences" %% "zlib-devel" % "0.2.0"
) 
