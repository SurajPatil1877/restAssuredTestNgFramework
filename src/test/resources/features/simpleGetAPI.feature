Feature: Simple HTTP Get Call

  This is a simple example for HTTP Get with Cucumber

  Scenario: Make HTTP get call and validate status code is 200
    Given I prepare a simple HTTP GET request
    When I send the request to API
    Then API Response should have HTTP Status code 200