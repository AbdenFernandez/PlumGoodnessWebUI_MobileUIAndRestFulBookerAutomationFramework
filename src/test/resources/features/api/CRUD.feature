@api
Feature: Verify CRUD functionality of the API

  @api
  Scenario: verify user can successfully create a booking

    Given user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"
    When user set body from "create_token.json" file
    Then user performs a post call
    Then verify status code is 200
    And verify response has field "token"
    And user saves the "token" as "token"
    Then user wants to call "/booking" end point
    When user set body from "create_booking.json" file as "CreateBookingRequestPojo"
    And set header "Content-Type" to "application/json"
    Then user performs a post call
    Then verify status code is 200
    And verify response has field "bookingid"

  @api
  Scenario: verify user can get his booking
    Given user wants to call "/booking" end point
    Then set header "Content-Type" to "application/json"
    When user set body from "create_booking.json" file as "CreateBookingRequestPojo"
    Then user performs a post call
    Then verify status code is 200
    And verify response has field "bookingid"
    And user saves the "bookingid" as "booking.id"
    Then user wants to call "/booking/{bookingid}" end point
    And set header "Content-Type" to "application/json"
    Then user set path parameter "bookingid" to "booking.id" from config
    When user performs a get call
    Then verify status code is 200
    And verify response has same scheme "create_booking_schema.json"


  @api
  Scenario: verify user can successfully update a booking

    Then user wants to call "/booking" end point
    And set header "Content-Type" to "application/json"
    When user set body from "update_booking.json" file as "CreateBookingRequestPojo"
    Then user performs a post call
    Then verify status code is 200
    And verify response has field "bookingid"

  @api
  Scenario: verify user can successfully delete a booking
    Given user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"
    When user set body from "create_token.json" file
    Then user performs a post call
    Then verify status code is 200
    And verify response has field "token"
    And user saves the "token" as "token"
    Given user wants to call "/booking" end point
    Then set header "Content-Type" to "application/json"
    When user set body from "create_booking.json" file as "CreateBookingRequestPojo"
    Then user performs a post call
    Then verify status code is 200
    And verify response has field "bookingid"
    And user saves the "bookingid" as "booking.id"
    Then user wants to call "/booking/{bookingid}" end point
    And set header "Content-Type" to "application/json"
    And set cookie header "Cookie" to "token"
    Then user set path parameter "bookingid" to "booking.id" from config
    When user performs a delete call
    Then verify status code is 201

  @api
  Scenario: verify user cannot delete a booking which is already deleted
    Given user wants to call "/auth" end point
    And set header "Content-Type" to "application/json"
    When user set body from "create_token.json" file
    Then user performs a post call
    Then verify status code is 200
    And verify response has field "token"
    And user saves the "token" as "token"
    Given user wants to call "/booking" end point
    Then set header "Content-Type" to "application/json"
    When user set body from "create_booking.json" file as "CreateBookingRequestPojo"
    Then user performs a post call
    Then verify status code is 200
    And verify response has field "bookingid"
    And user saves the "bookingid" as "booking.id"
    Then user wants to call "/booking/{bookingid}" end point
    And set header "Content-Type" to "application/json"
    And set cookie header "Cookie" to "token"
    Then user set path parameter "bookingid" to "booking.id" from config
    When user performs a delete call
    Then verify status code is 201
    Then user wants to call "/booking/{bookingid}" end point
    And set header "Content-Type" to "application/json"
    And set cookie header "Cookie" to "token"
    Then user set path parameter "bookingid" to "booking.id" from config
    When user performs a delete call
    Then verify status code is 405



