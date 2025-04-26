package com.automation.pages.web;

import com.automation.pages.common.BasePage;
import com.automation.pages.ui.PlumCharmPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WebPlumRewardsPage extends BasePage implements PlumCharmPage {
    @FindBy(xpath = "//div[@class='sh_inner_sav']")
    WebElement calculateBtn;

    @FindBy(xpath = "//input[@id='amount']")
    WebElement amtInput;

    @FindBy(xpath = "//button[contains(@onclick,'calculateSavings')]")
    WebElement calculateSavingsBtn;

    @FindBy(xpath = "//div[@class='price_div']//span[@class='price']")
    List<WebElement> cartPrices;

    @Override
    public boolean verifyUserIsOnRewardsPage() {
        return calculateBtn.isDisplayed();
    }

    @Override
    public void clickOnCalculateBtn() {
        calculateBtn.click();
    }

    @Override
    public void userInputAmount(int amount) {
        amtInput.sendKeys(String.valueOf(amount));
        calculateSavingsBtn.click();
    }

    @Override
    public boolean verifyPriceIsBelowCartValue(int amount) {
        for(WebElement price:cartPrices){
            if (Integer.parseInt(price.getText()) > amount){
                return false;
            }
        }
        return true;
    }
}
