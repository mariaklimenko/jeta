@first @second
Feature: Simple checks in "Demo Web Shop" site

  Scenario: Check site title
    Given I navigate to site "http://demowebshop.tricentis.com/"
    Then Site title is "Demo Web Shop"

  Scenario: Teardown
    Given Driver closed and quited