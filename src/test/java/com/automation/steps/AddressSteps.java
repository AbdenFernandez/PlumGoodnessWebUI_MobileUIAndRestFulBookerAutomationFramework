package com.automation.steps;

import com.automation.pages.android.AndroidAddressPage;
import com.automation.pages.ui.AddressPage;
import com.automation.pages.web.WebAddressPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AddressSteps {

    AddressPage addressPage;

    public AddressSteps(){
        if(System.getProperty("platform","web").equals("web")){
            addressPage = new WebAddressPage();
        }
        else addressPage = new AndroidAddressPage();
    }

    @Then("verify user is on address page")
    public void verifyUserIsOnAddressPage() {
        Assert.assertTrue(addressPage.isAddressPageDisplayed());
    }

    @When("user click on add new address button")
    public void userClickOnAddNewAddressButton() {
        addressPage.clickOnAddNewAddress();
    }

    @Then("verify address form page is displayed")
    public void verifyAddressFormPageIsDisplayed() {
        Assert.assertTrue(addressPage.isAddressFormDisplyed());
    }

    @When("user fills in new address")
    public void userFillsInNewAddress() {
        addressPage.fillForm();
    }

    @Then("user clicks on save address button")
    public void userClicksOnSaveAddressButton() {
        addressPage.clickOnSaveAddress();
    }

    @And("verify address added is added successfully")
    public void verifyAddressAddedIsAddedSuccessfully() {
        Assert.assertTrue(addressPage.verifyAddressAddedSuccessfully());
    }

    @When("user click delete button of address")
    public void userClickDeleteButtonOfAddress() {
        addressPage.clickDeleteButton();
        System.out.println("######################Accepting the alert########################");
        addressPage.clickConfirmDelete();
    }

    @Then("verify address is deleted successfully")
    public void verifyAddressIsDeletedSuccessfully() {
        Assert.assertTrue(addressPage.verifyAddressDeleted());
    }

    @And("verify user cannot delete default address")
    public void verifyUserCannotDeleteDefaultAddress() {
        Assert.assertTrue(addressPage.userCannotDelete());
    }

    @When("user click delete button of default address")
    public void userClickDeleteButtonOfDefaultAddress() {
            addressPage.clickDeleteBtnOfDefaultAddress();
    }
}
