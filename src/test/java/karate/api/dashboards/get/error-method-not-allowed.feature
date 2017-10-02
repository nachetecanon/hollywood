@api @dashboards @get @error-method-not-allowed
Feature: Try to get all the dashboards receiving an error message

  Scenario: A user makes a put to the get dashboards endpoint
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And request ""
    When method put
    Then status 405
    And match response.errorMessage == "Request method 'PUT' not supported"
    And match response.errorCode == "method.not.allowed"

  Scenario: A user makes a patch to the get dashboards endpoint
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And request ""
    When method patch
    Then status 405
    And match response.errorMessage == "Request method 'PATCH' not supported"
    And match response.errorCode == "method.not.allowed"

  Scenario: A user makes a delete to the get dashboards endpoint
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And request ""
    When method delete
    Then status 405
    And match response.errorMessage == "Request method 'DELETE' not supported"
    And match response.errorCode == "method.not.allowed"