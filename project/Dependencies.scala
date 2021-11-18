import sbt.Keys._
import sbt._
import sbtrelease.ReleasePlugin._
import sbtrelease.ReleasePlugin.autoImport.ReleaseKeys._
import sbtrelease.ReleasePlugin.autoImport._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.2.9"
}


object FraudioReleaseStateTransformations {

  import sbtrelease.ReleaseStateTransformations.reapply
  import sbtrelease.Utilities._

  type Versions = (String, String)

  lazy val setFraudioReleaseVersion: autoImport.ReleaseStep = setFraudioVersion(
    _._1
  )

  lazy val setFraudioNextVersion: autoImport.ReleaseStep = setFraudioVersion(
    _._2
  )

  private def setFraudioVersion(
                                 selectVersion: Versions => String
                               ): ReleaseStep = { st: State =>
    val vs = st
      .get(versions)
      .getOrElse(
        sys.error(
          "No versions are set! Was this release part executed before inquireVersions?"
        )
      )
    val selected = selectVersion(vs)

    st.log.info("Setting version to '%s'." format selected)
    val useGlobal = st.extract.get(releaseUseGlobalVersion)
    writeFraudioVersion(st, selected)

    reapply(
      Seq(
        if (useGlobal) ThisBuild / version := selected
        else version := selected
      ),
      st
    )
  }

  private def writeFraudioVersion(st: State, versionString: String): Unit = {
    val file = st.extract.get(releaseVersionFile)
    st.log.info("Writing new version '%s'." format versionString)
    IO.writeLines(file, Seq(versionString))
  }
}
