import Dependencies._
import ReleaseTransformations._

ThisBuild / scalaVersion := "2.13.7"
//ThisBuild / version := "0.0.1-SNAPSHOT"
ThisBuild / organization := "com.fraudio"
ThisBuild / organizationName := "Fraudio"

lazy val root = (project in file("."))
  .settings(
    name := "poc-autorelease",
    libraryDependencies += scalaTest % Test
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  setNextVersion,
  commitNextVersion,
  pushChanges
)
