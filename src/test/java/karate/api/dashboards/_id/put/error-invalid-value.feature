@api @dashboards @_id @put @error-invalid-value
Feature: Try to update a dashboard with invalid mandatory parameters
  In order to manage dashboards
  As a BA
  I want to update a dashboard with missing mandatory parameters

  Background:
    * def result = callonce read('classpath:api/dashboards/post/create-dashboard.feature')

  Scenario: Update a dashboard with too short parameter dashboard name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer ' + auth.token
    And set validDashboardRequest.name = "123"
    And request validDashboardRequest
    When method put
    Then status 400
    And match response.errorMessage == 'Constraint validation failure in input message'
    And match response.errorCode == 'invalid.input'

  Scenario: Update a dashboard with too long parameter dashboard name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer ' + auth.token
    And set validDashboardRequest.widgets[0].name = "1234567890123456789012345678901234567890123456789012345678901"
    And request validDashboardRequest
    When method put
    Then status 400
    And match response.errorMessage == 'Constraint validation failure in input message'
    And match response.errorCode == 'invalid.input'

  Scenario: Update a dashboard with too short parameter dashboard widget name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer ' + auth.token
    And set validDashboardRequest.widgets[0].name = "123"
    And request validDashboardRequest
    When method put
    Then status 400
    And match response.errorMessage == 'Constraint validation failure in input message'
    And match response.errorCode == 'invalid.input'

  Scenario: Update a dashboard with too long parameter dashboard widget name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer ' + auth.token
    And set validDashboardRequest.widgets[0].name = "1234567890123456789012345678901234567890123456789012345678901"
    And request validDashboardRequest
    When method put
    Then status 400
    And match response.errorMessage == 'Constraint validation failure in input message'
    And match response.errorCode == 'invalid.input'


  Scenario: Update a dashboard with invalid null parameter dashboard name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer ' + auth.token
    And set validDashboardRequest.name = null
    And request validDashboardRequest
    When method put
    Then status 400
    And match response.errorMessage == 'There is some wrong value in some input field'
    And match response.errorCode == 'invalid.input'


  Scenario: Update a dashboard with invalid null mandatory parameter dashboard widget name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer ' + auth.token
    And set validDashboardRequest.widgets[0].name = null
    And request validDashboardRequest
    When method put
    Then status 400
    And match response.errorMessage == 'There is some wrong value in some input field'
    And match response.errorCode == 'invalid.input'