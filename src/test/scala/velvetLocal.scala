package ohnosequences.statika.tests

class VelvetTests extends org.scalatest.FunSuite {

  test("local velvet install") {

    val version = "1.2.10"

    import scala.sys.process._
    import java.io.File

    Seq("wget", s"http://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/velvet/${version}/velvet_${version}.tgz").!

    Seq("tar", "-xvf", s"velvet_${version}.tgz" ).!


    val velvetDir: String = new File(s"velvet_${version}").getAbsolutePath.toString
    println(velvetDir)

    Seq("make", "-C", velvetDir, "-W", "doc", "-W", "Manual.pdf" // no docs, please
        // setting up make parameters:
        , "CATEGORIES=2"
        , "MAXKMERLENGTH=99"
    ).!
        // ++ ( if (bigAssembly)   Seq("BIGASSEMBLY=1") else Seq() )
        //   ++ ( if (longSequences) Seq("LONGSEQUENCES=1") else Seq() )
        //   ++ ( if (openMP)        Seq("OPENMP=1") else Seq() )

  }
}
