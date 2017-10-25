@api @dashboards @get @error-authentication
Feature: Get all the dashboards features using invalid authentication
  In order to get all dashboards using invalid authentication
  As a BA
  I want to get a specific error

  Scenario: Get all the dashboards using invalid token
    Given url urlBase
    And path '/dashboards'
    And header authorization = 'Bearer invalidtoken'
    When method get
    Then status 401

  #ignored until Max response
  @ignore
  Scenario: Get all the dashboards without authentication header
    Given url urlBase
    And path '/dashboards'
    When method get
    Then status 401
