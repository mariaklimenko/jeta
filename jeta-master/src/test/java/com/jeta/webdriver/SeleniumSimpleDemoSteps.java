package com.jeta.webdriver;

import org.apache.log4j.Logger;
import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

/**
 * Created by mariaklimenko on 25.02.2017.
 */

public class SeleniumSimpleDemoSteps {

    private final static Logger logger = Logger.getLogger(SeleniumSimpleDemoSteps.class);
    private SeleniumDriver selenium;
    private WebDriver driver;

    //Constructor
    public SeleniumSimpleDemoSteps(SeleniumDriver selenium){
        this.selenium = selenium.getInstance();
        this.driver=selenium.getDriver();
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
