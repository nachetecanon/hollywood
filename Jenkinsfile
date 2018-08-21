#!/usr/bin/env groovy

@Library('predictx-ci@develop') _

def job = new  main.groovy.net.predictx.flow.GradleFlow(this)

job.exec( {
  job.checkoutCode()

  if (!job.delegateMerge()) {

    stage("gradleCleanUnitTest") {
      job.gradle "clean build"
    }

    job.sonarQubeAnalysis(true)

    job.dockerBuild(null, "app")

    job.e2eTest()

    job.dockerPush(["NEXUS", "GCLOUD_EU"])

    job.deploy("dev")
  }
})
