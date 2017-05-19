package com.jeta.webdriver;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by mariaklimenko on 02.03.2017.
 */

public class SeleniumDriver extends EventFiringWebDriver {

    private static Logger logger = Logger.getLogger(SeleniumDriver.class);

    // create webdriver for test run, use browser received from VM options (if not specified then Chrome will be used by default)
    private static SeleniumDriver instance;
    private static final WebDriver driver = WebDriverFactory.createInstance(System.getProperty("browser", "chrome"));
    private static WebDriverRunner runner; // setWebDriver(WebDriver)

    public enum Locators		{ xpath, id, name, classname, paritallinktext, linktext, tagname };

    private static final Thread CLOSE_THREAD = new Thread() {

        public void run() {
            driver.close();
            driver.quit();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public SeleniumDriver() {
        super(driver);
    }

    public static SeleniumDriver getInstance(){
        if (instance == null){
            instance = new SeleniumDriver();
            runner = new WebDriverRunner();
            runner.setWebDriver(driver);
        }
        return instance;
    }
    public void close() {

        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    //a method to allow retrieving our driver instance
    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverRunner getRunner() {
        return runner;
    }
}