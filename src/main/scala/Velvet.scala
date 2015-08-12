package ohnosequencesBundles.statika

import ohnosequences.statika._, bundles._, instructions._
import java.io.File

// Abstract bundle, which compiles velvet with given paraeters
abstract class Velvet(
  val categories: Int,
  val maxKmerLength: Int,
  val bigAssembly: Boolean,
  val longSequences: Boolean,
  val openMP: Boolean,
  val version: String
) extends Bundle (cdevel, compressinglibs) {

  import ammonite.ops._
  val wd = cwd
  lazy val folder = wd/"velvet"

  def yum(names: String*): Results = Seq("yum", "install", "-y") ++ names

  final def install: Results = {

    Seq("wget", s"http://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/velvet/${version}/velvet_${version}.tgz") ->-
    Seq("tar", "-xvf", s"velvet_${version}.tgz" ) ->- {
      val velvetDir: String = new File(s"velvet_${version}").getAbsolutePath.toString
      println(velvetDir)
      (Seq("make", "-C", velvetDir, "-W", "doc", "-W", "Manual.pdf" // no docs, please
        // setting up make parameters:
        , "CATEGORIES="+categories+""
        , "MAXKMERLENGTH="+maxKmerLength+""
        ) ++ ( if (bigAssembly)   Seq("BIGASSEMBLY=1") else Seq() )
          ++ ( if (longSequences) Seq("LONGSEQUENCES=1") else Seq() )
          ++ ( if (openMP)        Seq("OPENMP=1") else Seq() )
      )  -&-
      Seq("cp", s"${velvetDir}/velvetg", s"${velvetDir}/velveth", "/usr/bin/")
    } ->-
    success(s"${bundleName} is installed")
  }
}
