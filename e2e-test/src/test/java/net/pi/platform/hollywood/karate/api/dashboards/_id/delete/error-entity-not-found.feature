@api @dashboards @id @delete @error-entity-not-found
Feature: Delete a non-existent dashboard
  In order to know if a non existent dashboard is being deleted Hollywood
  As a BA
  I want to receive a specific error

  Scenario: Delete non-existent dashboard
    * def validDashboard = call read('classpath:net/pi/platform/hollywood/karate/api/dashboards/post/create-dashboard.feature')
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    Given url urlBase
    And path '/dashboards/invalidDashboard'
    And header authorization = 'Bearer ' + auth.token
    When method delete
    Then status 404