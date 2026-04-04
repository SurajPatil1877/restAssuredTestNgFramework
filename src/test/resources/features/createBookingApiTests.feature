Feature: Create new bookings using DataTables

  Scenario: Create a new booking using DataTables as a List of Maps
    Given We have a valid request for  create booking with following params
      | firstName | lastName | depositPaid | additionalNeeds | totalPrice | checkInPlusDays | checkoutPlusDays |
      | Sam       | Alton    | false       | Cola            | 500        | 10              | 14               |
    When  We send request to create booking API
    Then API Response should have HTTP Status code 200
    And Create booking API response has valid bookingId

  Scenario: Create a new booking using DataTables as a  Maps
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

  Scenario: Create a new booking using DataTables as Maps and a int argument
    Given We have a valid request for  create booking with following params as Map and total price 1000
      | firstName        | John    |
      | lastName         | Simpson |
      | depositPaid      | false   |
      | additionalNeeds  | Soda    |
      | checkInPlusDays  | 20      |
      | checkoutPlusDays | 25      |
    When  We send request to create booking API
    Then API Response should have HTTP Status code 200
    And Create booking API response has valid bookingId

  Scenario Outline: Create a new booking using Scenario Outline
    Given We have a valid request for  create booking with following params
      | firstName | lastName | depositPaid | additionalNeeds | totalPrice   | checkInPlusDays | checkoutPlusDays |
      | Sam       | Alton    | false       | Cola            | <totalPrice> | 10              | 14               |
    When  We send request to create booking API
    Then API Response should have HTTP Status code 200
    And Create booking API response has valid bookingId
    Examples:
      | totalPrice |
      | 499        |
      | 999        |
      | 4999       |