@api @dashboards @post @error-empty-value
Feature: Create a new dashboard with empty value parameters
  In order to manage dashboards
  As a BA
  I want to receive an error when I create a dashboard with empty value parameters

  Scenario: Create a dashboard with too short name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And set message.name = "123"
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

  Scenario: Create a dashboard with too long name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And set message.name = "1234567890123456789012345678901234567890123456789012345678901"
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


  Scenario: Create a widget with too short widget name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And set message.widgets[0].name = "123"
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

  Scenario: Create a widget with too long widget name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And set message.widgets[0].name = "1234567890123456789012345678901234567890123456789012345678901"
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

  Scenario: Create a dashboard with null name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
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

  Scenario: Create a widget with null widget name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And set message.widgets[0].name = null
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