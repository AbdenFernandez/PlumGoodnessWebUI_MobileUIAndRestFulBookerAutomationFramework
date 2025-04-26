@web @android
Feature: Validate Login Functionality

  Background:
    Given user open application
    Then verify user is on home page
    When user click login button
    And verify login pop up is displayed

  Scenario: User tries to login with valid phone number
    When user input phone number "phone.no"
    And verify otp field is displayed and user input otp
    Then verify user is successfully logged in


  Scenario Outline: verify user cannot login using invalid phone number
    When user input invalid phone number "<phone>"
    And verify otp field is not displayed
    Examples:
      | phone     |
      | 123456    |
      | 000000    |
      | 123456789 |