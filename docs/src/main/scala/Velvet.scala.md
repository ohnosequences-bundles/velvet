
```scala
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
) extends Bundle (cdevel, compressinglibs) { velvet =>

  val name: String = "velvet_" + version
  val tgz: String = velvet.name + ".tgz"

  lazy val downloadVelvet: CmdInstructions = cmd("wget")(
    s"http://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/velvet/${version}/${velvet.tgz}"
  )

  lazy val untarVelvet: CmdInstructions = cmd("tar")("-xvzf", velvet.tgz)

  lazy val make: CmdInstructions = {
    val options = Seq(
      "-C", velvet.name,
      "-W", "doc", // no docs, please
      "-W", "Manual.pdf",
      s"CATEGORIES=${categories}",
      s"MAXKMERLENGTH=${maxKmerLength}"
    ) ++
    ( if (bigAssembly) Seq("BIGASSEMBLY=1") else Seq() ) ++
    ( if (longSequences) Seq("LONGSEQUENCES=1") else Seq() ) ++
    ( if (openMP) Seq("OPENMP=1") else Seq() )

    cmd("make")(options: _*)
  }

  def link(binary: String) = cmd("ln")("-s",
    new File(velvet.name, binary).getCanonicalPath,
    s"/usr/bin/${binary}"
  )

  def instructions: AnyInstructions =
    downloadVelvet -&- untarVelvet -&- make -&- link("velvetg") -&- link("velveth")
}

```




[main/scala/Velvet.scala]: Velvet.scala.md
[test/scala/velvetLocal.scala]: ../../test/scala/velvetLocal.scala.md