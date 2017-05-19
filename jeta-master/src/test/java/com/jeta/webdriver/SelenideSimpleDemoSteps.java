package com.jeta.webdriver;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.Logger;
import org.junit.Assert;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by mariaklimenko on 25.02.2017.
 */

public class SelenideSimpleDemoSteps {

    private final static Logger logger = Logger.getLogger(SelenideSimpleDemoSteps.class);
    private WebDriverRunner runner;

    //Constructor
    public SelenideSimpleDemoSteps(SeleniumDriver selenium){
        runner = selenium.getRunner();
    }

    @Given("^Selenide I navigate to site \"(.*?)\"$")
    public void i_navigate_to_site(String site) throws Throwable {
        open(site);
    }

    @Then("^Selenide Site title is \"(.*)\"$")
    public void checkSiteTitle (String title) throws Throwable {
        String realTitle = title();

        Assert.assertTrue("Expected titile is " + title + ", while real title value is " + realTitle, realTitle.startsWith(title));
    }
}
