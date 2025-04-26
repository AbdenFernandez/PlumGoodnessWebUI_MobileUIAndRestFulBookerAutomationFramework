package com.automation.pages.web;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.LoginPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebLoginPage extends BasePage implements LoginPage {

    @FindBy(xpath="//img[@id='close_button']/../../button")
    WebElement closeBtn;

    @FindBy(id = "iframe-kp")
    WebElement iframe;

    @FindBy(id = "kp-login-button-header-logo")
    WebElement userIcon;

    @FindBy(id = "phone-input")
    WebElement phoneInput;

    @FindBy(xpath = "//p[text()='We have sent verification code to']")
    WebElement otpText;


    public void openApplication(){
        driver.get(ConfigReader.getConfigValue("application.url"));
        driver.switchTo().frame(iframe);
        if (isDisplayed(closeBtn)) {
            System.out.println(closeBtn);
            closeBtn.click();
        }
        driver.switchTo().defaultContent();
    }

    public void clickOnLoginBtn(){
        userIcon.click();
    }

    public void inputPhoneNumber(){
        phoneInput.click();
    }

    public boolean verifyLoginPopUpIsDisplayed(){
        waitForElementVisible(iframe);
        driver.switchTo().frame(iframe);
        return phoneInput.isDisplayed();
    }

    public void userEnterPhoneNumber(String number){
        phoneInput.click();
        phoneInput.sendKeys(number);
    }

    public boolean verifyOtpPageIsDisplayed(){
        boolean val = isDisplayed(otpText);
        if (val) driver.switchTo().defaultContent();
        return val;
    }

    public boolean verifyUserIsLoggedIn(){
        return otpText.isDisplayed();
    }
}
