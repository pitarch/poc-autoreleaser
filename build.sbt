import Dependencies._
import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._


ThisBuild / scalaVersion := "2.13.7"
//ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / organization := "com.fraudio"
ThisBuild / organizationName := "Fraudio"
ThisBuild / version := IO
  .read(file(baseDirectory.value.getPath + "/VERSION"))
  .trim

lazy val root = (project in file("."))
  .settings(
    name := "poc-autorelease",
    releaseVersionFile := file(baseDirectory.value.getPath + "/VERSION"),
    libraryDependencies += scalaTest % Test
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  FraudioReleaseStateTransformations.setFraudioReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  FraudioReleaseStateTransformations.setFraudioNextVersion,
  commitNextVersion
  //pushChanges
)
