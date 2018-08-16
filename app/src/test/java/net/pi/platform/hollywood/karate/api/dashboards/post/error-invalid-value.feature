@api @dashboards @post @error-invalid-value
Feature: Try to create a new dashboard with invalid parameter name
  In order to manage dashboards
  As a BA
  I want to receive an error when I create a dashboard with invalid value parameters

  Scenario: Create a dashboard with longer name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And set message.name = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
    And request message
    When method post
    Then status 400
    And match response ==
    """
    {
      "errorMessage": "Constraint validation failure in input message",
      "errorCode": "invalid.input"
    }
    """

  Scenario: Create a widget with longer name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And set message.widgets[0].name = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
    And request message
    When method post
    Then status 400
    And match response ==
    """
    {
      "errorMessage": "Constraint validation failure in input message",
      "errorCode": "invalid.input"
    }
    """

  Scenario: Create a dashboard with ID that already exist
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def firstRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And request firstRequest
    When method post
    Then status 200
    And match response.name == firstRequest.name
    * def secondRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    And url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And set secondRequest.id = response.id
    And request secondRequest
    When method post
    Then status 400
    And match response ==
    """
    {
      "errorMessage": "You cannot create a dashboard with id",
      "errorCode": "invalid.input"
    }
    """

  Scenario: Create a dashboard with name null
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And set message.name = null
    And request message
    When method post
    Then status 400
    And match response ==
    """
    {
      "errorMessage": "There is some wrong value in some input field",
      "errorCode": "invalid.input"
    }
    """
