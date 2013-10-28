name := "velvet"

description := "A bundle for velvet tool"

homepage := Some(url("https://github.com/ohnosequences/velvet"))

organization := "ohnosequences"

organizationHomepage := Some(url("http://ohnosequences.com"))

licenses := Seq("AGPLv3" -> url("http://www.gnu.org/licenses/agpl-3.0.txt"))

libraryDependencies += "ohnosequences" %% "yum" % "0.1.0"
