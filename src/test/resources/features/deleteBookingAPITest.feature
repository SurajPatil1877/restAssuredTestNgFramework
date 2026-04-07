@Delete
Feature: Delete an existing booking
  Create and delete booking in same scenario

  Scenario: Create and Delete new booking
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
    And we send request to delete booking API
    Then API Response should have HTTP Status code 201
