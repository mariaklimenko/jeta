@WebTests
Feature: Check successful login scenario on "Demo Web Shop"

  Scenario: Open "Login Page" on site "Demo Web Shop"
    Given Selenide I navigate to site Demo Shop
    When Selenide I click header link Login
    Then Selenide I am on Login page

  Scenario: Login as user Mary Black
    Given Selenide I enter credentials for mary_black
    When Selenide I click Login button
    Then Selenide I logged as mary_black