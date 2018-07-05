@api @dashboards @post @create-dashboard-mandatory
Feature: Create a new dashboard with only mandatory fields
  As a BA
  I want to create a dashboard
  In order to see dashboards

  Scenario: Create a dashboard with only mandatory fields
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboard = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    * set validDashboard.widgets = []
    * remove validDashboard.bookmarked
    * remove validDashboard.hidden
    * remove validDashboard.image
    * remove validDashboard.link
    * remove validDashboard.type
    And request validDashboard
    When method post
    Then status 200
    And set validDashboard.id = response.id
    * set validDashboard.hidden = "#ignore"
    * set validDashboard.bookmarked = "#ignore"
    * set validDashboard.image = "#ignore"
    * set validDashboard.link = "#ignore"
    * set validDashboard.createdDate = "#ignore"
    * set validDashboard.lastModifiedDate = "#ignore"
    And match response == validDashboard

  Scenario: Create a dashboard without name - microcard
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def validDashboardAllParametersRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    * remove validDashboardAllParametersRequest.widgets[0].name
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