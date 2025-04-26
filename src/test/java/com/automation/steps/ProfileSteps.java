package com.automation.steps;

import com.automation.pages.android.AndroidProfilePage;
import com.automation.pages.ui.ProfilePage;
import com.automation.pages.web.WebProfilePage;
import com.automation.utils.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProfileSteps {

    ProfilePage profilePage;

    public ProfileSteps(){
        if(System.getProperty("platform","web").equals("web")){
            profilePage = new WebProfilePage();
        }else{
            profilePage = new AndroidProfilePage();
        }
    }

    @Then("verify user is on profile page")
    public void verifyUserIsOnProfilePage() {
        Assert.assertTrue(profilePage.verifyUserIsOnProfilePage());
    }


    @When("user clicks on manage address option")
    public void userClicksOnManageAddressOption() {
        profilePage.clickOnManageAddress();
    }


    @When("user clicks on edit profile option")
    public void userClicksOnEditProfileOption() {
        profilePage.editProfileDetails();
    }

    @And("user chooses the first gender option available")
    public void userChoosesTheFirstGenderOptionAvailable() {
        profilePage.chooseGender();

    }

    @Then("verify user edit profile successfully")
    public void verifyUserEditProfileSuccessfully() {
        profilePage.verifyUserIsOnProfilePage();
    }

    @And("user click on done button")
    public void userClickOnDoneButton() {
        profilePage.editProfileDone();
    }

    @And("user changes name to {string}")
    public void userChangesNameTo(String newName) {
        profilePage.editUserName(ConfigReader.getConfigValue(newName));
    }

    @Then("verify user name {string} is same as profile page username")
    public void verifyUserNameIsSameAsProfilePageUsername(String expectedUsername) {
        Assert.assertTrue(profilePage.verifyUserEditUsername(ConfigReader.getConfigValue(expectedUsername)));
    }

}
