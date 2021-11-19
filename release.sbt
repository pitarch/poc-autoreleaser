import sbtrelease.ReleaseStateTransformations.{
  checkSnapshotDependencies,
  commitNextVersion,
  commitReleaseVersion,
  inquireVersions,
  pushChanges,
  runClean,
  tagRelease
}

releaseVersionFile := file(baseDirectory.value.getPath + "/VERSION")

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  FraudioReleaseStateTransformations.setFraudioReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  FraudioReleaseStateTransformations.setFraudioNextVersion,
  commitNextVersion,
  pushChanges
)
