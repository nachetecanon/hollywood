#!/usr/bin/env groovy

@Library('predictx-ci@develop') _

net.predictx.flow.Flow.setProperties(this)

node('master') {

  def job = new net.predictx.flow.MavenFlow(this)

  job.checkoutCode()

  if (!job.delegateMerge()) {

     job.mvnCleanPackage()

     job.dockerBuild()

     job.dockerPush()

  }
}
