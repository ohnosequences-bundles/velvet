package ohnosequencesBundles.statika

import ohnosequences.statika._, bundles._, instructions._

// Abstract bundle, which compiles velvet with given paraeters
abstract class Velvet(
  val categories: Int,
  val maxKmerLength: Int,
  val bigAssembly: Boolean,
  val longSequences: Boolean,
  val openMP: Boolean
) extends Bundle {

  import ammonite.ops._
  val wd = cwd
  lazy val folder = wd/"velvet"

  def yum(names: String*): Results = Seq("yum", "install", "-y") ++ names

  final def install: Results = {

    val velvet = new java.io.File("velvet")

    yum("git", "zlib-devel", "gcc") -&-
    Seq("git", "clone", "git://github.com/dzerbino/velvet.git") -&-
    (Seq("make", "-W", "doc", "-W", "Manual.pdf" // no docs, please
      // setting up make parameters:
      , "CATEGORIES="+categories+""
      , "MAXKMERLENGTH="+maxKmerLength+""
      ) ++ ( if (bigAssembly)   Seq("BIGASSEMBLY=1") else Seq() )
        ++ ( if (longSequences) Seq("LONGSEQUENCES=1") else Seq() )
        ++ ( if (openMP)        Seq("OPENMP=1") else Seq() )
    ) @@ velvet -&-
    Seq("cp", "velvetg", "velveth", "/usr/bin/") @@ velvet ->-
    success(s"${bundleName} is installed")
  }
}

// bundle with default parameters



