# Hollywood Service API

[![Build Status](https://jenkins.pibenchmark.com/buildStatus/icon?job=HOL/hollywood-service/develop)](https://jenkins.pibenchmark.com/job/HOL/job/hollywood-service/job/develop/)

Hollywood Service API is Backend API for Hollywood component

Feel free to check specific details in Hollywood Confluence resources:
* [Hollywood API Confluence Space](https://confluence.pibenchmark.com/display/PLATHOLLYWOOD/Hollywood+Service+API) 

## Team members

| Name             | Contact                  |
| -----------------|--------------------------|
| Jesus Liebana    | jesus.liebana@p-i.net    |
| Manu Somolinos   | manuel.somolinos@p-i.net |

## Packaging

Use maven:

```sh
mvn clean package
```
all the unit tests will be executed and the package won't be built if some of them fail.

## Testing

Make sure you have set up integration resources:
```sh
docker-compose -f docker-integration-test.yml up --build
```

Launch unit and integration tests:
```sh
mvn clean verify
```
