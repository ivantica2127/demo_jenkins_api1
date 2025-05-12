Feature: Authentication
   Scenario: Successful login as admin
     Given the service is available
     When the client sends username "admin" and password "1234"
     Then the response should have status code 200
     And the body should contain "token"

  Scenario: Successful login ad utp
    Given the service is available
    When the client sends username "utp" and password "1234"
    Then the response should have status code 200
    And the body should contain "token"

  Scenario Outline: Successful login for valid users
    Given the service is available
    When the client sends username "<username>" and password "<password>"
    Then the response should have status code 200
    And the body should contain "token"

    Examples:
      | username | password |
      | admin    | 1234     |
      | utp      | 1234     |

   Scenario: User send with invalid credentials
     Given the service is available
     When the client sends username "invalid_user" and password "wrong_password"
     Then the response should have status code 401
     And the response body should contain key "detail"
     And the response body's "detail" should be "Credenciales incorrectas"
     And the response body's title "title" should be "Unauthorized"