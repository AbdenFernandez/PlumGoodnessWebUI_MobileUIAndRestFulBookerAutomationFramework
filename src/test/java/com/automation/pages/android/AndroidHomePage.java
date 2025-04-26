package com.automation.pages.android;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.HomePage;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AndroidHomePage extends BasePage implements HomePage {

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.view.View/android.view.View")
    WebElement homeElement;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"search \"]")
    WebElement searchBox;

    @FindBy(xpath = "//android.widget.EditText")
    WebElement searchInp;

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View")
    List<WebElement> searchRes;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc=\"my profile\"]")
    WebElement profileBtn;

    @FindBy(xpath = "//android.view.View[@content-desc=\"welcome to the plum goodness app\"]")
    WebElement loginPopUp;

    @FindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"charms\n" +
            "earn extra charms every time \")]")
    WebElement plumRewardsBtn;

    public boolean isHomePageDisplayed() {
        return isDisplayed(homeElement);
    }

    public void itemSearch(String item) {

        waitForElementClickable(searchBox);
        searchBox.click();
        searchInp.click();
        searchInp.sendKeys(item);

        ((AppiumDriver) driver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

    }

    @Override
    public boolean isLoggedIn() {
        waitForElementVisible(profileBtn);
        profileBtn.click();
        return !isDisplayed(loginPopUp);
    }

    @Override
    public void clickOnOrderHistory() {
      /*  waitForElementVisible(profileBtn);
        profileBtn.click();*/
    }

    @Override
    public void clickOnLoginBtn() {
        profileBtn.click();
    }

    @Override
    public void clickOnPlumRewards() {

        plumRewardsBtn.click();

    }
}
