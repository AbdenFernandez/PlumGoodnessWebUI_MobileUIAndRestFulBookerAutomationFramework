@web @android
Feature: Validate cart functionality of Plum Goodness App

  Background:
    Given user open application
    Then  verify user is on home page
    When  user search "product.search" in search bar
    Then  verify searched product is displayed

  Scenario: verify user can add product into cart
    When  user clicks on add to cart button of first product
    Then  verify product added to cart successfully

  Scenario: verify user can remove product from cart
    When  user clicks on add to cart button of first product
    Then  verify product added to cart successfully
    When user goes to cart page
    And verify user is on cart page
    And  user clicks on delete icon
    Then verify product successfully removed from cart

  Scenario: verify price breakdown in cart
    When  user clicks on add to cart button of first product
    Then  verify product added to cart successfully
    When user goes to cart page
    Then verify price breakdown in cart
