@web
Feature: Validate currency conversion feature

  Scenario Outline: verify user can convert currency

    Given user open application
    Then verify user is on home page
    When user changes currency to "<currency>"
    Then verify currency is not "₹"
    Examples:
      | currency               |
      | Euro                   |
      | US Dollar              |
      | Canadian Dollar        |
      | British Pound Sterling |
      | Dirham                 |



