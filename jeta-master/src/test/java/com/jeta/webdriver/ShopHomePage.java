package com.jeta.webdriver;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by mariaklimenko on 25.02.2017.
 */

public class ShopHomePage {
    private final static Logger logger = Logger.getLogger(SharedDriver.class);

    private final WebDriver driver;

    private WebElement myDynamicElement;
    //Locators
    //Log in link on home page header
    @FindBy(xpath = "/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")
    private WebElement header_link_login;

    //Page title in Login mode
    @FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/div/div[1]/h1")
    private WebElement page_title;

    //User Account link on home page header
    @FindBy(xpath = "/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a")
    private WebElement header_link_account;

    @FindBy(xpath = "//*[@id=\"Email\"]") //*[@id="Email"]
    private WebElement email;

    @FindBy(xpath = "//*[@id=\"Password\"]")
    private WebElement password;

    @FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")
    private WebElement login_btn;

    //Constructor
    public ShopHomePage(SharedDriver driver){
        this.driver = driver;
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnHeaderLinkLogin(){
        logger.info("link text: " + header_link_login.getText());
        header_link_login.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        myDynamicElement = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[1]/h1"));
    }

    public String getPageTitle(){
        return page_title.getText().toString();
    }

    public String getUserAccount(){
        return header_link_account.getText().toString();
    }

    public void enterCredentials(){
        email.clear();
        email.sendKeys("maryblack12345@gmail.com");
        password.clear();
        password.sendKeys("123456");
    }

    public void btnLoginClick(){
        login_btn.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"nivo-slider\"]/a"));
    }
}
