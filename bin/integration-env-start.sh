#!/usr/bin/env bash
# Jenkins BUILD TAG is not set, set it
if [ ! -v BUILD_TAG ]
then
    BUILD_TAG=RandomValue
fi
docker-compose -f docker-compose-integration-test.yml -p $BUILD_TAG down &&
docker-compose -f docker-compose-integration-test.yml -p $BUILD_TAG up -d