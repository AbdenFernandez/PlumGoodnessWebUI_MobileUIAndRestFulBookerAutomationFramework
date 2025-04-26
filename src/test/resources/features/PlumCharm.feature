@web @android
Feature: Validate Plum Charm Feature

  Background:
    Given user open application
    Then verify user is on home page
    When user click login button
    Then verify login pop up is displayed
    When user input phone number "phone.no"
    And verify otp field is displayed and user input otp
    Then verify user is successfully logged in


  Scenario: verify the savings for a given cart value is within the specified value
    When user clicks on profile button
    Then verify user is on profile page
    When user click on plum rewards button
    Then verify user is on plum rewards page
    When user click on calculate button
    And user input cart value of 2000
    Then verify price for all tier is below the entered cart value of 2000
