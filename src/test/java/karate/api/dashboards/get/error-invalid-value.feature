@api @dashboards @get @error-invalid-value
Feature: Try to get all the dashboards features with invalid syntax

  Scenario: Invalid syntax in URL path
    * def auth = call read('classpath:common/auth/keycloak-login.feature') { realm: '#(realm)', username: '#(username)', password: '#(password)', client_id: "#(client_id)"}
    Given url urlBase
    And path '/invSintax'
    And header authorization = 'Bearer ' + auth.token
    When method get
    Then status 404
    And match response ==
    """
     {
       "timestamp":#ignore,
       "status":404,
       "error":"Not Found",
       "message":"No message available",
       "path":"/api/invSintax"
      }
    """
