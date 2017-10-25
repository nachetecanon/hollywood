@api @dashboards @post @create-dashboard
Feature: Create a new dashboard
  In order to manage dashboards
  As a BA
  I want to create a dashboard

  Scenario: Create a dashboard with only mandatory fields
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboard = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And set validDashboard.widgets = []
    And request validDashboard
    When method post
    Then status 200
    And set validDashboard.id = response.id
    And match response == validDashboard

  Scenario: Create a dashboard
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardAllParametersRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    And print validDashboardAllParametersRequest
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And request validDashboardAllParametersRequest
    When method post
    Then status 200
    And set validDashboardAllParametersRequest.id = response.id
    And match response == validDashboardAllParametersRequest
    And def dashboardId = validDashboardAllParametersRequest.id
