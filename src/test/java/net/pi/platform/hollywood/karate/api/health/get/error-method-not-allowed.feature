@api @health @error-method-not-allowed
@ignore
Feature: Error Method Not Allowed
  In order to disable endpoints method not allowed
  As a Security Tester
  I want to know that health status only enables GET method.

  Scenario: A user makes a post to the health endpoint
    Given url urlBase
    And path '/health'
    And request ""
    When method post
    Then status 405
    And match response ==
    """
    {
      "errorMessage": "Request method 'POST' not supported",
      "errorCode": "method.not.allowed"
    }
    """

  Scenario: A user makes a put to the health endpoint
    Given url urlBase
    And path '/health'
    And request ""
    When method put
    Then status 405
    And match response ==
    """
    {
      "errorMessage": "Request method 'PUT' not supported",
      "errorCode": "method.not.allowed"
    }
    """

  Scenario: A user makes a patch to the health endpoint
    Given url urlBase
    And path '/health'
    And request ""
    When method patch
    Then status 405
    And match response ==
    """
    {
      "errorMessage": "Request method 'PATCH' not supported",
      "errorCode": "method.not.allowed"
    }
    """

  Scenario: A user makes a delete to the health endpoint
    Given url urlBase
    And path '/health'
    And request ""
    When method delete
    Then status 405
    And match response ==
    """
    {
      "errorMessage": "Request method 'DELETE' not supported",
      "errorCode": "method.not.allowed"
    }
    """
