@api @dashboards @_id @put @error-authorization
Feature: Update a dashboard using invalid role
  In order to update dashboards using invalid role
  As a BA
  I want to get a specific error

  Background:
    * def result = callonce read('classpath:api/dashboards/post/create-dashboard.feature')

  Scenario: Update a dashboard using an invalid role
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: 'hollywood.test', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer ' + auth.token
    And request validDashboardRequest
    When method put
    Then status 403
    And match response.status == "403"
    And match response.error == "Forbidden"
    And match response.message == "Access to the requested resource has been denied"
