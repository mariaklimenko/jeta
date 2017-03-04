package com.jeta.webdriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;

/**
 * Created by mariaklimenko on 02.03.2017.
 */

public class SharedDriver extends EventFiringWebDriver {

    private final static Logger logger = Logger.getLogger(SharedDriver.class);

    // create webdriver for test run, use browser received from VM options (if not specified then Chrome will be used by default)
    private static final WebDriver REAL_DRIVER = WebDriverFactory.createInstance(System.getProperty("browser", "chrome"));
  /* private static WebDriver REAL_DRIVER = new EdgeDriver(); */

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

    @Before
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