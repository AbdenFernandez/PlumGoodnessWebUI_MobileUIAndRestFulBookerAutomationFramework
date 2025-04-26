package com.automation.pages.android;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AndroidLoginPage extends BasePage implements LoginPage {


    @FindBy(xpath = "//android.view.View[@content-desc='welcome to the plum goodness app']")
    WebElement welcome;

    @FindBy(xpath = "//android.widget.EditText[contains(@hint,'enter phone number')]")
    WebElement phoneInput;

    @FindBy(id = "com.google.android.gms:id/positive_button")
    WebElement allowBtn;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"hello\n" +
            "full name*\n" +
            "email*\"]/android.widget.EditText[1]")
    WebElement fullName;

    @FindBy(xpath = "//android.widget.EditText[contains(@hint,'enter email id')]")
    WebElement email;

    @FindBy(xpath = "//android.view.View[@content-desc='skip']")
    WebElement skip;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"continue\"]")
    WebElement continueBtn;

    @FindBy(xpath = "//android.view.View[@content-desc=\"welcome to the plum goodness app\"]")
    WebElement loginPopUp;

    @FindBy(xpath = "//android.view.View[@content-desc=\"verify otp\"]")
    WebElement otpVerifyText;


    String XPATH_GENDER_VALUE = "//android.widget.ImageView[@content-desc=\"%s\"]";

    public void openApplication() {
        waitForElementVisible(skip);
        skip.click();
    }


    public boolean isLoginPageDisplayed() {
        return welcome.isDisplayed();

    }

    public void verification(String phone) {

        phoneInput.click();
        phoneInput.sendKeys(phone);
        if (isDisplayed(allowBtn)) {
            allowBtn.click();
        }
    }

    public void enterUsernameAndEmail(String name, String mail) {
        System.out.println(driver.getPageSource());

        fullName.click();
        fullName.sendKeys(name);
        email.click();
        email.sendKeys(mail);
        System.out.println(driver.getPageSource());
    }

    public void genderInput(String gender) {

        WebElement genderBtn = driver.findElement(By.xpath(String.format(XPATH_GENDER_VALUE, gender)));
        genderBtn.click();
    }


    public void skipCLick() {
        skip.click();
    }

    @Override
    public void clickOnLoginBtn() {
        continueBtn.click();
    }

    @Override
    public boolean verifyLoginPopUpIsDisplayed() {
        return loginPopUp.isDisplayed();
    }

    @Override
    public void userEnterPhoneNumber(String number) {
        phoneInput.click();
        phoneInput.sendKeys(number);
    }

    @Override
    public boolean verifyOtpPageIsDisplayed() {

        boolean otpVerifyTextDisplayed = isDisplayed(otpVerifyText);
        boolean t = otpVerifyTextDisplayed;
        while (otpVerifyTextDisplayed){

            System.out.println("Waiting for otp verification");
            otpVerifyTextDisplayed = isDisplayed(otpVerifyText);
            pause(2000);
        }
        return t;
    }
}
