package com.jeta.webdriver;

import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by mariaklimenko on 25.02.2017.
 */
public class SeleniumSimplfiedSteps {
    private WebDriver driver = new ChromeDriver();

    @Given("^Driver closed and quited$")
    public void driver_closed_and_quited() throws Throwable {
        driver.close();
        driver.quit();
    }

    @Given("^I navigate to site \"(.*?)\"$")
    public void i_navigate_to_site(String site) throws Throwable {
        driver.navigate().to(site);
    }

    @Then("^Site title is \"(.*)\"$")
    public void checkSiteTitle (String title) throws Throwable {
        String realTitle = driver.getTitle();
        Assert.assertTrue("Expected titile is " + title + ", while real title value is " + realTitle, realTitle.startsWith(title));
    }
}
