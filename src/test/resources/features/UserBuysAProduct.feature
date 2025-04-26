@web @android
Feature: Validate Shopping Functionality

  Background:
    Given user open application
    Then  verify user is on home page
    When  user search "product.search" in search bar
    Then  verify searched product is displayed

  Scenario: verify user can place order successfully
    When  user clicks on add to cart button of first product
    Then  verify product added to cart successfully
    When user goes to cart page
    And verify user is on cart page
    And user clicks in checkout button
    And verify checkout page is displayed

