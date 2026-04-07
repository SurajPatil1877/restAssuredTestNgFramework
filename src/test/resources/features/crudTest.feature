@CRUD
Feature: CRUD tests for restful booker

  Scenario: E2E  CRUD Scenario for Restful Booker APIs
    #Create
    Given  We have a valid request for  create booking with following params as Map and total price 1000
      | firstName        | John    |
      | lastName         | Simpson |
      | depositPaid      | false   |
      | additionalNeeds  | Soda    |
      | checkInPlusDays  | 20      |
      | checkoutPlusDays | 25      |
    When  We send request to create booking API
    Then API Response should have HTTP Status code 200
    And Create booking API response has valid bookingId
#Read
    When bookingId has been saved in shared context
    And We retrieve the booking using bookingId
    Then API Response should have HTTP Status code 200
    When getBooking API response should have fields same as create request
#Update
    And we prepare a request for update booking API
      | firstName        | John          |
      | lastName         | Simpson       |
      | depositPaid      | true          |
      | additionalNeeds  | Mineral Water |
      | checkInPlusDays  | 20            |
      | checkoutPlusDays | 25            |
      | totalPrice       | 500           |
    And we send request to update booking API
    Then API Response should have HTTP Status code 200
#Delete
    And we send request to delete booking API
    Then API Response should have HTTP Status code 201
    #Validation
    When We retrieve the booking using bookingId
    Then API Response should have HTTP Status code 404