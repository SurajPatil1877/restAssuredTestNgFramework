@UpdateBooking
Feature: Update an existing booking
  Create and update booking in same scenario


  Scenario: Create a new booking and update the additional needs and price
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
    When bookingId has been saved in shared context
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


