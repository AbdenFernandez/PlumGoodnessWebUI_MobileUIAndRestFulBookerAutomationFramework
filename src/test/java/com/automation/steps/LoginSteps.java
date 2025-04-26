package com.automation.steps;

import com.automation.pages.android.AndroidLoginPage;
import com.automation.pages.ui.LoginPage;
import com.automation.pages.web.WebLoginPage;
import com.automation.utils.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class LoginSteps {
    LoginPage loginPage;

    public LoginSteps() {
        if (System.getProperty("platform", "web").equals("web"))
            loginPage = new WebLoginPage();
        else
            loginPage = new AndroidLoginPage();
    }

    @Given("user open application")
    public void user_open_application() {
        loginPage.openApplication();
    }

    @Then("verify user is on login page")
    public void verify_user_is_on_login_page() {
        Assert.assertTrue(loginPage.isLoginPageDisplayed());
    }

    @When("user enter phone number {string} and wait for verification to complete")
    public void userEnterPhoneNumberAndWaitForVerificationToComplete(String phone) {
        loginPage.verification(ConfigReader.getConfigValue(phone));
    }

    @When("user enter full name {string} email {string}")
    public void user_enter_full_name_email(String uname, String email) {
        loginPage.enterUsernameAndEmail(ConfigReader.getConfigValue(uname), ConfigReader.getConfigValue(email));
    }

    @When("user enter gender {string}")
    public void user_enter_gender(String gender) {
        loginPage.genderInput(ConfigReader.getConfigValue(gender));
    }

//    @And("user click on continue")
//    public void userClickOnContinue() {
//        loginPage.clickOnLoginBtn();
//    }

    @When("user click on skip button")
    public void user_click_on_skip_button() {
        loginPage.skipCLick();
    }


//    @When("user click login button")
//    public void userClickLoginButton() {
//        loginPage.clickOnLoginBtn();
//    }

    @And("verify login pop up is displayed")
    public void verifyLoginPopUpIsDisplayed() {
        Assert.assertTrue(loginPage.verifyLoginPopUpIsDisplayed());
    }


    @And("verify otp field is displayed and user input otp")
    public void verifyOtpFieldIsDisplayedAndUserInputOtp() {
        Assert.assertTrue(loginPage.verifyOtpPageIsDisplayed());
    }


    @When("user input phone number {string}")
    public void userInputPhoneNumber(String number) {
        loginPage.userEnterPhoneNumber(ConfigReader.getConfigValue(number));
    }


    @And("verify otp field is not displayed")
    public void verifyOtpFieldIsNotDisplayed() {
        Assert.assertFalse(loginPage.verifyOtpPageIsDisplayed());
    }

    @When("user input invalid phone number {string}")
    public void userInputInvalidPhoneNumber(String phone) {
        loginPage.userEnterPhoneNumber(phone);
    }


}
