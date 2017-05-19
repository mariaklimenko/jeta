package com.jeta.webdriver;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SelenideElement.*;
import static com.codeborne.selenide.Condition.*;
/**
 * Created by mariaklimenko on 25.02.2017.
 */

public class SelenideShopHomePageObject {
    private final static Logger logger = Logger.getLogger(SeleniumDriver.class);

    //Locators
    //Log in link on home page header
    private SelenideElement header_link_login = $(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"));

    //Page title in Login mode
    private SelenideElement page_title = $(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[1]/h1"));

    //User Account link on home page header
    private SelenideElement header_link_account = $(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a"));

//    @FindBy(xpath = "//*[@id=\"Email\"]") //*[@id="Email"]
    private SelenideElement email = $(By.id("Email"));

//    @FindBy(xpath = "//*[@id=\"Password\"]")
    private SelenideElement password = $(By.xpath("//*[@id=\"Password\"]"));

 //   @FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")
    private SelenideElement login_btn = $(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));

    //Constructor
    public SelenideShopHomePageObject(SeleniumDriver selenium){
    }

    public void clickOnHeaderLinkLogin(){
        logger.info("link text: " + header_link_login.getText());
        header_link_login.click();
        $(page_title).should(visible);
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
        $(By.id("nivo-slider")).should(visible);
    }
}
