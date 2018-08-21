@api @dashboards @get @error-authorization
Feature: Get all the dashboards features using invalid user role
  In order to get all dashboards using invalid role
  As a BA
  I want to get a specific error

  Scenario: Get all the dashboards using invalid role
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: 'hollywood.test', password: '#(password)', client_id: "#(client_id)"}
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    When method get
    Then status 403
    And match response.status == "403"
    And match response.error == "Forbidden"
    And match response.message == "Access to the requested resource has been denied"