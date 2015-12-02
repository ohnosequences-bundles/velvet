package ohnosequencesBundles.statika

import ohnosequences.statika._, bundles._, instructions._
import java.io.File

abstract class MetaVelvet(
  val version: String,
  val maxKmerLength: Int,
  val categories: Int
)(velvet: Velvet) extends Bundle(cdevel, compressinglibs, velvet) { metaVelvet =>

  val name: String = "MetaVelvet-" + version
  val tgz: String = metaVelvet.name + ".tgz"

  lazy val download: CmdInstructions = cmd("wget")(
    s"http://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/MetaVelvet/${metaVelvet.tgz}"
  )

  lazy val untar: CmdInstructions = cmd("tar")("-xvzf", metaVelvet.tgz)

  lazy val make: CmdInstructions = cmd("make")(
    "-C", metaVelvet.name,
    "-W", "doc", // no docs, please
    "-W", "Manual.pdf",
    s"CATEGORIES=${categories}",
    s"MAXKMERLENGTH=${maxKmerLength}"
  )

  def link(binary: String) = cmd("ln")("-s",
    new File(metaVelvet.name, binary).getCanonicalPath,
    s"/usr/bin/${binary}"
  )

  def instructions: AnyInstructions =
    download -&- untar -&- make -&- link("meta-velvetg")
}
