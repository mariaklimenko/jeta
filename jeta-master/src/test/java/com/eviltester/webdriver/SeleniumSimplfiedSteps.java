package com.eviltester.webdriver;

import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Maria on 25.02.2017.
 */
public class SeleniumSimplfiedSteps {
    private WebDriver driver = new ChromeDriver();

    @Given("^Driver closed and quited$")
    public void driver_closed_and_quited() throws Throwable {
        driver.close();
        driver.quit();
    }

    @Given("^I navigate to site \"(.*)\"$")
    public void navigateToSite(String site) throws Throwable {
        driver.navigate().to(site);
    }

    @Then("^Site title is \"(.*)\"$")
    public void checkSiteTitle (String title) throws Throwable {
        Assert.assertTrue("title should start differently", driver.getTitle().startsWith("Selenium Simplified"));
        driver.close();
        driver.quit();
    }
}
