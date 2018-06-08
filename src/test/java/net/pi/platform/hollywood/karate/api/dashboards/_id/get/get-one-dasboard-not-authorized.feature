@api @dashboards @id @get @get-one-dashboard-not-authorized @authorization
Feature: Get one dashboard authorized
  In order to know if a user is not authorized to see a dashboard
  As a BA
  I want to get a dashboard non authorized

  Scenario: Get one dashboard not authorized
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    Given url urlBase1
    And path '/dashboards/28'
    And header authorization = 'Bearer ' + auth.token
    When method get
    Then status 405