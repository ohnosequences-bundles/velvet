package ohnosequences.statika

import ohnosequences.statika._, bundles._, instructions._

// Abstract bundle, which compiles velvet with given paraeters
abstract class AbstractVelvet(
  categories: Int,
  maxKmerLength: Int,
  bigAssembly: Boolean,
  longSequences: Boolean,
  openMP: Boolean
) extends Bundle() {

  def yum(names: String*): Results = Seq("yum", "install", "-y") ++ names

  def install: Results = {

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
case object DefaultVelvet extends AbstractVelvet(
  categories = 2,
  maxKmerLength = 31,
  bigAssembly = false,
  longSequences = false,
  openMP = false
)
