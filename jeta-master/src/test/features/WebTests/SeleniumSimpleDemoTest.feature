@first @WebTests
Feature: Simple checks in "Demo Web Shop" site

  #http://demowebshop.tricentis.com/
  Scenario: Check site title
    Given Selenide I navigate to site "http://demowebshop.tricentis.com/"
    Then Selenide Site title is "Demo Web Shop"
