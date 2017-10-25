@api @dashboards @_id @put @error-empty-value
Feature: Try to update a dashboard with empty mandatory parameters
  In order to manage dashboards
  As a BA
  I want to update a dashboard with missing mandatory parameters

  Background:
    * def result = callonce read('classpath:karate/api/dashboards/post/create-dashboard.feature')

  Scenario: Update a dashboard with missing empty parameter dashboard name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer ' + auth.token
    And set validDashboardRequest.name = ""
    And request validDashboardRequest
    When method put
    Then status 400
    And match response.errorMessage == 'Constraint validation failure in input message'
    And match response.errorCode == 'invalid.input'


  Scenario: Update a dashboard with empty mandatory parameter dashboard widget name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer ' + auth.token
    And set validDashboardRequest.widgets[0].name = ""
    And request validDashboardRequest
    When method put
    Then status 400
    And match response.errorMessage == 'Constraint validation failure in input message'
    And match response.errorCode == 'invalid.input'