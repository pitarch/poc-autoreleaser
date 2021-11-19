#!/usr/bin/env bash

GIT=`which git` || echo "git not found" && exit 1
SBT=`which sbt` || echo "sbt not found" && exit 1

$SBT "release with-defaults"
$GIT checkout staging
$GIT merge production
$GIT push
