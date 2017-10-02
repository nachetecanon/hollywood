@api @dashboards @get @get-all-dashboards
Feature: Get all the dashboards features
  In order to manage dashboards
  As a BA
  I want to get all dashboards

  Scenario: Get all the dashboards
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    When method get
    Then status 200
    And match response.[*].id == '#notnull'