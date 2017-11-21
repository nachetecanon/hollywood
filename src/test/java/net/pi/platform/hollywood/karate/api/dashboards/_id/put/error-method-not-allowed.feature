@api @dashboards @_id @put @error-method-not-allowed
Feature: Try to update a dashboard with an incorrect http method
  In order to manage dashboards
  As a BA
  I want to create a dashboard and after that update it
  Using incorrect http methods

  Background:
    * def result = call read('classpath:net/pi/platform/hollywood/karate/api/dashboards/post/create-dashboard.feature')

  Scenario: A user makes a get to the update endpoint
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.response.id
    Given url urlBase
    And path '/dashboards/' + result.response.id
    And header authorization = 'Bearer ' + auth.token
    And request validDashboardRequest
    When method get
    Then status 405
    And match response.errorMessage == "Request method 'GET' not supported"
    And match response.errorCode == "method.not.allowed"

  Scenario: A user makes a post to the update endpoint
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.response.id
    Given url urlBase
    And path '/dashboards/' + result.response.id
    And header authorization = 'Bearer ' + auth.token
    And request validDashboardRequest
    When method post
    Then status 405
    And match response.errorMessage == "Request method 'POST' not supported"
    And match response.errorCode == "method.not.allowed"

  Scenario: A user makes a patch to the update endpoint
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.response.id
    Given url urlBase
    And path '/dashboards/' + result.response.id
    And header authorization = 'Bearer ' + auth.token
    And request validDashboardRequest
    When method patch
    Then status 405
    And match response.errorMessage == "Request method 'PATCH' not supported"
    And match response.errorCode == "method.not.allowed"

  Scenario: A user makes a delete to the update endpoint
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.response.id
    Given url urlBase
    And path '/dashboards/' + result.response.id
    And header authorization = 'Bearer ' + auth.token
    And request validDashboardRequest
    When method delete
    Then status 405
    And match response.errorMessage == "Request method 'DELETE' not supported"
    And match response.errorCode == "method.not.allowed"
