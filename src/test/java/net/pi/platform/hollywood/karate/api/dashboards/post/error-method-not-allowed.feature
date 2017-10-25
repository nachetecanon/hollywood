@api @dashboards @post @error-method-not-allowed
Feature: Try to create a new dashboard with not allowed http methods
  In order to disable endpoints method not allowed
  As a Security Tester
  I want to know that dashboard endpoint only enables GET and POST method.

  Scenario: A user makes a patch to the create endpoint
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And request message
    When method put
    Then status 405
    And match response ==
    """
    {
      "errorMessage": "Request method 'PUT' not supported",
      "errorCode": "method.not.allowed"
    }
    """

  Scenario: A user makes a patch to the create endpoint
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And request message
    When method patch
    Then status 405
    And match response ==
    """
    {
      "errorMessage": "Request method 'PATCH' not supported",
      "errorCode": "method.not.allowed"
    }
    """

  Scenario: A user makes a delete to the create endpoint
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And request message
    When method delete
    Then status 405
    And match response ==
    """
    {
      "errorMessage": "Request method 'DELETE' not supported",
      "errorCode": "method.not.allowed"
    }
    """
