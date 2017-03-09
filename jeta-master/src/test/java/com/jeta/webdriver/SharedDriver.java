package com.jeta.webdriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import java.util.function.Function;

/**
 * Created by mariaklimenko on 02.03.2017.
 */

public class SharedDriver extends EventFiringWebDriver {

    private static Logger logger = Logger.getLogger(SharedDriver.class);

    // create webdriver for test run, use browser received from VM options (if not specified then Chrome will be used by default)
    private static WebDriver REAL_DRIVER = WebDriverFactory.createInstance(System.getProperty("browser", "chrome"));

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.close();
            REAL_DRIVER.quit();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public SharedDriver() {
        super(REAL_DRIVER);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

  //  @Before
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            logger.error("Error while capture screenshot");
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }
}