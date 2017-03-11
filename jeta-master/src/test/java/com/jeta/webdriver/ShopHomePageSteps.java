package com.jeta.webdriver;

import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by mariaklimenko on 25.02.2017.
 */

public class ShopHomePageSteps {
    private ShopHomePage homePage;

    //Page URL
    private static String PAGE_URL="http://demowebshop.tricentis.com/";

    private static WebDriver driver;
    private static SeleniumDriver selenium;
    //Constructor
    public ShopHomePageSteps(SeleniumDriver driver){
        this.selenium = selenium.getInstance();
        this.driver=selenium.getDriver();
       homePage = new ShopHomePage(driver);
    }

    @Given("^I navigate to site Demo Shop$")
    public void i_navigate_to_site_demo_shop() throws Throwable {
      //  driver.get(PAGE_URL);
        driver.navigate().to(PAGE_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[1]/a/img"));
    }

    @When("^I click header link Login$")
    public void i_click_header_link_login() throws Throwable {
        homePage.clickOnHeaderLinkLogin();
    }

    @Then("^I am on Login page$")
    public void i_am_on_login_page() throws Throwable {
        String expectedTitle = "Welcome, Please Sign In!";
        String actualTitle = homePage.getPageTitle();
        Assert.assertTrue("Expected account name is " + expectedTitle + ", while actual account value is " + actualTitle, actualTitle.equals(expectedTitle));
    }

    @Then("^I logged as mary_black$")
    public void i_logged_as_correct_user() throws Throwable {
        String expectedTitle = "maryblack12345@gmail.com";
        String actualTitle = homePage.getUserAccount();
        Assert.assertTrue("Expected account name is " + expectedTitle + ", while actual account value is " + actualTitle, actualTitle.equals(expectedTitle));
    }

    @Given("^I enter credentials for mary_black$")
    public void i_enter_credentials_user() throws Throwable {
        homePage.enterCredentials();
    }

    @When("^I click Login button$")
    public void i_click_login_button() throws Throwable {
        homePage.btnLoginClick();
    }
}


