# Hollywood Service API

[![Build Status](https://jenkins.pibenchmark.com/buildStatus/icon?job=HOL/hollywood-service/develop)](https://jenkins.pibenchmark.com/job/HOL/job/hollywood-service/job/develop/)

Hollywood Service API is Backend API for Hollywood component

Feel free to check specific details in Hollywood Confluence resources:
* [Hollywood API Confluence Space](https://confluence.pibenchmark.com/display/PLATHOLLYWOOD/Hollywood+Service+API) 


## AuthX Integration

![Sequence diagram](./doc/authx_dashboards_sequence.png)

## Development 
### Packaging

Use maven:

```sh
mvn clean package
```
all the unit tests will be executed and the package won't be built if some of them fail.

### Testing

Make sure you have set up integration resources:
```sh
docker-compose -f docker-compose-integration-test.yml up --build
```

Launch unit and integration tests:
```sh
mvn clean verify
```

### Startup Hollywood Service stack

There are two options, launch plain stack or launch stack with debug configuration:

1. Plain stack
```sh
docker-compose -f docker-compose.yml up --build
```
2. Stack with debug config
```sh
docker-compose -f docker-compose-debug.yml up --build
```
