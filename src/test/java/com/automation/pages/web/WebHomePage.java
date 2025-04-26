package com.automation.pages.web;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WebHomePage extends BasePage implements HomePage {

    @FindBy(xpath = "//img[@alt='Plum Goodness']")
    WebElement logo;

    @FindBy(xpath = "//img[@id='close_button']/../../button")
    WebElement closeBtn;

    @FindBy(id = "iframe-kp")
    WebElement iframe;

    @FindBy(xpath = "//div[@id='search-desktop']//input[@name='st']")
    WebElement searchBox;

    @FindBy(id = "kp-login-button-header-logo")
    WebElement userIcon;

    @FindBy(id = "logout-button-desktop")
    WebElement logoutBtn;

    @FindBy(id = "loggedin-icons")
    WebElement loggedInIcon;

    @FindBy(xpath = "//p[normalize-space()='Order History']")
    WebElement orderHist;

    @FindBy(xpath = "//div[contains(@class,'currency-switcher')]")
    WebElement currencySelect;

    @FindBy(xpath = "//div[contains(@class,'new_launches')]//strong//span")
    List<WebElement> newLaunchesPriceList;

    @FindBy(xpath = "//a[normalize-space()='Plum rewards']")
    WebElement plumReward;

    String currXp = "//div[contains(@class,'currency-switcher')]//li[contains(text(),'%s')]";

    public boolean isHomePageDisplayed() {
        return logo.isDisplayed();
    }

    public void clickPopUpClose() {
        driver.switchTo().frame(iframe);
        waitForElementVisible(closeBtn);
        System.out.println(closeBtn);
        closeBtn.click();
        System.out.println("CLosed it");
        driver.switchTo().defaultContent();
    }

    public void itemSearch(String item) {
        searchBox.sendKeys(item, Keys.ENTER);
    }

    @Override
    public boolean isLoggedIn() {
        boolean notFrameClosed = isDisplayed(iframe);
        while (notFrameClosed) {
            System.out.println("waiting for iframe to close");
            pause(1000);
            notFrameClosed = isDisplayed(iframe);
        }
        System.out.println("iframe closed");
        driver.switchTo().defaultContent();
        loggedInIcon.click();
        return logoutBtn.isDisplayed();
    }

    @Override
    public void clickOnOrderHistory() {
        orderHist.click();
    }

    @Override
    public void clickOnLoginBtn() {
        userIcon.click();
    }

    @Override
    public void changeCurrency(String currency) {
        waitForElementVisible(currencySelect);
        currencySelect.click();
        driver.findElement(By.xpath(String.format(currXp, currency))).click();
    }

    public boolean verifyCurrencyIsNotINR(String inr) {
        for (WebElement currency : newLaunchesPriceList) {
            if (currency.getText().contains(inr)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clickOnPlumRewards() {
        plumReward.click();
    }
}
