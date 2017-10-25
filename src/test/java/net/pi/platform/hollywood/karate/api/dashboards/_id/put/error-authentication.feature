@api @dashboards @_id @put @error-authentication
Feature: Update a dashboard using invalid authentication
  In order to update dashboards using invalid authentication
  As a BA
  I want to create a dashboard and after that update it

  Background:
    * def result = callonce read('classpath:karate/api/dashboards/post/create-dashboard.feature')

  Scenario: Update a dashboard using an invalid token
    * def validDashboardRequest = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And header authorization = 'Bearer invalidtoken'
    And request validDashboardRequest
    When method put
    Then status 401

  #ignore until Max response
  @ignore
  Scenario: Update a dashboard without authentication header
    * def validDashboardRequest = call read('classpath:karate/utility/funcs/generate-valid-dashboard.js')
    * set validDashboardRequest.id = result.dashboardId
    Given url urlBase
    And path '/dashboards/' + result.dashboardId
    And request validDashboardRequest
    When method put
    Then status 401

