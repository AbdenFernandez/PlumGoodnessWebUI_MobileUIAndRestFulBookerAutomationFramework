@web @android
Feature: Validate Filter Functionality

  Background:
    Given user open application
    Then  verify user is on home page
    When  user search "product.type" in search bar
    Then  verify searched product is displayed

  Scenario: verify price range filter works correctly
    When user chooses the first price filter from the list
    Then verify price filter is applied to the search list


  Scenario: verify concern filter works correctly
    When user chooses the first concern filter from the list
    Then verify concern filter is applied to the search list