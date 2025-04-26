package com.automation.steps;

import com.automation.pages.android.AndroidPlumRewardsPage;
import com.automation.pages.ui.PlumCharmPage;
import com.automation.pages.web.WebPlumRewardsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PlumCharmSteps {

    PlumCharmPage plumCharmPage;

    public PlumCharmSteps() {
        if (System.getProperty("platform", "web").equals("web")) {
            plumCharmPage = new WebPlumRewardsPage();
        } else {
            plumCharmPage = new AndroidPlumRewardsPage();
        }
    }

    @Then("verify user is on plum rewards page")
    public void verifyUserIsOnPlumRewardsPage() {
        Assert.assertTrue(plumCharmPage.verifyUserIsOnRewardsPage());
    }

    @When("user click on calculate button")
    public void userClickOnCalculateButton() {
        plumCharmPage.clickOnCalculateBtn();
    }

    @And("user input cart value of {int}")
    public void userInputCartValueOf(int amount) {
        plumCharmPage.userInputAmount(amount);
    }


    @Then("verify price for all tier is below the entered cart value of {int}")
    public void verifyPriceForAllTierIsBelowTheEnteredCartValueOf(int amount) {
        Assert.assertTrue(plumCharmPage.verifyPriceIsBelowCartValue(amount));
    }
}
