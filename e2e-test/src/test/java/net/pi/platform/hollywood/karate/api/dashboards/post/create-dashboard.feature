@api @dashboards @post @create-dashboard
Feature: Create a new dashboard
  As a BA
  I want to create a dashboard
  In order to see dashboards

  Scenario: Create a dashboard
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardAllParametersRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And request validDashboardAllParametersRequest
    When method post
    Then status 200
    And print response
    And set validDashboardAllParametersRequest.id = response.id
    And set validDashboardAllParametersRequest.createdDate = response.createdDate
    And set validDashboardAllParametersRequest.lastModifiedDate = response.lastModifiedDate
    And match response == validDashboardAllParametersRequest
