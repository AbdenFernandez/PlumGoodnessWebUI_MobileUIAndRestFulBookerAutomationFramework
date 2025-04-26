package com.automation.pages.android;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.ProfilePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AndroidProfilePage extends BasePage implements ProfilePage {

    @FindBy(xpath = "//android.view.View[@content-desc=\"edit profile\"]")
    WebElement editProfile;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,'manage address')]")
    WebElement manageAddress;

    @FindBy(xpath = "//android.view.View[contains(@content-desc,\"gender\")]/android.view.View/android.view.View/android.view.View")
    List<WebElement> genderOption;

    @FindBy(xpath = "//android.widget.Button[@content-desc=\"done\"]")
    WebElement done;

    @FindBy(xpath = "//android.widget.EditText")
    WebElement nameInput;

    @FindBy(xpath = "(//android.view.View/android.view.View/android.view.View/android.view.View/android.view.View)[1]")
    WebElement username;

    public void editProfileDetails() {
        editProfile.click();
    }

    @Override
    public boolean verifyUserIsOnProfilePage() {
        pause(10000);
        return manageAddress.isDisplayed();
    }

    @Override
    public void clickOnManageAddress() {
        manageAddress.click();
    }

    @Override
    public boolean verifyUserEditUsername(String name) {
        pause(10000);
        waitForElementVisible(username);

        String newName = username.getAttribute("content-desc");
        System.out.println(newName);
        System.out.println(name);
        return name.equals(newName);

    }


    @Override
    public void chooseGender() {
        pause(2000);
        genderOption.getFirst().click();
    }

    public void editUserName(String name) {
        pause(2000);
        nameInput.click();
        nameInput.clear();
        nameInput.sendKeys(name);
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

    }

    public void editProfileDone() {
        done.click();
    }

}
