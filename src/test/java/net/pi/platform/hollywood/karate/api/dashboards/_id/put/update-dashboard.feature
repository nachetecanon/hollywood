@api @dashboards @_id @put @update-dashboard
Feature: Update a dashboard
  In order to manage dashboards
  As a BA
  I want to create a dashboard and after that update it

  Background:
    * def result = callonce read('classpath:net/pi/platform/hollywood/karate/api/dashboards/post/create-dashboard.feature')

  Scenario: Update a dashboard
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer ' + auth.token
    And set validDashboardRequest.bookmarked = "true"
    And request validDashboardRequest
    When method put
    Then status 204

