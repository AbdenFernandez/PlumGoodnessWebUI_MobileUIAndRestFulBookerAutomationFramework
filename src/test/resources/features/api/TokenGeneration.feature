@api
Feature: Verify token generation feature

  Background:
    Given user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"

  Scenario: verify user can generate token using valid credentials

    Then user set body from "create_token.json" file
    When user performs a post call
    Then verify status code is 200
    And verify response has field "token"


  Scenario Outline: verify user cannot generate token using invalid credentials

    Then user set body from "create_token.json" file with updated field "<username>" and value "<password>"
    When user performs a post call
    Then verify status code is 200
    And verify response has field "reason"

    Examples:
      | username | password    |
      | admin    | invalid     |
      | Admin    | password123 |
      | admin    | PASSWORD123 |
      | invalid  | invalid123  |
