@WebTests
Feature: Check successful login scenario on "Demo Web Shop"

  Scenario: Open "Login Page" on site "Demo Web Shop"
    Given I navigate to site Demo Shop
    When I click header link Login
    Then I am on Login page

  Scenario: Login as user Mary Black
    Given I enter credentials for mary_black
    When I click Login button
    Then I logged as mary_black