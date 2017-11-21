@api @health @check-health-status
@ignore
Feature: Check health status
  In order to have a Text Generator service working fine
  As a Sysadmin
  I want to know what health status got Hollywood service.

  Scenario: A user check health status
    Given url urlBase
    And path '/health'
    When method get
    Then status 200
    And match response ==
    """
    {
      status: "UP",
      diskSpace:
      {
        status: "UP",
        total: #ignore,
        free: #ignore,
        threshold: #ignore
      }
    }
    """

  @manual
  Scenario: A user check health status but disk space is full
    Given url urlBase
    And path '/health'
    When method get
    Then status 200
    And match response ==
    """
    {
      "status": "DOWN",
      "diskSpace":
      {
        "status": "DOWN",
        "total": #ignore,
        "free": #ignore,
        "threshold": #ignore
      }
    }
    """