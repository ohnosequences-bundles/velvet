package ohnosequences.statika

import ohnosequences.statika._

case object Velvet extends Bundle(Git :: Gcc :: ZlibDevel :: HNil) {

  val metadata = generated.metadata.Velvet

  def install[D <: DistributionAux](distribution: D): InstallResults = {
    // do someting here
    success("%s is installed" format metadata)
  }

}
