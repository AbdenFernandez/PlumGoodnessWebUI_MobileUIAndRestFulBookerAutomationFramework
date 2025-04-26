@web @android
Feature: Validate sort functionality

  Background:
    Given user open application
    Then  verify user is on home page
    When  user search "product.type" in search bar
    Then  verify searched product is displayed


  Scenario: Verify the price sorting functionality works correctly in ascending order
    When user click on sort button
    When the user selects the option to sort products by price in ascending order
    Then the application displays products sorted from the lowest to highest price

  Scenario: Verify the price sorting functionality works correctly in descending order
    When user click on sort button
    When the user selects the option to sort products by price in descending order
    Then the application displays products sorted from the highest to lowest price