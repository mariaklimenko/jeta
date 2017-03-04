@first @WebTestExamples
Feature: Simple checks in "Demo Web Shop" site

  #http://demowebshop.tricentis.com/
  Scenario: Check site title
    Given I navigate to site "http://demowebshop.tricentis.com/"
    Then Site title is "Demo Web Shop"
