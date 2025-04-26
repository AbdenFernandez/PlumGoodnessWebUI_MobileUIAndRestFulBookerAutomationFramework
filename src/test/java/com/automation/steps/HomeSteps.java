package com.automation.steps;

import com.automation.pages.android.AndroidHomePage;
import com.automation.pages.ui.HomePage;
import com.automation.pages.web.WebHomePage;
import com.automation.utils.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class HomeSteps {
    HomePage homePage;

    public HomeSteps(){
        if(System.getProperty("platform","web").equals("web"))
            homePage=new WebHomePage();
        else
            homePage=new AndroidHomePage();
    }

    @Then("verify user is on home page")
    public void verify_user_is_on_home_page() {
        Assert.assertTrue(homePage.isHomePageDisplayed());
    }

    @When("user click login button")
    public void userClickLoginButton() {
        homePage.clickOnLoginBtn();
    }


    @Then("verify user is successfully logged in")
    public void verifyUserIsSuccessfullyLoggedIn() {
        Assert.assertTrue(homePage.isLoggedIn());
    }

    @Then("user close the pop-up")
    public void userCloseThePopUp() {
        homePage.clickPopUpClose();
    }

    @When("user search {string} in search bar")
    public void userSearchInSearchBar(String item) {
        homePage.itemSearch(ConfigReader.getConfigValue(item).toLowerCase());

    }

    @When("user clicks on profile button")
    public void userClicksOnProfileButton() {
        homePage.clickOnOrderHistory();
    }

    @When("user changes currency to {string}")
    public void userChangesCurrencyTo(String currency) {
        homePage.changeCurrency(currency);
    }

    @Then("verify currency is not {string}")
    public void verifyCurrencyIsNot(String inr) {
        Assert.assertTrue(homePage.verifyCurrencyIsNotINR(inr));
    }

    @When("user click on plum rewards button")
    public void userClickOnPlumRewardsButton() {
        homePage.clickOnPlumRewards();
    }
}
