@api @dashboards @post @error-uri-not-found
Feature: Create a new dashboard using an invalid uri
  In order to create dashboards using invalid uri
  As a BA
  I want to get a specific error

  Scenario: Create a dashboard
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardAllParametersRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboardsinvalid'
    And header authorization = 'Bearer ' + auth.token
    And request validDashboardAllParametersRequest
    When method post
    Then status 404
    And match response.error == "Not Found"
    And match response.path == "/api/dashboardsinvalid"