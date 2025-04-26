package com.automation.steps;

import com.automation.pages.android.AndroidCartPage;
import com.automation.pages.android.AndroidLoginPage;
import com.automation.pages.ui.CartPage;
import com.automation.pages.web.WebCartPage;
import com.automation.pages.web.WebLoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CartSteps {

    CartPage cartPage;
    public CartSteps(){
        if (System.getProperty("platform", "web").equals("web"))
            cartPage = new WebCartPage();
        else
            cartPage = new AndroidCartPage();
    }





    @And("verify user is on cart page")
    public void verifyUserIsOnCartPage() {
        Assert.assertTrue(cartPage.isCartPageDisplayed());
    }

    @And("user clicks on delete icon")
    public void userClicksOnDeleteIcon() {
        cartPage.clickOnDelete();
    }

    @Then("verify product successfully removed from cart")
    public void verifyProductSuccessfullyRemovedFromCart() {
        Assert.assertTrue(cartPage.isCartEmpty());
    }

    @Then("verify price breakdown in cart")
    public void verifyPriceBreakdownInCart() {
        cartPage.cartPriceBreakDown();
    }

    @And("user clicks in checkout button")
    public void userClicksInCheckoutButton() {
        cartPage.clickOnCheckOut();
    }

    @Then("user fills in address")
    public void userFillsInAddress() {
        cartPage.fillInAddress();
    }

    @When("user clicks continue button")
    public void userClicksContinueButton() {
        cartPage.clickOnContinueBtn();
    }

    @And("verify user is on payment page")
    public void verifyUserIsOnPaymentPage() {
        Assert.assertTrue(cartPage.isUserOnPaymentPage());
    }


    @And("verify checkout page is displayed")
    public void verifyCheckoutPageIsDisplayed() {
        Assert.assertTrue(cartPage.isCheckoutPageDisplayed());
    }
}
