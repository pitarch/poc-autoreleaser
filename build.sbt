import Dependencies._

ThisBuild / scalaVersion := "2.12.13"
ThisBuild / organization := "com.fraudio"
ThisBuild / organizationName := "Fraudio"
ThisBuild / version := IO
  .read(file(baseDirectory.value.getPath + "/VERSION"))
  .trim

lazy val root = (project in file("."))
  .settings(
    name := "poc-autorelease",
    libraryDependencies += scalaTest % Test
  )
g
