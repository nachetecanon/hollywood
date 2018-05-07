@api @dashboards @get @get-all-dasboards-authorized @authorization
Feature: Get one dashboard authorized
  In order to know if a user is authorized to see a dashboard
  As a BA
  I want to get a dashboard authorized

  Scenario: Get one dashboard authorized
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    Given url urlBase1
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    When method get
    Then status 200
    And match response.[*].id == '#notnull'