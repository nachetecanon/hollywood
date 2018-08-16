@api @dashboards @post @error-authentication
Feature: Create a new dashboard using invalid authentication
  In order to manage dashboards using invalid authentication
  As a BA
  I want to get a specific error

  Scenario: Create a dashboard using an invalid token
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardAllParametersRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer invalid'
    And request validDashboardAllParametersRequest
    When method post
    Then status 401

  #ignore until Max response
  @ignore
  Scenario: Create a dashboard without authentication header
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboard = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And set validDashboard.widgets = []
    And request validDashboard
    When method post
    Then status 401
