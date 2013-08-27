package ohnosequences.statika

import ohnosequences.statika._
import sys.process._

abstract class VelvetOpts(
    categories: Int = 2
  , maxKmerLength: Int = 31
  , bigAssembly: Boolean = false
  , longSequences: Boolean = false
  , openMP: Boolean = false
  ) 
  extends Bundle(Git :: GCC :: ZlibDevel :: HNil) {

  // Adding method to run commands from a given path
  implicit class PBAt(cmd: Seq[String]) {
    implicit def @@(path: String): ProcessBuilder =
      Process(cmd, new java.io.File(path), "" -> "")
  }

  def install[D <: DistributionAux](distribution: D): InstallResults = {

    val trace = List[ProcessBuilder](
      Seq("git", "clone", "git://github.com/dzerbino/velvet.git")
    , (Seq("make", "-W", "doc", "-W", "Manual.pdf" // no docs, please
        // setting up make parameters:
        , "'CATEGORIES="+categories+"'"
        , "'MAXKMERLENGTH="+maxKmerLength+"'"
        ) ++ ( if (bigAssembly)   Seq("'BIGASSEMBLY=1'") else Seq() ) 
          ++ ( if (longSequences) Seq("'LONGSEQUENCES=1'") else Seq() )
          ++ ( if (openMP)        Seq("'OPENMP=1'") else Seq() )
      ) @@ "velvet"
    , Seq("cp", "velvetg", "velveth", "/usr/bin/") @@ "velvet"
    ).
    foldLeft(List[InstallResult]()){ (acc, cmd) => 
      if (acc exists (_.isLeft)) acc
      else if (cmd.! != 0) acc ::: failure(cmd.toString)
      else acc ::: success(cmd.toString)
    }

    if (trace exists (_.isLeft)) trace
    else success("%s is installed" format metadata)

  }

}

// bundle with default parameters
case object Velvet extends VelvetOpts {
  val metadata = generated.metadata.Velvet
}
