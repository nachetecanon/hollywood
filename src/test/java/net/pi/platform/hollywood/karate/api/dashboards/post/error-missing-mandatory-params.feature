@api @dashboards @post @error-missing-mandatory-params
Feature: Try to create a new dashboard with missing mandatory parameters
  In order to manage dashboards
  As a BA
  I want to receive an error when I create a dashboard with missing mandatory parameters

  Scenario: Create a dashboard without name
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    * def message = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer ' + auth.token
    And remove message.name
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
