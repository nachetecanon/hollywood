#### [0.12.1](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.12.1) (03-09-2018) [#36](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/36/overview) [[PXCORE-75](https://jira.predictx.com/browse/PXCORE-75)] Move hollywood-service resources to px-platform namespace

### [0.12.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.12.0) (30-08-2018) [#34](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/34/overview) [[PIPHW-456](https://jira.predictx.com/browse/PIPHW-456)] should delete a dashboard

+ Delete a dashboard on hollywood

#### [0.11.5](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.11.5) (24-08-2018) [#33](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/33/overview) [[PXCORE-40](https://jira.predictx.com/browse/PXCORE-40)] Move integration tests to a separate module.

#### [0.11.4](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.11.4) (21-08-2018) [#32](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/32/overview) [[PXCORE-41](https://jira.predictx.com/browse/PXCORE-41)] move karate tests to e2e test module.

* [[PXCORE-41](https://jira.predictx.com/browse/PXCORE-41)] Introduce e2e test module.

#### [0.11.3](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.11.3) (20-08-2018) [#31](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/31/overview) [[PXCORE-44](https://jira.predictx.com/browse/PXCORE-44)] Add deployment to GCP/DEV environment.

#### [0.11.2](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.11.2) (16-08-2018) [#30](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/30/overview) [[PXCORE-39](https://jira.predictx.com/browse/PXCORE-39)] move hollywood service src to app folder

#### [0.11.1](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.11.1) (16-08-2018) [#29](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/29/overview) [[PXCORE-42](https://jira.predictx.com/browse/PXCORE-42)] Replace maven with gradle.

### [0.11.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.11.0) (14-08-2018) [#27](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/27/overview) [[AUTHX-268](https://jira.predictx.com/browse/AUTHX-268)] filter dashboard

* [[AUTHX-268](https://jira.predictx.com/browse/AUTHX-268)] move to spring-boot2
* [[AUTHX-268](https://jira.predictx.com/browse/AUTHX-268)] add AuthX service and filter dashboards

### [0.10.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.10.0) (06-07-2018) [#28](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/28/overview) [[PIPHW-306](https://jira.predictx.com/browse/PIPHW-306)] add a type for widget and put null the name

+ Change jenckinsfile with the new configuration

#### [0.9.2](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.9.2) (07-06-2018) [#26](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/26/overview) [*no issue*] Added SonarQube and detekt config default file

#### [0.9.1](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.9.1) (21-05-2018) [#24](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/24/overview) [[AUTHX-240](https://jira.predictx.com/browse/AUTHX-240)] fix issues with authx-client-library

* bad authorization header in authx-service requests
* fix docker file debug (adjust debug ports)

### [0.9.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.9.0) (18-05-2018) [#22](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/22/overview) [[PIPHW-154](https://jira.predictx.com/browse/PIPHW-154)] implement auditing

Implement auditing of date of creation on dashboard and also the lastModifiedDate

#### [0.8.3](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.8.3) (18-05-2018) [#23](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/23/overview) [[AUTHX-240](https://jira.predictx.com/browse/AUTHX-240)] move access authorization logic out of hollywood-service api

* move out of hollywood-service codebase all authorization code
* refactor some tests

#### [0.8.2](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.8.2) (08-05-2018) [#21](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/21/overview) [[AUTHX-238](https://jira.predictx.com/browse/AUTHX-238)] hollywood-service meets authx-backend-client-library

* experimental integration between hollywood-service and authx
* authorize service access using authx (through authx-backend-client-library)
* full integration with authx-backend-client-library but use authx-services api services mocked using spring-cloud-contract
* unit tests
* integration tests
* integration in karate test class

#### [0.8.1](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.8.1) (21-11-2017) [#20](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/20/overview) [[ZRKCRI-365](https://jira.pibenchmark.com/browse/ZRKCRI-365)] add link paramater

Add link paramater to the dashboard entity.

### [0.8.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.8.0) (14-11-2017) [#19](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/19/overview) [[ZRKCRI-342](https://jira.pibenchmark.com/browse/ZRKCRI-342)] add hidden field to dashboard entity

+ Add hidden field to dasboard entity.
+ Update domain objects

#### [0.7.3](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.7.3) (06-11-2017) [#18](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/18/overview) [[ZRKCRI-274](https://jira.pibenchmark.com/browse/ZRKCRI-274)] - Added images to a dashboard

#### [0.7.2](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.7.2) (30-10-2017) [#16](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/16/overview) [[PIP-435](https://jira.pibenchmark.com/browse/PIP-435)] add parameter app

#### [0.7.1](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.7.1) (02-10-2017) [#15](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/15/overview) [[BAF-168](https://jira.pibenchmark.com/browse/BAF-168)] move test to backend service repository

* BAF 168 - Move net.pi.platform.hollywood.karate tests to backend repository
* Fixed path problems

### [0.7.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.7.0) (28-09-2017) [#14](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/14/overview) [[PIP-334](https://jira.pibenchmark.com/browse/PIP-334)] change dashboard entity on backend

[PIP-334] Add bookmarked to Dashboard.

### [0.6.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.6.0) (27-09-2017) [#13](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/13/overview) [[PIP-326](https://jira.pibenchmark.com/browse/PIP-326)] Update Dashboard Entity + Refactor + Integration Test Fix.

[PIP-326] Update Dashboard Entity + Refactor + Integration Test Fix.

#### [0.5.2](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.5.2) (14-09-2017) [#12](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/12/overview) [*no issue*] 280 put limit of charac

* Put limit of characters on name of widget and dashboard

#### [0.5.1](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.5.1) (14-09-2017) [#11](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/11/overview) [[PIP-247](https://jira.pibenchmark.com/browse/PIP-247)] Adding Keycloack authentication:

-Enabling Keycloak authentication
 -Tests adapted to add the authentication header
 -Added ClientHttpRequestInterceptor to test authentication
 -Kotlin refactor to improve the code quality
 -Tests ignored to avoid dependencies issue; it will be fixed soon

### [0.5.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.5.0) (14-09-2017) [#9](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/9/overview) [[PIP-159](https://jira.pibenchmark.com/browse/PIP-159)] create PUT endpoint to update dashboards

- New endpoint
- Edit Services (include javadoc, refactor to save/create/update methods)
- Unit / IT tests
- Include a proper README

#### [0.4.1](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.4.1) (13-09-2017) [#8](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/8/overview) [[PIP-216](https://jira.pibenchmark.com/browse/PIP-216)] add visualization parameter to Widget Entity

* Add the vidualization parameter
* Add the new object with this parameter to test

### [0.4.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.4.0) (05-09-2017) [#7](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/7/overview) [[PIP-222](https://jira.pibenchmark.com/browse/PIP-222)] fix dont dave dashboard with id

+ Add test that validates that when you send a id with same value
+ Check in the service that the id is null

### [0.3.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.3.0) (31-08-2017) [#6](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/6/overview) [[PIP-187](https://jira.pibenchmark.com/browse/PIP-187)] Add spring-error library

In order to see better what error appears on application, it will be added Spring Error library.

### [0.2.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.2.0) (31-08-2017) [#4](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/4/overview) [[PIP-156](https://jira.pibenchmark.com/browse/PIP-156)] Create an api endpoint to list dashboards

* Create endpoint to list all dashboads entities.
* Add a controller that list all dashboards.
* Add a service method to enable dashboard listing.

#### [0.1.1](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.1.1) (30-08-2017) [#5](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/5/overview) [[PIP-156](https://jira.pibenchmark.com/browse/PIP-156)] change the name of widgetPosition

Change the name of widget position
Put documentation of the  WidgetPosition

### [0.1.0](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.1.0) (30-08-2017) [#3](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/3/overview) [[PIP-148](https://jira.pibenchmark.com/browse/PIP-148)] Refactor  the  java to kotlin

Change all package to kotlin

#### [0.0.2](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.0.2) (25-08-2017) [#2](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/2/overview) [[PIP-139](https://jira.pibenchmark.com/browse/PIP-139)] add simple dashboard entities in java

+Create a simple project in java for save one dataset
+Add config server dependency and config server dependency
+add docker-compose
+add dockerfile
+add integration test to save one dashboard

#### [0.0.1](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/browse?at=refs%2Ftags%2Fv0.0.1) (24-08-2017) [#1](https://bitbucket.pibenchmark.com/projects/HOL/repos/hollywood-service/pull-requests/1/overview) [[PIP-139](https://jira.pibenchmark.com/browse/PIP-139)] add simple project

Add simpel project

