@api @dashboards @_id @put @error-authentication
Feature: Update a dashboard using invalid authentication
  As a BA
  I want to create a dashboard and after that update it
  In order to update dashboards using invalid authentication

  Background:
    * def result = call read('classpath:net/pi/platform/hollywood/karate/api/dashboards/post/create-dashboard.feature')

  Scenario: Update a dashboard using an invalid token
    * def validDashboardRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.response.id
    Given url urlBase
    And path '/dashboards/' + result.response.id
    And header authorization = 'Bearer invalidtoken'
    And request validDashboardRequest
    When method put
    Then status 401

  #ignore until Max response
  @ignore
  Scenario: Update a dashboard without authentication header token
    * def validDashboardRequest = call read('classpath:net/pi/platform/hollywood/karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.response.id
    Given url urlBase
    And path '/dashboards/' + result.response.id
    And request validDashboardRequest
    When method put
    Then status 401

