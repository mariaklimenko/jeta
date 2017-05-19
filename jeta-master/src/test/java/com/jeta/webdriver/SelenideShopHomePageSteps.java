package com.jeta.webdriver;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SelenideElement.*;
import static com.codeborne.selenide.Condition.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by mariaklimenko on 25.02.2017.
 */

public class SelenideShopHomePageSteps {
    private SelenideShopHomePageObject homePage;

    //Page URL
    private static String PAGE_URL="http://demowebshop.tricentis.com/";
    private final static Logger logger = Logger.getLogger(SelenideSimpleDemoSteps.class);

    //Constructor
    public SelenideShopHomePageSteps(SeleniumDriver selenium){
        homePage = new SelenideShopHomePageObject(selenium);
    }

    @Given("^Selenide I navigate to site Demo Shop$")
    public void i_navigate_to_site_demo_shop() throws Throwable {
        open(PAGE_URL);

        By page_title = By.xpath("/html/body/div[4]/div[1]/div[1]/div[1]/a/img");
        $(page_title).should(visible);
    }

    @When("^Selenide I click header link Login$")
    public void i_click_header_link_login() throws Throwable {
        homePage.clickOnHeaderLinkLogin();
    }

    @Then("^Selenide I am on Login page$")
    public void i_am_on_login_page() throws Throwable {
        String expectedTitle = "Welcome, Please Sign In!";
        String actualTitle = homePage.getPageTitle();
        Assert.assertTrue("Expected account name is " + expectedTitle + ", while actual account value is " + actualTitle, actualTitle.equals(expectedTitle));
    }

    @Then("^Selenide I logged as mary_black$")
    public void i_logged_as_correct_user() throws Throwable {
        String expectedTitle = "maryblack12345@gmail.com";
        String actualTitle = homePage.getUserAccount();
        Assert.assertTrue("Expected account name is " + expectedTitle + ", while actual account value is " + actualTitle, actualTitle.equals(expectedTitle));
    }

    @Given("^Selenide I enter credentials for mary_black$")
    public void i_enter_credentials_user() throws Throwable {
        homePage.enterCredentials();
    }

    @When("^Selenide I click Login button$")
    public void i_click_login_button() throws Throwable {
        homePage.btnLoginClick();
    }
}


