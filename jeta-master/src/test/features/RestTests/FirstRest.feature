@rest
Feature: make get request

  Scenario: get request
    Given Request GET comments, code "200"
    And Response contains "500" comments
