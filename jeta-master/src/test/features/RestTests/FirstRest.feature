@rest
Feature: make get request

  Scenario: get request
    Given Get request, url = "https://jsonplaceholder.typicode.com/comments", code "200"
    And Response contains "500" comments
