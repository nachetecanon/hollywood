# Hollywood Service

[![Build Status](https://jenkins.pibenchmark.com/buildStatus/icon?job=HOL/hollywood-service/develop)](https://jenkins.pibenchmark.com/job/HOL/job/hollywood-service/job/develop/)

Hollywood Service API is Backend API for Hollywood component

Feel free to check specific details in Hollywood Confluence resources:
* [Hollywood API Confluence Space](https://confluence.pibenchmark.com/display/PLATHOLLYWOOD/Hollywood+Service+API) 


## Model

The service has 1 entity the dashboard. [Here the model](./src/main/kotlin/net/pi/platform/hollywood/model/Dashboard.kt)

## API

Here details of the available [API](./src/main/kotlin/net/pi/platform/hollywood/controller/DashboardController.kt)
## Architecture

 A self-contained service with its own storage mongoDB.
 
### AuthX Integration

 To be able to filter dashboards based on user authorization, we need to have:
  
  - configuration `auth.enabled` should be set to true.
  - configuration `authx.url` should be set to the base url of authX service
  - dashboards should be registered as resources in authX with the following path `/hollywood/dashboards/` .
  - User / dashboards should belongs to the same group. 

![Sequence diagram](./doc/authx_dashboards_sequence.png)

Check authX [documentation](https://bitbucket.pibenchmark.com/projects/AUTHX/repos/authx-service-kt/browse/README.md)

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
