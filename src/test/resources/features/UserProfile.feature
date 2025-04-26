Feature: Verify user profile functionality

  Background:
    Given user open application
    Then verify user is on home page
    When user click login button
    Then verify login pop up is displayed
    When user input phone number "phone.no"
    And verify otp field is displayed and user input otp
    Then verify user is successfully logged in

  @web @android
  Scenario: user tries to successfully add new address and delete a saved address
    When user clicks on profile button
    Then verify user is on profile page
    When user clicks on manage address option
    Then verify user is on address page
    When user click on add new address button
    Then verify address form page is displayed
    When user fills in new address
    Then user clicks on save address button
    And verify address added is added successfully
    When user click delete button of address
    Then verify address is deleted successfully

  @android
  Scenario: user cannot delete default address
    When user clicks on manage address option
    Then verify user is on address page
    When user click delete button of default address
    And verify user cannot delete default address




