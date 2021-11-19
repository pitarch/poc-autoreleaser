#!/usr/bin/env bash

sbt "release with-defaults"
git checkout staging
git merge production
git push
