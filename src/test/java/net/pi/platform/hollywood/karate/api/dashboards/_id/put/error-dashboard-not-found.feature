@api @dashboards @_id @put @error-dashboard-not-found
Feature: Try to Update a dashboard not found
  In order to manage dashboards
  As a BA
  I want to create a dashboard and after that update it

  Scenario: Try to Update a dashboard not found
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards/' + 'notFound'
    And header authorization = 'Bearer ' + auth.token
    And request validDashboardRequest
    When method put
    Then status 404
    And match response.errorMessage == 'Unable to update dashboard: Dashboard with id[notFound] not found'
    And match response.errorCode == 'uri.not.found'