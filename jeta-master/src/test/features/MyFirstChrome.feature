Feature: Simple checks in "Selenium Simplified" site

  Scenario: Check site title
    Given I navigate to site "http://seleniumsimplified.com"
    Then Site title is "Selenium Simplified"

  Scenario: Teardown
    Given Driver closed and quited