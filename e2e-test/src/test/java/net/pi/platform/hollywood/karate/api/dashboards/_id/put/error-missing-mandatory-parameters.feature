@api @dashboards @_id @put @error-missing-mandatory-parameters
Feature: Try to update a dashboard with missing mandatory parameters
  In order to manage dashboards
  As a BA
  I want to update a dashboard with missing mandatory parameters

  Background:
    * def result = call read('classpath:net/pi/platform/hollywood/karate/api/dashboards/post/create-dashboard.feature')

  Scenario: Update a dashboard with missing mandatory parameter dashboard name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.response.id
    Given url urlBase
    And path '/dashboards/' + result.response.id
    And header authorization = 'Bearer ' + auth.token
    And remove validDashboardRequest.name
    And request validDashboardRequest
    When method put
    Then status 400
    And match response.errorMessage == 'There is some wrong value in some input field'
    And match response.errorCode == 'invalid.input'