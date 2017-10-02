@api @dashboards @post @error-authorization
Feature: Create a new dashboard invalid user role
  In order to manage dashboards using invalid user role
  As a BA
  I want to get a specific error

  Scenario: Create a dashboard using an invalid role
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: 'hollywood.test', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardAllParametersRequest = call read('classpath:utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And request validDashboardAllParametersRequest
    When method post
    Then status 403
    And match response.status == "403"
    And match response.error == "Forbidden"
    And match response.message == "Access to the requested resource has been denied"