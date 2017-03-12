@rest
Feature: Test GET and POST requests using RestAssured and Wiremock
  Scenario: Upload data to web service
    When I upload info about the project
    Then The server should process it and return a success status

  Scenario: Data retrieval from a web service
    When I want to get information on the Cucumber project
    Then The requested data received