package ohnosequences.statika

import ohnosequences.statika._
import sys.process._

case object GCC extends YumBundle("gcc")
case object ZlibDevel extends YumBundle("zlib-devel")

abstract class VelvetOpts(
    categories: Int = 2
  , maxKmerLength: Int = 31
  , bigAssembly: Boolean = false
  , longSequences: Boolean = false
  , openMP: Boolean = false
  ) 
  extends Bundle(Git :+: GCC :+: ZlibDevel) {

  def install[D <: AnyDistribution](distribution: D): InstallResults = {

    val velvet = new java.io.File("velvet")

     (  Git.clone("git://github.com/dzerbino/velvet.git")
    -&- (Seq("make", "-W", "doc", "-W", "Manual.pdf" // no docs, please
          // setting up make parameters:
          , "CATEGORIES="+categories+""
          , "MAXKMERLENGTH="+maxKmerLength+""
          ) ++ ( if (bigAssembly)   Seq("BIGASSEMBLY=1") else Seq() ) 
            ++ ( if (longSequences) Seq("LONGSEQUENCES=1") else Seq() )
            ++ ( if (openMP)        Seq("OPENMP=1") else Seq() )
        ) @@ velvet
    -&- Seq("cp", "velvetg", "velveth", "/usr/bin/") @@ velvet
    ->- success(name + " is installed")
     )
  }

}

// bundle with default parameters
case object Velvet extends VelvetOpts
