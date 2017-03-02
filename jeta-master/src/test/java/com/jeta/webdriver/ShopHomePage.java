package com.jeta.webdriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mariaklimenko on 25.02.2017.
 */

public class ShopHomePage {
    //Locators
    //Log in link on home page header
    @FindBy(how = How.LINK_TEXT, using = "Log in")
    private WebElement header_link_login;

    //Page title in Login mode
    @FindBy(how = How.CLASS_NAME , using = "page-title")
    private WebElement page_title;

    //User Account link on home page header
    @FindBy(how = How.CLASS_NAME , using = "account")
    private WebElement header_link_account;

    @FindBy(how = How.ID, using = "Email")
    private WebElement email;

    @FindBy(how = How.ID, using = "Password")
    private WebElement password;

    @FindBy(xpath = "/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")
    private WebElement login_btn;

    //Constructor
    public ShopHomePage(SharedDriver driver){
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnHeaderLinkLogin(){
        header_link_login.click();
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
    }
}
