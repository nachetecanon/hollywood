#!/usr/bin/env groovy

@Library('predictx-ci@develop') _

def job = new  main.groovy.net.predictx.flow.MavenFlow(this)

job.exec( {
  job.checkoutCode()

  if (!job.delegateMerge()) {

    job.mvnCleanVerify(false,'target/surefire-reports/TEST-*.xml','','target/cucumber','continuous-int')

    job.sonarQubeAnalysis(true)

    job.dockerBuild()

    job.dockerPush()
  }
})
