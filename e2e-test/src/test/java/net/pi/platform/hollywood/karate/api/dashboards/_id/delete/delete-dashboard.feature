@api @dashboards @id @delete @delete-dashboard
Feature: Delete one dashboard
  In order to remove a dashboard from Hollywood
  As a BA
  I want to delete a dashboard

  Scenario: Delete one dashboard
    * def validDashboard = call read('classpath:net/pi/platform/hollywood/karate/api/dashboards/post/create-dashboard.feature')
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    Given url urlBase
    And path '/dashboards/'+ validDashboard.response.id
    And header authorization = 'Bearer ' + auth.token
    When method delete
    Then status 200