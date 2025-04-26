@android
Feature: Validate user edit profile functionality

  Background:
    Given user open application
    Then verify user is on home page
    When user click login button
    Then verify login pop up is displayed
    When user input phone number "phone.no"
    And verify otp field is displayed and user input otp
    Then verify user is successfully logged in
    When user clicks on profile button
    Then verify user is on profile page

  Scenario: user can edit gender specification
    When user clicks on edit profile option
    And user chooses the first gender option available
    And user click on done button
    Then verify user edit profile successfully

  Scenario: user can edit user name
    When user clicks on edit profile option
    And user changes name to "new.userName"
    And user click on done button
    Then verify user name "new.userName" is same as profile page username